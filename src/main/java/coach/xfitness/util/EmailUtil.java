package coach.xfitness.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil {

    /**
     * It sends an email to the specified address with the specified subject and body
     * 
     * @param SMTP_HOST The hostname of the SMTP server.
     * @param SMTP_PORT The port number of the SMTP server.
     * @param SMTP_USERNAME The username of the email account you want to send the email from.
     * @param SMTP_PASSWORD The password for the SMTP server.
     * @param toAddress The email address of the recipient.
     * @param subject The subject of the email.
     * @param body The body of the email.
     */
    public static void send(
            final String SMTP_HOST, final String SMTP_PORT,
            final String SMTP_USERNAME, final String SMTP_PASSWORD,
            String toAddress, String subject, String body)
            throws MessagingException {

        // local SMTP server properties
        Properties serverProperties = new Properties();
        serverProperties.put("mail.smtp.host", SMTP_HOST);
        serverProperties.put("mail.smtp.port", SMTP_PORT);
        serverProperties.put("mail.smtp.auth", "true");
        serverProperties.put("mail.smtp.starttls.enable", "true");

        Authenticator authenticator = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SMTP_USERNAME, SMTP_PASSWORD);
            }
        };

        Session session = Session.getInstance(serverProperties, authenticator);

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(SMTP_USERNAME));

        InternetAddress[] toInternetAddress = { new InternetAddress(toAddress) };

        // set up message details
        message.setRecipients(Message.RecipientType.TO, toInternetAddress);
        message.setSubject(subject);
        message.setSentDate(new Date());
        message.setContent(body, "text/html");

        Transport.send(message);
    }
}