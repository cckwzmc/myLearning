/**
 * 
 */
package com.toney.sso.dto;

import java.io.Serializable;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :LoginDTO.java
 * @DESCRIPTION :Login DTO
 * @AUTHOR :ly_zy_ljh
 * @VERSION :v1.0
 * @DATE :2013-3-26
 *       </p>
 **************************************************************** 
 */
public class LoginDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3975664369600679735L;
	
	private String userName;
	private String mobile;
	private String loginType;
	private String ip;
	private String password;
	
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * @return the loginType
	 */
	public String getLoginType() {
		return loginType;
	}
	/**
	 * @param loginType the loginType to set
	 */
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}
	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
}
