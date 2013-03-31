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
	 * TODO 1.str[]的随机读取 <br/>
	 * 2.在每个参数间插入随机值 random1=,random2=
	 * 
	 * @param userName
	 * @param ip
	 * @param userId
	 * @param enCode
	 * @param channelCode
	 * @return
	 */
	public static String generateTokenId(String userName, String ip, String userId, String enCode, long loginTimestamp, String channelCode) {
		// String[] str=new String[6];
		// str[0]="userName=" + userName;
		// str[1]="userId=" + userId;
		// str[2]="ip=" + ip;
		// str[3]="enCode=" + enCode;
		// str[4]="channelCode=" + channelCode;
		// str[5]="loginTime=" + loginTimestamp;
		StringBuffer sb = new StringBuffer();
		sb.append("userName=" + userName + "-");
		sb.append("userId=" + userId + "-");
		sb.append("ip=" + ip + "-");
		sb.append("enCode=" + enCode + "-");
		sb.append("channelCode=" + channelCode + "-");
		sb.append("loginTime=" + loginTimestamp);
		return DigestUtils.md5DigestAsHex(DigestUtils.md5DigestAsHex(sb.toString().getBytes()).getBytes());
	}

	public static boolean compareTokenId(String tokenId, String userName, String ip, String userId, String enCode, long loginTimestamp, String channelCode) {
		if (StringUtils.isBlank(tokenId)) {
			return false;
		}
		return tokenId.equals(generateTokenId(userName, ip, userId, enCode, loginTimestamp, channelCode));
	}
}
