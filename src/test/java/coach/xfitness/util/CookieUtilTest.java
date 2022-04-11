package coach.xfitness.util;

import coach.xfitness.util.CookieUtil;
import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.http.Cookie;

import org.junit.Test;

public class CookieUtilTest {
    private Cookie[] cookies;
    private String cookieName = "";
    private String cookieValue = "";

    @Test
    public void CookieNotNullTest() {
        if (cookies != null) {

            for(Cookie cookie: cookies) {
                
                if (cookieName.equals(cookie.getName())) {
                    cookieValue = cookie.getValue();
                    assertNotNull(cookies);
    
                }
            }
        }
    }

    @Test
    public void CookieReturnTest() {
        boolean actual = false;

            CookieUtil.find(cookies, cookieName);
        assertFalse(cookieValue, actual);
    }
}
