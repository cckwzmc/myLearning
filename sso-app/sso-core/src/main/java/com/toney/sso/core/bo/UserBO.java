/**
 * 
 */
package com.toney.sso.core.bo;

import com.toney.sso.module.BaseObject;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :UserBO.java
 * @DESCRIPTION :user biz object
 * @AUTHOR :ly_zy_ljh
 * @VERSION :v1.0
 * @DATE :2013-3-26
 *       </p>
 **************************************************************** 
 */
public class UserBO extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8242599067835649326L;

	private Long id;
	private String userName;
	private String nickName;
	private String regType;
	private String mobile;
	private String email;
	private Integer status;
	/**
	 * 管理员或普通用户或保留用户.
	 */
	private String userType;
	private long regDate;
	private long loginDate;
	private long lastSessionDate;
	private String ip;

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
	 * @return the lastSessionDate
	 */
	public long getLastSessionDate() {
		return lastSessionDate;
	}

	/**
	 * @param lastSessionDate
	 *            the lastSessionDate to set
	 */
	public void setLastSessionDate(long lastSessionDate) {
		this.lastSessionDate = lastSessionDate;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
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
	 * @return the regType
	 */
	public String getRegType() {
		return regType;
	}

	/**
	 * @param regType
	 *            the regType to set
	 */
	public void setRegType(String regType) {
		this.regType = regType;
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * @param userType
	 *            the userType to set
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

	/**
	 * @return the regDate
	 */
	public long getRegDate() {
		return regDate;
	}

	/**
	 * @param regDate
	 *            the regDate to set
	 */
	public void setRegDate(long regDate) {
		this.regDate = regDate;
	}

	/**
	 * @return the loginDate
	 */
	public long getLoginDate() {
		return loginDate;
	}

	/**
	 * @param loginDate
	 *            the loginDate to set
	 */
	public void setLoginDate(long loginDate) {
		this.loginDate = loginDate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.toney.sso.module.BaseObject#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.toney.sso.module.BaseObject#hashCode()
	 */
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.toney.sso.module.BaseObject#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object object) {
		// TODO Auto-generated method stub
		return false;
	}

}
