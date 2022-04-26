package coach.xfitness.controller;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import coach.xfitness.business.User;
import coach.xfitness.util.EmailUtil;

@WebServlet(name = "EmailController", urlPatterns = { "/email" })
public class EmailServlet extends HttpServlet {

    private static String smtpHost;
    private static String smtpPort;
    private static String smtpUsername;
    private static String smtpPassword;

    /**
    * Called when the servlet is first loaded. It reads the
    * SMTP server settings from the web.xml file and stores them in the variables
    * smtpHost, smtpPort, smtpUsername, and smtpPassword
    */
    public void init() {
        // SMTP server from web.xml file
        ServletContext context = getServletContext();
        smtpHost = context.getInitParameter("SMTP_HOST");
        smtpPort = context.getInitParameter("SMTP_PORT");
        smtpUsername = context.getInitParameter("SMTP_USERNAME");
        smtpPassword = context.getInitParameter("SMTP_PASSWORD");
    }

    /**
    * If the action is register or resend, send the code
    * 
    * @param request The request object
    * @param response The response object
    */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action.equals("register") || action.equals("resend")) {
            sendCode(request);
        }
    }

    /**
    * Send an email to the user with the code that was generated in the previous
    * step. The session is needed to get the user and the code.
    * 
    * @param request the request object
    */
    private void sendCode(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        // get user stored in session
        User recipient = (User) session.getAttribute("recipient");

        // get code stored in session
        String code = (String) session.getAttribute("emailVerificationCode");

        // prepare email
        String toAddress = recipient.getEmail();
        String subject = "XFit: Email Verification";
        String body = buildCodeEmailBody(code, recipient.getName());

        // send email
        try {
            EmailUtil.send(
                    smtpHost, smtpPort, smtpUsername, smtpPassword,
                    toAddress, subject, body);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    /**
    * Builds the body of the email that will be sent to the user
    * 
    * @param code The code that will be sent to the user.
    * @param recipientName The name of the recipient of the email.
    * @return A string that contains the body of the email.
    */
    private String buildCodeEmailBody(String code, String recipientName) {
        return new StringBuilder()
                .append("<h1><strong>XFit:</strong> Email Verification</h1>")
                .append("<p>Hi ").append(recipientName).append("!</p>")
                .append("<p>Enter the following code in the email verification page:</p>")
                .append("<h3>").append(code).append("</h3>")
                .toString();
    }

}
