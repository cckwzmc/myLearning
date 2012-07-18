package com.toney.mvc.web.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

/**
 *************************************************************** 
 * <p>
 * 
 * @DESCRIPTION :
 * @AUTHOR : xiu@xiu.com
 * @DATE :2012-4-3 下午12:24:47
 *       </p>
 **************************************************************** 
 */
public final class CookieUtil {
    /**
     * 获取Cookie的值
     */
    public static String getCookieValue(HttpServletRequest request, String domain, String name) {
        Cookie cookie = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i] == null) {
                    continue;
                }
//                if (StringUtils.isNotEmpty(domain) && !StringUtils.equalsIgnoreCase(domain, cookies[i].getDomain())) {
//                    continue;
//                }
                if (StringUtils.equals(name, cookies[i].getName())) {
                    cookie = cookies[i];
                    
                    break;
                }
            }
        }
        if (cookie == null) {
            return null;
        }
        String value = cookie.getValue();
        try {
            return java.net.URLDecoder.decode(value, "utf-8");
        } catch (Exception e) {
        }
        return value;
    }
}
