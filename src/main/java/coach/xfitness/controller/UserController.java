package coach.xfitness.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import coach.xfitness.business.User;
import coach.xfitness.data.UserDB;
import coach.xfitness.util.PasswordUtil;

@WebServlet(name = "UserController", urlPatterns = {"/signin", "/register", "/settings"})
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

    private String autolog(HttpServletRequest request, HttpServletResponse response){
       String url = "";
       String email;
        HttpSession session = request.getSession();
        User user = (User) request.getAttribute("user");
        if(user == null){
            Cookie[] get = request.getCookies();
            email = CookieUtil.getCookieValue(get, "loginCookie");
            
            if(email.equals("") || email == null){
                url = "/index.jsp";
                return url;
            }
            else{
                user = UserDB.select(email);

                if(user == null){
                    url = "/index.jsp";
                    return url;
                }
                session.setAttribute("user", user);
            }
        }

        url = ""; //URL FOR TODAYS WORKOUT
        return url;
    }

    private String register(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        request.setAttribute("user", user);

        String url = "";
        String message = "";
        if (UserDB.has(email)) {
            message = "This email address is already in use.";
        } else {
            try {
                String hashedPassword = PasswordUtil.generate(password);
                user.setPassword(hashedPassword);
                HttpSession session = request.getSession();
                session.setAttribute("newUser", user);
                message = "";
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                System.out.println(e);
                // url = "?"; // TODO: add error page
            } finally {
                user.setPassword("");
            }

            message = "";
            request.setAttribute("message", message);
            //before url is set to email validation, email the user with a randomly generated code and store it in the session
            EmailController.CodeEmail(request, response);
            url = "/email-verification.jsp"; //Email confirmation link
        }

        request.setAttribute("message", message);

        return url;
    }

    private String signIn(HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String url, message;
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if(UserDB.has(email)){
            User search = UserDB.select(email);
            
            if(PasswordUtil.validate(password, search.getPassword())){
                url = "/"; //Direct to Today's workout page
                Cookie log1 = new Cookie("loginCookie", search.getEmail());
                log1.setMaxAge(60*60*24*3); //cookie max age is 2 days
                log1.setPath("/"); //path for cookie to be used by entire application
                response.addCookie(log1); //saves cookie to client pc
            }
            else{
                message = "Invalid Password.";
                url = "/"; //Refresh to current page
            }
        }
        else{
            message = "Invalid Email and/or Password.";
            url = "/"; //Refresh to current page
        }
        return url;
    }
    
    private String configure(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
