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
import coach.xfitness.util.ServletUtil;

@WebServlet(name = "UserController", urlPatterns = {
        "/register", "/signin", "/authenticate", "/signout", "/configure"
})
public class UserController extends HttpServlet {
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

    // #region register

    private String register(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String url = "/register.jsp";

        if (action.equals("verify")) {
            url = doVerifyEmailAction(request);
        } else {
            url = registerAction(request, response);
        }

        return url;
    }

    // TODO: change name to something more descriptive?
    private String registerAction(HttpServletRequest request, HttpServletResponse response) {
        User newUser = makeFromRequest(request);
        String validationMessage = validate(newUser);

        if (validationMessage.isBlank()) {
            try {
                securePassword(newUser);

                // save new user in session so it's accessible by email servlet
                HttpSession session = request.getSession();
                session.setAttribute("recipient", newUser);

                sendVerificationEmail(request, response);

                return "/verify-email.jsp";
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                e.printStackTrace();
                validationMessage = "An error has occurred. Please try again later.";
            } catch (ServletException | IOException e) {
                e.printStackTrace();
                validationMessage = "Verification email could not be sent. Please try again later.";
            }
        }

        request.setAttribute("validationMessage", validationMessage);

        return "/register.jsp";
    }

    // TODO: change name to something more descriptive?
    private String verifyAction(HttpServletRequest request) {
        String validationMessage = "Email verification was unsuccessful.";
        String url = "/verify-email.jsp";

        boolean codeIsVerified = verifyEmailedCode(request);

        if (codeIsVerified) {
            // get user from session
            HttpSession session = request.getSession(false);
            User newUser = (User) session.getAttribute("recipient");

            // register user
            UserDB.insert(newUser);

            // TODO: sign user in
            session.setAttribute("user", newUser);

            // remove new user
            session.removeAttribute("recipient");

            validationMessage = "Email verification was successful.";
            url = "/equipment.jsp";
        }

        request.setAttribute("validationMessage", validationMessage);

        return url;
    }

    private User makeFromRequest(HttpServletRequest request) {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(password);

        return newUser;
    }

    private void securePassword(User user) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String password = PasswordUtil.generate(user.getPassword());
        user.setPassword(password);
    }

    private String validate(User user) {
        String validationMessage = validatePassword(user.getPassword());

        if (!validationMessage.isBlank())
            return validationMessage;

        return validateEmail(user.getEmail());
    }

    private static String validatePassword(String password) {
        String validationMessage = "";

        if (!PasswordUtil.validate(password)) {
            validationMessage = "Password is not strong enough! "
                    + "Make sure it is at least 8 characters long, and "
                    + "include at least 1 character from A-Z, a-z, and 0-9.";
        }

        return validationMessage;
    }

    private static String validateEmail(String email) {
        String validationMessage = "";

        if (UserDB.hasUserWithEmail(email)) {
            validationMessage = "\"" + email + "\" is already in use.";
        }

        return validationMessage;
    }

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

    private boolean isEmailVerified(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String emailVerificationCode = (String) session.getAttribute("emailVerificationCode");
        String code = request.getParameter("code");

        boolean codeIsValid = !(code.isBlank() || !code.equals(emailVerificationCode));

        request.setAttribute("codeIsValid", codeIsValid);

        return codeIsValid;
    }

    private String doResendVerificationEmailAction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User newUser = (User) session.getAttribute("recipient");

        changeUserEmail(request, newUser);

        session.setAttribute("recipient", newUser);
        sendVerificationEmail(request, response);

        return "/user/verify.jsp";
    }

    private void changeUserEmail(HttpServletRequest request, User user) {
        String email = request.getParameter("email");
        if (email != null && isEmailValid(request, email)) {
            user.setEmail(email);
        }
    }

    // #endregion register

    // #region configure

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

    private List<Equipment> makeEquipmentsFromRequest(HttpServletRequest request) {
        // get selected equipments id list
        String[] equipmentIdValues = request.getParameterValues("equipmentId");

        // convert list to integers
        List<Integer> equipmentIds = Arrays.stream(equipmentIdValues).map(Integer::valueOf)
                .collect(Collectors.toList());

        // fetch equipment from database
        return EquipmentDB.selectByIdIn(equipmentIds);
    }

    // #endregion configure

    // #region signin

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
            //cookie.setSecure(true); // only accessible through https // TODO:
            cookie.setHttpOnly(true); // not accessible to client-side scripting
            response.addCookie(cookie);
        }

        user.setAccessToken(accessToken); // save to user
        UserDB.update(user); // save to database
    }

    // #endregion signin

    // #region authenticate

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

    // #endregion authenticate
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

    // #region signout

    private String signOut(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);

        // invalidate session
        if (session != null) {
            session.invalidate();
        }

        clearCookies(request.getCookies(), response);

        return "/index.jsp";
    }

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

    // #endregion signout

}
