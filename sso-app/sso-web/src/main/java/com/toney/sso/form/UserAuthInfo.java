package com.toney.sso.form;

import com.toney.sso.commons.BaseObject;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :UserAuthInfo.java
 * @DESCRIPTION : 用于存放用户登录验证的相关信息
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 21, 2013
 *       </p>
 **************************************************************** 
 */
public class UserAuthInfo extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4031027356817233354L;

	private Long userId;
	private String tokenId;
	private String userName;
	private String nickName;

	public UserAuthInfo(Long userId, String tokenId, String userName, String nickName) {
		this.userId = userId;
		this.tokenId = tokenId;
		this.userName = userName;
		this.nickName = nickName;
	}

	public String getUserName() {
		return userName;
	}

	public String getNickName() {
		return nickName;
	}

	public String getTokenId() {
		return tokenId;
	}

	public Long getUserId() {
		return userId;
	}

	@Override
	public String toString() {
		return "UserAuthInfo [userId=" + userId + ", tokenId=" + tokenId + ", userName=" + userName + ", nickName=" + nickName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nickName == null) ? 0 : nickName.hashCode());
		result = prime * result + ((tokenId == null) ? 0 : tokenId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserAuthInfo other = (UserAuthInfo) obj;
		if (nickName == null) {
			if (other.nickName != null)
				return false;
		} else if (!nickName.equals(other.nickName))
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
