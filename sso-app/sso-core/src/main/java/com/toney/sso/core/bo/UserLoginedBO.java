package com.toney.sso.core.bo;

import java.io.Serializable;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :UserLoginedBo.java
 * @DESCRIPTION : 用户登录成功后的信息
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 21, 2013
 *       </p>
 **************************************************************** 
 */
public class UserLoginedBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4037646077174701144L;

	private String tokenId;
	private String userName;
	private String ip;
	private Long userId;
	private long loginDate;
	private Short userType;
	private long lastSessionDate;

	public Short getUserType() {
		return userType;
	}

	public void setUserType(Short userType) {
		this.userType = userType;
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
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
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

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserLoginedBO [tokenId=" + tokenId + ", userName=" + userName + ", ip=" + ip + ", userId=" + userId + ", loginDate=" + loginDate + ", lastSessionDate="
				+ lastSessionDate + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + (int) (lastSessionDate ^ (lastSessionDate >>> 32));
		result = prime * result + (int) (loginDate ^ (loginDate >>> 32));
		result = prime * result + ((tokenId == null) ? 0 : tokenId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserLoginedBO other = (UserLoginedBO) obj;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (lastSessionDate != other.lastSessionDate)
			return false;
		if (loginDate != other.loginDate)
			return false;
		if (tokenId == null) {
			if (other.tokenId != null)
				return false;
		} else if (!tokenId.equals(other.tokenId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

}
