package coach.xfitness.util;

import javax.servlet.http.Cookie;

public class CookieUtil {

    public static Cookie find(Cookie[] cookies, String name) {
        if (cookies == null) {
            return null;
        }

        Cookie foundCookie = null;

        for (Cookie cookie : cookies) {
            if (name.equals(cookie.getName())) {
                foundCookie = cookie;
                break;
            }
        }

        return foundCookie;
    }

    public static String findValue(Cookie[] cookies, String name) {
        Cookie foundCookie = find(cookies, name);
        return foundCookie == null ? null : foundCookie.getValue();
    }

}