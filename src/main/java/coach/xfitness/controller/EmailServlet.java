package coach.xfitness.controller;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import coach.xfitness.business.User;
import coach.xfitness.data.UserDB;
import coach.xfitness.util.EmailUtil;
@WebServlet(name = "EmailController", urlPatterns = {"/validate"})
public class EmailServlet extends HttpServlet {

    private static String host;
    private static String port;
    private static String user;
    private static String pass;

    public void init(){
        //SMTP server from web.xml file
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        user = context.getInitParameter("user");
        pass = context.getInitParameter("pass");
    }

    //static method to be called when email code must be sent to user
    public static String CodeEmail(HttpServletRequest request, HttpServletResponse response){
        Random emailcode = new Random();
        HttpSession session = request.getSession();
        //returns user object stored in session from register
        User recip = (User) request.getAttribute("newUser");
        String subject = "XFit Email-Verification";
        String to = recip.getEmail();
        String code = Integer.toString(emailcode.nextInt(999999- 100000) + 100000);
        //store code in the session
        session.setAttribute("EmailCode", code);
        String message = "";

        try{
            //send email to user
            EmailUtil.sendMessage(host, port, user, pass, to, subject, code);
            message = "Email was sent to" + recip.getEmail() + ".\nPlease check email to enter code for email verification";
        } catch (Exception ex) {
            message = "Email failed to send properly.  Retry to send the message.";
        }
        return message;
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException{
        HttpSession session = request.getSession();
        String message = "";
        String url = "";
        String action = request.getParameter("action");
        User newUser = (User) session.getAttribute("newUser");
        switch(action){
            //case that user wants to submit code they received as an email
            case "code":
                //get code stored in session
                String code = (String) session.getAttribute("EmailCode");
                //get code from what user submitted
                String compare = request.getParameter("code");
                if(code.equals(compare)){
                    message = "";
                    UserDB.insert(newUser);
                    url = "/"; //TO IMPLEMENT, direct to equipment selection
                }
                else{
                    message = "Code does not match.";
                    url = "/email-verification.jsp";
                }
                break;
            //case that user wants to resend a new code to their email
            case "send":
                CodeEmail(request, response);
                message = "A new code has been sent to " + newUser.getEmail();
                url = "/email-verification.jsp";
                break;
            //case that user wants to retype a new email to receieve a code
            case "email":
                String newemail = request.getParameter("email");
                newUser.setEmail(newemail);
                session.setAttribute("newUser",newUser);
                CodeEmail(request, response);
                message ="A new code has been sent to " + newUser.getEmail();
                url = "/email-verification.jsp";
                break;
        }
        
        request.setAttribute("message", message);
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }
}
