package com.toney.core.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.toney.dal.model.BaseObject;

/**
 *************************************************************** 
 * <p>
 * 
 * @DESCRIPTION : 用于存放用户登录验证的相关信息
 * @AUTHOR : xiu@xiu.com
 * @DATE :2012-4-3 上午11:44:16
 *       </p>
 **************************************************************** 
 */
public class UserAuthInfo extends BaseObject {
	private static final long serialVersionUID = -1542689354109286596L;

	private String ssoTokenId; // SSO证书id
	private Long ssoUserId; // SSO用户id
	private Long ssoCusId; // SSO客户id
	private String ssoCreateTime; // SSO用户创建时间

	private String loginName; // 登陆名
	private String loginFlag; // 是否是代理登录,0:代表是客服代理客户登录，1：正常登录
	private String clientIpAddress; // 客户访问IP地址

	public String getSsoTokenId() {
		return ssoTokenId;
	}

	public void setSsoTokenId(String ssoTokenId) {
		this.ssoTokenId = ssoTokenId;
	}

	public Long getSsoUserId() {
		return ssoUserId;
	}

	public void setSsoUserId(Long ssoUserId) {
		this.ssoUserId = ssoUserId;
	}

	public Long getSsoCusId() {
		return ssoCusId;
	}

	public void setSsoCusId(Long ssoCusId) {
		this.ssoCusId = ssoCusId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getSsoCreateTime() {
		return ssoCreateTime;
	}

	public void setSsoCreateTime(String ssoCreateTime) {
		this.ssoCreateTime = ssoCreateTime;
	}

	public String getLoginFlag() {
		return loginFlag;
	}

	public void setLoginFlag(String loginFlag) {
		this.loginFlag = loginFlag;
	}

	public String getClientIpAddress() {
		return clientIpAddress;
	}

	public void setClientIpAddress(String clientIpAddress) {
		this.clientIpAddress = clientIpAddress;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clientIpAddress == null) ? 0 : clientIpAddress.hashCode());
		result = prime * result + ((loginFlag == null) ? 0 : loginFlag.hashCode());
		result = prime * result + ((loginName == null) ? 0 : loginName.hashCode());
		result = prime * result + ((ssoCreateTime == null) ? 0 : ssoCreateTime.hashCode());
		result = prime * result + ((ssoCusId == null) ? 0 : ssoCusId.hashCode());
		result = prime * result + ((ssoTokenId == null) ? 0 : ssoTokenId.hashCode());
		result = prime * result + ((ssoUserId == null) ? 0 : ssoUserId.hashCode());
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
		if (clientIpAddress == null) {
			if (other.clientIpAddress != null)
				return false;
		} else if (!clientIpAddress.equals(other.clientIpAddress))
			return false;
		if (loginFlag == null) {
			if (other.loginFlag != null)
				return false;
		} else if (!loginFlag.equals(other.loginFlag))
			return false;
		if (loginName == null) {
			if (other.loginName != null)
				return false;
		} else if (!loginName.equals(other.loginName))
			return false;
		if (ssoCreateTime == null) {
			if (other.ssoCreateTime != null)
				return false;
		} else if (!ssoCreateTime.equals(other.ssoCreateTime))
			return false;
		if (ssoCusId == null) {
			if (other.ssoCusId != null)
				return false;
		} else if (!ssoCusId.equals(other.ssoCusId))
			return false;
		if (ssoTokenId == null) {
			if (other.ssoTokenId != null)
				return false;
		} else if (!ssoTokenId.equals(other.ssoTokenId))
			return false;
		if (ssoUserId == null) {
			if (other.ssoUserId != null)
				return false;
		} else if (!ssoUserId.equals(other.ssoUserId))
			return false;
		return true;
	}

}
