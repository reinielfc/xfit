package coach.xfitness.util;

import org.junit.Test;
import static org.junit.Assert;

public class PasswordUtilTest {

    @Test
    //test that the password will generate a random assortment of characters
    public void generatePasswordTest() {
        String regex = "(("
        
        String thePassword = PasswordUtil.generate(regex);

        assertNotNull(thePassword);
        assertTrue(thePassword.matches(regex));
    }

    @Test
    //will test the validation should the password be null
    public void validatePassword_Null() {
        String password = null;

        boolean actual = PasswordUtil.validate(password, actual);

        assertFalse(actual);
    }

    @Test
    //will test the validation should the password be an empty string
    public void validatePassword_EmptyString() {
        String password = "";

        boolean actual = PasswordUtil.validate(password, actual);

        assertFalse(actual);
    }

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
    //will test the validation should the password be missing a symbol
    public void validatePassword_Missing_OneSymbol() {
        String passwrod = "abcdefg4";

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
}