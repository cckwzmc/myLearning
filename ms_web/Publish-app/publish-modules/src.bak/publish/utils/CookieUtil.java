package com.toney.publish.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.toney.dal.dao.utils.SpringResourceLocator;

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
				// if (StringUtils.isNotEmpty(domain) && !StringUtils.equalsIgnoreCase(domain, cookies[i].getDomain()))
				// {
				// continue;
				// }
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

	/**
	 * 
	 * @Title: addCookie
	 * @Description: 增加cookie
	 * @param response
	 * @param key
	 * @param value
	 * @return void
	 * @throws
	 */
	public static void addCookie(HttpServletResponse response, String key, String value) {

		Cookie cookie = new Cookie(key, value);

		org.apache.commons.configuration.Configuration configuration = SpringResourceLocator.getConfiguration();

		String domain = configuration.getString("cookie.domain");
		String path = configuration.getString("cookie.path");

		String maxAgeStr = configuration.getString("cookie.maxAge");
		int maxAge = Integer.parseInt(maxAgeStr == null ? "-1" : maxAgeStr);
		Boolean isSecure = Boolean.valueOf(configuration.getString("cookie.isSecure"));

		cookie.setDomain(domain == null ? ".xiu.com" : domain);
		cookie.setPath(path == null ? "/" : path);
		cookie.setMaxAge(maxAge);
		cookie.setSecure(isSecure);

		response.addCookie(cookie);
	}
	
	public static void deleteCookie(HttpServletResponse response, String key){
		Cookie cookie = new Cookie(key, null);

		org.apache.commons.configuration.Configuration configuration = SpringResourceLocator.getConfiguration();
		String domain = configuration.getString("cookie.domain");
		String path = configuration.getString("cookie.path");
		
		cookie.setDomain(domain == null ? ".xiu.com" : domain);
		cookie.setPath(path == null ? "/" : path);
		cookie.setMaxAge(0);
		
		response.addCookie(cookie);
	}
}
