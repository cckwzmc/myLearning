/**
 * 
 */
package com.toney.sso.core.util;

import org.apache.commons.digester.Digester;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.DigestUtils;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :TokenIdUtil.java
 * @DESCRIPTION :TOKENID 的工具.
 * @AUTHOR :ly_zy_ljh
 * @VERSION :v1.0
 * @DATE :2013-3-26
 *       </p>
 **************************************************************** 
 */
public class TokenIdUtil {
	/**
	 * @param userName
	 * @param ip
	 * @param userId
	 * @param enCode
	 * @return
	 */
	public static String generateTokenId(String userName,String ip ,String userId,String enCode,long loginTimestamp){
		StringBuffer sb=new StringBuffer();
		sb.append(userName+"-");
		sb.append(userId+"-");
		sb.append(ip+"-");
		sb.append(enCode+"-");
		sb.append(loginTimestamp);
		return DigestUtils.md5DigestAsHex(DigestUtils.md5DigestAsHex(sb.toString().getBytes()).getBytes());
	}
	
	
	public static boolean compareTokenId(String tokenId,String userName,String ip ,String userId,String enCode,long loginTimestamp){
		if(StringUtils.isBlank(tokenId)){
			return false;
		}
		return tokenId.equals(generateTokenId(userName, ip, userId, enCode, loginTimestamp));
	}
}
