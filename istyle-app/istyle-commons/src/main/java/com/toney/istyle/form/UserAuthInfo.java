package com.toney.istyle.form;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.toney.istyle.commons.BaseObject;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean equals(Object object) {
		// TODO Auto-generated method stub
		return false;
	}

}
