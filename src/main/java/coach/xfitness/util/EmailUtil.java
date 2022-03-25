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

public class EmailUtil{

    public static void sendMessage(String host, String port, final String username,
     final String password, String toAddr, String subject, String message) throws AddressException, MessagingException{

        //local SMTP server properties
        Properties serverProp = new Properties();
        serverProp.put("mail.smtp.host", host);
        serverProp.put("mail.smtp.port", port);
        serverProp.put("mail.smtp.auth", "true");
        serverProp.put("mail.smtp.starttls.enable", "true");

        Authenticator auth = new Authenticator(){
            public PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(username, password);
            }
        };

        Session session = Session.getInstance(serverProp, auth);
        
        Message msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress(username));
        InternetAddress[] toAddress = {new InternetAddress(toAddr)};

        //set up message details
        msg.setRecipients(Message.RecipientType.TO, toAddress);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        msg.setText(message);

        Transport.send(msg);
     }
}