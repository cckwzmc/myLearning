package com.toney.istyle.bo;

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

	private UserBO userBo;
	private String tokenId;

	public UserBO getUserBo() {
		return userBo;
	}

	public void setUserBo(UserBO userBo) {
		this.userBo = userBo;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	@Override
	public String toString() {
		return "UserLoginedBo [userBo=" + userBo + ", tokenId=" + tokenId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tokenId == null) ? 0 : tokenId.hashCode());
		result = prime * result + ((userBo == null) ? 0 : userBo.hashCode());
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
		UserLoginedBO other = (UserLoginedBO) obj;
		if (tokenId == null) {
			if (other.tokenId != null)
				return false;
		} else if (!tokenId.equals(other.tokenId))
			return false;
		if (userBo == null) {
			if (other.userBo != null)
				return false;
		} else if (!userBo.equals(other.userBo))
			return false;
		return true;
	}

}
