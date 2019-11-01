package org.nuaa.wa.waelder.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Name: CookieUtil
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-10-27 22:58
 * @Version: 1.0
 */
public class CookieUtil {
    public static Cookie getCookieByName(String name, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                return cookie;
            }
        }

        return null;
    }

    public static void addCookie(String key, String value, int maxAge, HttpServletResponse response) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
