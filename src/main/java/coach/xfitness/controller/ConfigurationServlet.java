package coach.xfitness.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import coach.xfitness.business.User;

@WebServlet(name = "ConfigurationController", urlPatterns = {"/c/*"})
public class ConfigurationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
    
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            if (name != null && !name.isEmpty()) {
                user.setName(name);

            }
            if (email != null && !email.isEmpty() && email.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")) {
                user.setEmail(email);
            }
            if (password != null && !password.isEmpty()) {
                user.setPassword(password);

            }

            //boolean success = UserDB.updateUser( user);
            //if (success) {
            //    session.setAttribute("user", user);
            //} else {
            //
            //}
    } 
    private boolean isDouble(String d) {
        try {
            Double.parseDouble(d);
            return true;
        }
        catch (Exception e) {
        return false;
        }
    }
}
