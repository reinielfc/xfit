package coach.xfitness.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import coach.xfitness.business.Equipment;
import coach.xfitness.business.User;
import coach.xfitness.data.EquipmentDB;
import coach.xfitness.data.UserDB;
import coach.xfitness.util.CookieUtil;
import coach.xfitness.util.PasswordUtil;

@WebServlet(name = "UserController", urlPatterns = {
        "/register", "/signin", "/authenticate", "/signout", "/configure"
})
public class UserController extends HttpServlet {
    private static final String[] COOKIES_TO_CLEAR = new String[] { "accessToken" };

    /**
     * If the request is for the register page, call the register function, if it's for
     * the signin page, call the signin function, if it's for the configure page, call
     * the configure function
     * 
     * @param request The request object is an instance of a class that implements the
     * ServletRequest interface. It encapsulates the request message sent by the client
     * to the server.
     * @param response The response object is used to send data back to the client.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String url = "/user/signin.jsp";

        if (request.getAttribute("authenticate") != null) {
            authenticate(request);
            return;
        }

        if (requestURI.endsWith("/register")) {
            url = register(request, response);
        } else if (requestURI.endsWith("/signin")) {
            url = signIn(request, response);
        } else if (requestURI.endsWith("/configure")) {
            url = configure(request, response);
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    // #region REGISTER

    // TODO: javadoc
    private String register(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String url = "/user/register.jsp";

        if (action.equals("verify")) {
            url = doVerifyEmailAction(request);
        } else if (action.equals("resend")) {
            url = doResendVerificationEmailAction(request, response);
        } else {
            url = doRegisterAction(request, response);
        }

        return url;
    }

    // #region doRegisterAction

    /**
    * It gets the user from the request, validates the user, secures the user's
    * password, saves the user in the session, and sends a verification email
    * 
    * @param request The request object that was sent to the servlet.
    * @param response The response object that will be sent back to the client.
    * @return The return value is a String that represents the path to the next JSP
    * page.
    */
    private String doRegisterAction(HttpServletRequest request, HttpServletResponse response) {
        User newUser = getUserFromRequest(request);
        String message = "";

        if (isUserValid(request, newUser)) {
            try {
                // secure user password
                secureUserPassword(newUser);

                // save new user in session, so it's accessible by email servlet
                HttpSession session = request.getSession();
                session.setAttribute("recipient", newUser);

                // send verification email with email servlet
                sendVerificationEmail(request, response);

                return "/user/verify.jsp";
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                e.printStackTrace();
                message = "An error has occurred. Please try again later.";
            } catch (ServletException | IOException e) {
                e.printStackTrace();
                message = "Verification email could not be sent. Please try again later.";
            }
        }

        newUser.setPassword("");
        request.setAttribute("newUser", newUser);
        request.setAttribute("message", message);

        return "/user/register.jsp";
    }

    /**
    * Get the name, email, and password from the request, and create a new User
    * object with those values.
    * 
    * @param request The request object that was sent to the servlet.
    * @return A new user object with the name, email, and password set.
    */
    private User getUserFromRequest(HttpServletRequest request) {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(password);

        return newUser;
    }

    /**
    * If the password is valid and the email is valid, then the user is valid.
    * 
    * @param request The request object that was sent to the servlet.
    * @param user The user object that was created from the form data.
    * @return A boolean value.
    */
    private boolean isUserValid(HttpServletRequest request, User user) {
        return isPasswordValid(request, user.getPassword()) && isEmailValid(request, user.getEmail());
    }

    /**
    * If the password is valid, set the passwordIsValid attribute to true, and return
    * true
    * 
    * @param request The request object that was sent to the servlet.
    * @param password The password entered by the user.
    * @return A boolean value.
    */
    private boolean isPasswordValid(HttpServletRequest request, String password) {
        boolean passwordIsValid = UserDB.isPasswordValid(password);
        request.setAttribute("passwordIsValid", passwordIsValid);
        return passwordIsValid;
    }

    /**
    * If the email is not already in the database, then it is valid
    * 
    * @param request The request object that was sent to the servlet.
    * @param email The email address entered by the user.
    * @return A boolean value.
    */
    private boolean isEmailValid(HttpServletRequest request, String email) {
        boolean emailIsValid = !UserDB.hasUserWithEmail(email);
        request.setAttribute("emailIsValid", emailIsValid);
        return emailIsValid;
    }

    /**
    * Takes a user object, generates a secure password, and sets the password on
    * the user object
    * 
    * @param user The user object that is being created.
    */
    private void secureUserPassword(User user) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String password = PasswordUtil.generate(user.getPassword());
        user.setPassword(password);
    }

    /**
    * Generate a random code, store it in the session, and send it to the user.
    * 
    * @param request The request object that was sent to the servlet.
    * @param response The response object that will be sent back to the client.
    */
    private void sendVerificationEmail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // generate email verification code
        String code = PasswordUtil.generateCode();

        // send code email
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/email");
        HttpSession session = request.getSession(false);

        // store code in session
        session.setAttribute("emailVerificationCode", code);

        requestDispatcher.include(request, response);
    }

    // #endregion doRegisterAction

    // #region verifyEmailAction

    /**
    * If the email is verified, register the user and sign them in
    * 
    * @param request The request object that was sent to the servlet.
    * @return The url of the next page to be displayed.
    */
    private String doVerifyEmailAction(HttpServletRequest request) {
        String message = "Email verification was unsuccessful.";
        String url = "/user/verify.jsp";

        if (isEmailVerified(request)) {
            // get user from session
            HttpSession session = request.getSession(false);
            User newUser = (User) session.getAttribute("recipient");

            // register user
            UserDB.insert(newUser);

            // sign user in
            session.setAttribute("user", newUser);
            session.removeAttribute("recipient");

            message = "Email verification was successful.";
            request.setAttribute("messageType", "success");

            url = "/equipment.jsp";
        }

        request.setAttribute("message", message);

        return url;
    }

    /**
    * It checks if the code in the request is the same as the code in the session
    * 
    * @param request The request object that was sent to the servlet.
    * @return A boolean value. // TODO: a boolean value is not descriptive
    */
    private boolean isEmailVerified(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String emailVerificationCode = (String) session.getAttribute("emailVerificationCode");
        String code = request.getParameter("code");

        boolean codeIsValid = !(code.isBlank() || !code.equals(emailVerificationCode));

        request.setAttribute("codeIsValid", codeIsValid);

        return codeIsValid;
    }

    // #endregion verifyEmailAction

    // #region resendVerificationEmailAction

    /**
    * It takes the user's email address from the form, changes the user's email
    * address in the database, and sends a new verification email
    * 
    * @param request The request object that was sent to the servlet.
    * @param response The response object that will be sent back to the client.
    * @return The return value is the path to the verify.jsp page.
    */
    private String doResendVerificationEmailAction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User newUser = (User) session.getAttribute("recipient");

        changeUserEmail(request, newUser);

        session.setAttribute("recipient", newUser);
        sendVerificationEmail(request, response);

        return "/user/verify.jsp";
    }

    /**
    * If the email parameter is not null and isEmailValid returns true, then set the
    * user's email to the email parameter.
    * 
    * @param request The request object that was sent to the servlet.
    * @param user The user object that is being edited.
    */
    private void changeUserEmail(HttpServletRequest request, User user) {
        String email = request.getParameter("email");
        if (email != null && isEmailValid(request, email)) {
            user.setEmail(email);
        }
    }

    // #endregion resendVerificationEmailAction

    // #endregion REGISTER

    // #region CONFIGURE

    /**
    * If the user is changing their user settings, then call the
    * configureUserSettings method, otherwise call the configureEquipmentSettings
    * method
    * 
    * @param request The request object that was sent to the servlet.
    * @param response The response object that will be sent back to the client.
    * @return The url of the page to be forwarded to.
    */
    private String configure(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String setting = request.getParameter("setting");
        String url = "/user/settings.jsp";

        if (setting.equals("user")) {
            url = configureUserSettings(request, response);
        } else if (setting.equals("equipment")) {
            url = configureEquipmentSettings(request);
        }

        return url;
    }

    // TODO: javadoc
    private String configureUserSettings(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String url = "/user/settings.jsp";

        if (session == null) {
            request.setAttribute("message", "Please sign in to configure your account.");
            return "/user/signin.jsp";
        }

        String message = "";

        User user = (User) session.getAttribute("user");

        // validate username
        String newName = request.getParameter("newName");
        if (!newName.isBlank()) {
            user.setName(newName);
        }

        // validate password
        String newPassword = request.getParameter("newPassword");
        if (!newPassword.isBlank()) {
            //TODO: validatePassword(newPassword);
            user.setPassword(newPassword);
            try {
                secureUserPassword(user);
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                e.printStackTrace();
                message = "An error has occurred. Please try again later.";
                request.setAttribute("message", message);
                return url;
            }
        }

        UserDB.update(user);

        return url;
    }

    /**
    * It takes a request, makes a list of equipments from the request, gets the user
    * from the request, sets the user's equipments to the list of equipments, and
    * updates the user in the database
    * 
    * @param request The request object that was sent to the servlet.
    * @return A string that is the path to the next page.
    */
    private String configureEquipmentSettings(HttpServletRequest request) {
        List<Equipment> equipments = makeEquipmentsFromRequest(request);

        HttpSession session = request.getSession(false);

        if (session == null) {
            request.setAttribute("message", "Please sign in to configure your equipment");
            return "/signin";
        }

        User user = (User) request.getAttribute("user");
        user.setUserEquipmentsByEquipments(equipments);

        UserDB.update(user);

        return "/routine/planner.jsp";
    }

    /**
    * Get the selected equipment ids from the request, convert them to integers,
    * fetch the equipment from the database, and return the list of equipment.
    * 
    * @param request The request object that was sent to the servlet.
    * @return A list of equipment objects
    */
    private List<Equipment> makeEquipmentsFromRequest(HttpServletRequest request) {
        // get selected equipments id list
        String[] equipmentIdValues = request.getParameterValues("equipmentId");

        // convert list to integers
        List<Integer> equipmentIds = Arrays.stream(equipmentIdValues).map(Integer::valueOf)
                .collect(Collectors.toList());

        // fetch equipment from database
        return EquipmentDB.selectByIdIn(equipmentIds);
    }

    // #endregion CONFIGURE

    // #region SIGNIN

    /**
     * If the user exists and the password is correct, then set the user's session and
     * redirect to the workout page
     * 
     * @param request The request object that is passed to the servlet.
     * @param response The response object is used to send the authentication cookies
     * to the browser.
     * @return The url is being returned.
     */
    private String signIn(HttpServletRequest request, HttpServletResponse response) {
        String url = "/user/signin.jsp";
        String message = "";

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");

        User user = UserDB.selectByEmail(email);

        try {
            if (user != null && PasswordUtil.verify(password, user.getPassword())) {

                if (rememberMe != null) {
                    saveAuthenticationCookies(user, response);
                }

                request.getSession().setAttribute("user", user);

                url = "/workout";
            } else {
                message = "Invalid credentials.";
                request.setAttribute("email", email);
                request.setAttribute("rememberMe", rememberMe);
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            message = "An error has occurred. Please try again later.";
        }

        System.out.println(message);
        request.setAttribute("message", message);

        return url;
    }

    /**
    * Creates two cookies, one for the access token and one for the email, and
    * then saves them to the client and the database
    * 
    * @param user the user object that was just created
    * @param response The response object that will be sent back to the client.
    */
    private void saveAuthenticationCookies(User user, HttpServletResponse response) {
        String accessToken = PasswordUtil.generateAccessToken();
        String email = user.getEmail();

        Cookie[] cookies = new Cookie[] {
                new Cookie("accessToken", accessToken),
                new Cookie("email", email)
        };

        // save to client
        for (Cookie cookie : cookies) {
            cookie.setMaxAge(60 * 60 * 24 * 3); // 3 days
            cookie.setPath("/"); // entire app
            cookie.setHttpOnly(true); // not accessible to client-side scripting
            response.addCookie(cookie);
        }

        user.setAccessToken(accessToken); // save to user
        UserDB.update(user); // save to database
    }

    // #endregion SIGNIN

    //  #region AUTHENTICATE

    /**
     * If the user has valid cookies, then set the user attribute in the session
     * 
     * @param request The request object that is passed to the servlet.
     */
    private void authenticate(HttpServletRequest request) {
        User user = makeUserFromCookies(request.getCookies());

        if (user != null) {
            request.getSession().setAttribute("user", user);
        }
    }

    /**
     * If the access token and email in the cookies match the access token and email
     * in the database, return the user.
      * 
     * @param cookies The cookies that were sent with the request.
     * @return A User object.
     */
    private User makeUserFromCookies(Cookie[] cookies) {
        String accessToken = CookieUtil.findValue(cookies, "accessToken");
        String email = CookieUtil.findValue(cookies, "email");

        if (!(accessToken == null || email == null)) {
            User user = UserDB.selectByEmail(email);

            if (user.getAccessToken().equals(accessToken)) {
                return user;
            }
        }

        return null;
    }

    // #endregion AUTHENTICATE

    /**
     * If the user is not authenticated, then forward the request to the signin page
     * 
     * @param request The request object
     * @param response The response object is used to send data back to the client.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/user/signin.jsp";
        String requestURI = request.getRequestURI();

        if (request.getAttribute("authenticate") != null) {
            authenticate(request);
            return;
        }

        if (requestURI.endsWith("/signout")) {
            url = signOut(request, response);
        } else if (requestURI.endsWith("/register")) {
            url = "/user/register.jsp";
        } else if (requestURI.endsWith("/configure")) {
            url = "/user/settings.jsp";
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);

    }

    // #region SIGNOUT

    /**
    * If there is a session, invalidate it, and clear all cookies.
    * 
    * @param request The request object that was sent to the servlet.
    * @param response The response object that will be sent back to the client.
    * @return The path to the index.jsp page.
    */
    private String signOut(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);

        // invalidate session
        if (session != null) {
            session.invalidate();
        }

        clearCookies(request.getCookies(), response);

        return "/index.jsp";
    }

    /**
    * For each cookie in the list of cookies to clear, find the cookie in the list of
    * cookies sent by the browser, set its max age to 0, set its path to "/", and add
    * it to the response.
    * 
    * @param cookies The cookies that were sent with the request.
    * @param response The response object that will be sent back to the client.
    */
    private void clearCookies(Cookie[] cookies, HttpServletResponse response) {
        Cookie cookie;
        for (String cookieName : COOKIES_TO_CLEAR) {
            cookie = CookieUtil.find(cookies, cookieName);
            if (cookie != null) {
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }
    }

    // #endregion SIGNOUT

}
