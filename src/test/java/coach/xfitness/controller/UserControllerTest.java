package coach.xfitness.controller;

import static org.junit.Assert.assertEquals;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import coach.xfitness.business.User;
import coach.xfitness.data.UserDB;
import coach.xfitness.util.PasswordUtil;
import coach.xfitness.controller.UserController;

public class UserControllerTest {

    private void securePassword(User user) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String password = PasswordUtil.generate(user.getPassword());
        user.setPassword(password);
    }

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

        if(UserDB.hasUserWithEmail(user2.getEmail())){
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
        UserDB.deleteByEmail(user1.getEmail());
    }
    @Test
    //test if method returns String value of '/'
    public void testReturnSignUp(){

    }

    @Test
    public void testRegister() {
        HttpServletRequest request;
        HttpServletResponse response;

        User user = new User();

        user.makeFromRequest(request);

        String action = request.getParameter("action");
        String url = "/register.jsp";
    }

    @Test
    public void testDoRegisterActionFail1() {
        String actual= "";
        String expected = "An error has occured. Please try again later.";
        try {

            throw new NoSuchAlgorithmException();
        } catch (NoSuchAlgorithmException e) {
            //TODO: handle exception
            actual = "An error has occured. Please try again later.";
        }

        assertEquals(actual, expected);
        

    }

    @Test
    public void testDoRegisterActionFail2() {
        String actual= "";
        String expected = "An error has occured. Please try again later.";
        try {        
            throw new InvalidKeySpecException();
        } catch (InvalidKeySpecException e) {
            //TODO: handle exception
            actual = "An error has occured. Please try again later.";
        }

        assertEquals(actual, expected);
        

    }
}
