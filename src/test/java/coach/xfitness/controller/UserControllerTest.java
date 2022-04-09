package coach.xfitness.controller;

import static org.junit.Assert.assertEquals;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static junit.framework.Assert.*;

import org.junit.Test;

import coach.xfitness.business.User;
import coach.xfitness.data.UserDB;
import coach.xfitness.util.PasswordUtil;

public class UserControllerTest {
    @Test
    //
    public void testRegisterMainFlow() {
        String error = "This email address is already in use.";
        String message = "";
        User user1 = new User();
        user1.setName("John");
        user1.setEmail("JohnSmith@112.com");
        user1.setPassword("Apples3657");
        UserDB.insert(user1);
        User user2 = new User();
        user2.setName("Reiniel");
        user2.setEmail("JohnSmith@112.com");
        user2.setPassword("reinielisasleep");

        if(UserDB.hasUser(user2.getEmail())){
            message = "This email address is already in use.";
        }
        else{    
            try {
                String hashedpassword = PasswordUtil.generate(user2.getPassword());
                user2.setPassword(hashedpassword);
                message = "";
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                e.printStackTrace();
            }
        }

        assertEquals(message, error);
        UserDB.deleteUser(user1.getEmail());
    }
    @Test
    //test if method returns String value of '/'
    public void testReturnSignUp(){
        String message = "";
        String url = "";

        User user1 = new User();
        user1.setName("John");
        user1.setEmail("JohnSmith@112.com");
        user1.setPassword("Apples3657");
        UserDB.insert(user1);

        if(UserDB.hasUser(user1.getEmail())) {
            User search = UserDB.selectUser(user1.getEmail());

            try {
                 String hashedPassword = PasswordUtil.generate(user1.getPassword());
                 user1.setPassword(hashedPassword);
                 message = "";
                 if(PasswordUtil.validate(hashedPassword, search.getPassword())){
                     url = "/";
                     Cookie log1 = new Cookie("loginCookie", search.getEmail());
                     log1.setMaxAge(60*60*24*3);
                     log1.setPath("/");
                 }
                 else {
                     message = "Invalid Password.";
                     url = "/";
                 }
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        else {
            message = "Invalid Email and/or Password";
            url = "/";
        }
        assertEquals(message, url);
        UserDB.deleteUser(user1.getEmail());
    }
}
