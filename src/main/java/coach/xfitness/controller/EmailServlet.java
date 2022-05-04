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

    public void init() {
        // SMTP server from web.xml file
        ServletContext context = getServletContext();
        smtpHost = context.getInitParameter("SMTP_HOST");
        smtpPort = context.getInitParameter("SMTP_PORT");
        smtpUsername = context.getInitParameter("SMTP_USERNAME");
        smtpPassword = context.getInitParameter("SMTP_PASSWORD");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action.equals("register")) {
            sendCode(request);
        }
    }

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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private String buildCodeEmailBody(String code, String recipientName) {
        return "<h1><strong>XFit:</strong> Email Verification</h1>" +
                "<p>Hi " + recipientName + "!</p>" +
                "<p>Enter the following code in the email verification page:</p>" +
                "<h3>" + code + "</h3>";
    }

}
