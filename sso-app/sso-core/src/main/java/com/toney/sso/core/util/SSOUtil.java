/**
 * 
 */
package com.toney.sso.core.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.DigestUtils;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :SSOUtil.java
 * @DESCRIPTION :sso util
 * @AUTHOR :ly_zy_ljh
 * @VERSION :v1.0
 * @DATE :2013-3-26
 *       </p>
 **************************************************************** 
 */
public class SSOUtil {
	/**
	 * @param password
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static String encodePassword(String password) throws IllegalArgumentException{
		if(StringUtils.isBlank(password)){
			throw new IllegalArgumentException("password can not blank!!!");
		}
		String result=DigestUtils.md5DigestAsHex(DigestUtils.md5DigestAsHex(password.getBytes()).getBytes());
		return result;
	}
}
