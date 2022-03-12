package coach.xfitness.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coach.xfitness.business.User;
import coach.xfitness.data.UserDB;

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
            UserDB.insert(user);
            message = "";
            request.setAttribute("message", message);
            url = "/"; // TODO: add URL
        }

        return url;
    }

    private String logIn(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
    
}
