package coach.xfitness.util;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
import java.util.regex.Pattern;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordUtil {
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();
    private static final Base64.Encoder BASE64_ENCODER = Base64.getUrlEncoder();

    public static boolean validate(String password) {
        if (password == null || password.isBlank()) {
            return false;
        }

        String[] requirements = new String[] { "\\S{8,}", "[A-Z]", "[a-z]", "[0-9]" };

        for (String requirement : requirements) {
            // if password does not match requirement regex
            if (!Pattern.compile(requirement).matcher(password).find()) {
                return false; // it is invalid
            }
        }

        return true;
    }

    public static String generateCode() {
        int code = new Random().nextInt(999999);
        return String.format("%06d", code);
    }

    /**
     * It generates a random string of 24 characters, each of which is a letter or
     * number
     * 
     * @return A random string of 24 characters.
     */
    public static String generateAccessToken() {
        return generateRandomBase64String(24);
    }

    /**
     * It generates a random string of the specified length, using the characters A-Z,
     * a-z, 0-9, and + and /
     * 
     * @param length The length of the random string to be generated.
     * @return A random string of length `length`
     */
    public static String generateRandomBase64String(int length) {
        byte[] randomBytes = new byte[length];
        SECURE_RANDOM.nextBytes(randomBytes);
        return BASE64_ENCODER.encodeToString(randomBytes);
    }

    public static String generate(String password)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        char[] chars = password.toCharArray();
        byte[] salt = getSalt().getBytes();
        int iterations = 65536; // password strength (2^16 iterations in this case)
        int keyLength = 256;

        PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, keyLength);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        byte[] hash = secretKeyFactory.generateSecret(spec).getEncoded();

        return iterations + ":" + toHex(salt) + ":" + toHex(hash);
    }

    private static String getSalt() throws NoSuchAlgorithmException {
        SecureRandom secureRandom = SecureRandom.getInstanceStrong();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return Arrays.toString(salt);
    }

    /**
     * It takes a byte array and returns a hexadecimal string representation of it
     * 
     * @param array The byte array to convert to hex.
     * @return The hexadecimal representation of the SHA-256 hash of the input string.
     */
    private static String toHex(byte[] array) throws NoSuchAlgorithmException {
        BigInteger bigInteger = new BigInteger(1, array);
        String hex = bigInteger.toString(16);

        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0) {
            return String.format("%0" + paddingLength + "d", 0) + hex;
        } else {
            return hex;
        }
    }

    public static boolean verify(String originalPassword, String storedPassword)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        String[] parts = storedPassword.split(":");
        int iterations = Integer.parseInt(parts[0]);

        byte[] salt = fromHex(parts[1]);
        byte[] hash = fromHex(parts[2]);

        PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, hash.length * 8);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        byte[] testHash = secretKeyFactory.generateSecret(spec).getEncoded();

        int diff = hash.length ^ testHash.length;

        for (int i = 0; i < hash.length && i < testHash.length; i++) {
            diff |= hash[i] ^ testHash[i];
        }

        return diff == 0;
    }

    /**
     * It takes a string of hexadecimal characters and converts it to a byte array
     * 
     * @param hex The hexadecimal string to convert to bytes.
     * @return The bytes of the hex string.
     */
    private static byte[] fromHex(String hex) {
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }

        return bytes;
    }

}
