package coach.xfitness.util;

import org.junit.jupiter.api.Test;

public class PasswordUtilTest {
    @Test
    void testGenerate() {
        String passwordHash;

        try {
            passwordHash = PasswordUtil.generate("sesame");
            Boolean isValid = PasswordUtil.validate("sesame", passwordHash);
            System.out.println(isValid);
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }

    @Test
    void testValidate() {

    }
}
