package coach.xfitness.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import coach.xfitness.business.User;
import coach.xfitness.data.UserDB;
import coach.xfitness.util.CookieUtil;
import coach.xfitness.util.PasswordUtil;

@WebServlet(name = "UserController", urlPatterns = { "/signin", "/register", "/settings" })
public class UserController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String url = "";

        if (requestURI.endsWith("/register")) {
            url = register(request, response);
        } else if (requestURI.endsWith("/signin")) {
            try {
                url = signIn(request, response);
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if (requestURI.endsWith("/settings")) {
            url = configure(request, response);
        }
        //AUTOLOGIN
        /*  else if (requestURI.endsWith("/")){
            url = autolog(request, response);
        } */
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
            url = verifyAction(request);
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
        String verificationCode = PasswordUtil.generateCode();

        // send code email
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/email");
        HttpSession session = request.getSession(false);

        // store code in session
        session.setAttribute("emailVerificationCode", verificationCode);

        requestDispatcher.include(request, response);
    }

    private boolean verifyEmailedCode(HttpServletRequest request) {
        // get session
        HttpSession session = request.getSession(false);
        if (session == null) {
            return false;
        }

        // get sent code from session
        String emailVerificationCode = (String) session.getAttribute("emailVerificationCode");
        if (emailVerificationCode == null || emailVerificationCode.isEmpty()) {
            return false;
        }

        // get and verify user input code from request
        String code = request.getParameter("code");
        if (code == null || code.isBlank() || !code.equals(emailVerificationCode)) {
            return false;
        }

        return true;
    }

    // #endregion register

    // #region configure

    private String configure(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    // #endregion configure

    // #region signin

    private String signIn(HttpServletRequest request, HttpServletResponse response)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        String url, message;
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (UserDB.hasUserWithEmail(email)) {
            User search = UserDB.selectByEmail(email);

            if (PasswordUtil.verify(password, search.getPassword())) {
                url = "/"; //Direct to Today's workout page
                Cookie log1 = new Cookie("loginCookie", search.getEmail());
                log1.setMaxAge(60 * 60 * 24 * 3); //cookie max age is 2 days
                log1.setPath("/"); //path for cookie to be used by entire application
                response.addCookie(log1); //saves cookie to client pc
            } else {
                message = "Invalid Password.";
                url = "/"; //Refresh to current page
            }
        } else {
            message = "Invalid Email and/or Password.";
            url = "/"; //Refresh to current page
        }
        return url;
    }

    // #endregion signin

    // #region signout
    // #endregion signout

    private String autolog(HttpServletRequest request, HttpServletResponse response) {
        String url = "";
        String email;
        HttpSession session = request.getSession();
        User user = (User) request.getAttribute("user");
        if (user == null) {
            Cookie[] get = request.getCookies();
            email = CookieUtil.find(get, "loginCookie");

            if (email.equals("") || email == null) {
                url = "/index.jsp";
                return url;
            } else {
                user = UserDB.selectByEmail(email);

                if (user == null) {
                    url = "/index.jsp";
                    return url;
                }
                session.setAttribute("user", user);
            }
        }

        url = ""; //URL FOR TODAYS WORKOUT
        return url;
    }
}
