package coach.xfitness.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coach.xfitness.business.User;
import coach.xfitness.data.UserDB;
import coach.xfitness.util.PasswordUtil;

@WebServlet(name = "UserController", urlPatterns = {"/u/*"})
public class UserController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String url = "";
        if (requestURI.endsWith("/signup")) {
            url = signUp(request, response);
        } else if (requestURI.endsWith("/login")) {
            url = logIn(request, response);
        }
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    private String signUp(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        request.setAttribute("user", user);

        String url;
        String message;
        if (UserDB.hasUser(email)) {
            message = "This email address is already in use.";
            request.setAttribute("message", message);
            url = "/"; // TODO: add URL
        } else {
            try {
                String hashedPassword = PasswordUtil.generate(password);
                user.setPassword(hashedPassword);
                UserDB.insert(user);
            } catch (NoSuchAlgorithmException e) {
                System.out.println(e); // TODO: Add error message
            } catch (InvalidKeySpecException e) {
                System.out.println(e); // TODO: Add error message
            } finally {
                user.setPassword("");
            }

            message = "";
            request.setAttribute("message", message);
            url = "/"; // TODO: add URL
        }

        return url;
    }

    private String logIn(HttpServletRequest request, HttpServletResponse response) {
        String url, message;
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if(UserDB.hasUser(email)){
            User search = UserDB.selectUser(email);
            
            if(true/*PasswordUtil.validate(password, search.getPassword())*/){
                url = "/"; //Direct to Todays workout page
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
    
}
