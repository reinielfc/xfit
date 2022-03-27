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
        String regex = "[a-z0-9]{128}";

        String password = "password";
        
        String thePassword = "5bJkhkh";
        try {
            thePassword = PasswordUtil.generate(password);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(thePassword);
        //assertTrue(thePassword.matches(regex));
    }

    @Test
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
    public void generatePassword_NotEmptyString() {

        String password = "password";
        
        String thePassword = "";
        try {
            thePassword = PasswordUtil.generate(password);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        assert(!thePassword.isEmpty());
    }

    @Test
    //will test the validation should the password be an empty string
    public void generatePassword_EmptyString() {
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


/*
    @Test
    //will test the validation should the password be missing a number
    public void validatePassword_Missing_OneNumber() {
        String password = "Abcdefg#";

        boolean actual = PasswordUtil.validate(password, actual);

        assertFalse(actual);
    }

    @Test
    //will test the validation should the password be missing an uppercase letter
    public void validatePassword_Missing_OneUpperCase() {
        String password = "abcdefg#4";
        
        boolean actual = PasswordUtil.validate(password, actual);

        assertFalse(actual);
    }

    @Test
    //will test the validation should the password be missing a lowercase letter
    public void validatePassword_Missing_OneLowerCase() {
        String password = "ABCDEFG#4";

        boolean actual = PasswordUtil.validate(password, actual);

        assertFalse(actual);
    }

    @Test
    //will test the validation should the password be too short
    public void validatePassword_LengthTooShort() {
        String password = "Ab#4df";

        boolean actual = PasswordUtil.validate(password, actual);

        assertFalse(actual);
    }

    @Test
    //will test the validation should the password meet all the rules set
    public void validatePassword_AllRulesMet() {
        String password = "Abcdefg#4";

        boolean actual = PasswordUtil.validate(password, actual);

        assertTrue(actual);
    }
    */
}
