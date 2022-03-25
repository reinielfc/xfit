package coach.xfitness.util;

import org.junit.Test;
import static org.junit.Assert;

public class PasswordUtilTest {

    @Test
    public void generatePasswordTest() {
        String regex = "(("
        
        String thePassword = PasswordUtil.generate(regex);

        assertNotNull(thePassword);
        assertTrue(thePassword.matches(regex));
    }

    @Test
    public void validatePassword_Null() {
        String password = null;

        boolean actual = PasswordUtil.validate(password, actual);

        assertFalse(actual);
    }

    @Test
    public void validatePassword_EmptyString() {
        String password = "";

        boolean actual = PasswordUtil.validate(password, actual);

        assertFalse(actual);
    }

    @Test
    public void validatePassword_Missing_OneNumber() {
        String password = "Abcdefg#";

        boolean actual = PasswordUtil.validate(password, actual);

        assertFalse(actual);
    }

    @Test
    public void validatePassword_Missing_OneUpperCase() {
        String password = "abcdefg#4";
        
        boolean actual = PasswordUtil.validate(password, actual);

        assertFalse(actual);
    }

    @Test
    public void validatePassword_Missing_OneLowerCase() {
        String password = "ABCDEFG#4";

        boolean actual = PasswordUtil.validate(password, actual);

        assertFalse(actual);
    }

    @Test
    public void validatePassword_Missing_OneSymbol() {
        String passwrod = "abcdefg4";

        boolean actual = PasswordUtil.validate(password, actual);

        assertFalse(actual);
    }

    @Test
    public void validatePassword_LengthTooShort() {
        String password = "Ab#4df";

        boolean actual = PasswordUtil.validate(password, actual);

        assertFalse(actual);
    }

    @Test
    public void validatePassword_AllRulesMet() {
        String password = "Abcdefg#4";

        boolean actual = PasswordUtil.validate(password, actual);

        assertTrue(actual);
    }
}