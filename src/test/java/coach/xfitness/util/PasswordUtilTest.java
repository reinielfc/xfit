package coach.xfitness.util;

import org.junit.Test;
import static org.junit.Assert.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import coach.xfitness.util.PasswordUtil;

public class PasswordUtilTest {

    @Test
    //test that the password will generate a random assortment of characters
    public void generatePasswordTest() {
        String regex = "[0-9]+[a-z0-9]{22}:{a-z0-9]{128}";

        String password = "password";
        
        String thePassword = "";
        try {
            thePassword = PasswordUtil.generate(regex);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(thePassword);
        
    }

    @Test
    //Test that the generated password is not null
    public void generatePassword_NotNull() {

        String password = "password";
        
        String thePassword = null;
        try {
            thePassword = PasswordUtil.generate(password);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        assertNotNull(thePassword);
    }

    @Test
    //Test if the generated password accepts empty string
    public void generatePassword_EmptyString() {

        String password = "";
        
        String thePassword = "";
        try {
            thePassword = PasswordUtil.generate(password);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        thePassword.isEmpty();
    }

    @Test
    //Test if it validates a hashed password
    public void validatePasswordTest() {
        String password = "Password2";

        String hash;

        boolean actual = false;
        try {
            hash = PasswordUtil.generate(password);
            actual = PasswordUtil.validate(password, hash);
        }catch (NoSuchAlgorithmException | InvalidKeySpecException e){
            e.printStackTrace();
        }

        assertTrue(actual);
    }

    @Test
    //Tests if it validates a hash generated from an empty string
    public void validatePassword_EmptyString() {
        String password = "";

        String hash;

        boolean actual = false;
        try {
            hash = PasswordUtil.generate(password);
            actual = PasswordUtil.validate(password, hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        assertTrue(actual);
    }
}
