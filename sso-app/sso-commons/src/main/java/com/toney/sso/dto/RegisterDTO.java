/**
 * 
 */
package com.toney.sso.dto;

import java.io.Serializable;

import com.toney.sso.commons.SSOConstants;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :RegisterDTO.java
 * @DESCRIPTION :注册 dto
 * @AUTHOR :ly_zy_ljh
 * @VERSION :v1.0
 * @DATE :2013-3-26
 *       </p>
 **************************************************************** 
 */
public class RegisterDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5832872909119744287L;

	/**
	 * 必须是邮箱，如果是手机注册可以不填写邮箱.
	 */
	private String userName;
	/**
	 * 昵称用于用户在发表言论是显示.
	 */
	private String nickName;
	/**
	 * 注册渠道
	 */
	private String channelCode;
	/**
	 * 注册密码
	 */
	private String password;
	/**
	 * 手机
	 */
	private String mobile;
	/**
	 * 注册类型，手机注册还是邮箱注册.
	 */
	private String regType;

	private String ip;

	private Integer userType;


	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip
	 *            the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * @param nickName
	 *            the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**
	 * @return the channelCode
	 */
	public String getChannelCode() {
		return channelCode;
	}

	/**
	 * @param channelCode
	 *            the channelCode to set
	 */
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile
	 *            the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the regType
	 * @see SSOConstants 注册类型，手机注册还是邮箱注册.
	 */
	public String getRegType() {
		return regType;
	}

	/**
	 * @param regType
	 *            the regType to set 注册类型，手机注册还是邮箱注册.
	 * @see SSOConstants
	 */
	public void setRegType(String regType) {
		this.regType = regType;
	}

}
