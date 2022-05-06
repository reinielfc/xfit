package coach.xfitness.util;

import javax.servlet.http.Cookie;

public class CookieUtil {

    /**
     * If the cookies array is null, return null; otherwise, loop through the array and
     * return the first cookie whose name matches the name parameter.
     * 
     * @param cookies The array of cookies that you want to search through.
     * @param name The name of the cookie.
     * @return The first cookie with the name that matches the name parameter.
     */
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

    /**
     * If the cookie array contains a cookie with the given name, return the value of
     * that cookie, otherwise return null.
     * 
     * @param cookies The array of cookies to search through.
     * @param name The name of the cookie to find.
     * @return The value of the cookie with the given name.
     */
    public static String findValue(Cookie[] cookies, String name) {
        Cookie foundCookie = find(cookies, name);
        return foundCookie == null ? null : foundCookie.getValue();
    }

}