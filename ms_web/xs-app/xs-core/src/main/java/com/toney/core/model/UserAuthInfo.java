package com.toney.core.model;

import org.apache.commons.lang.builder.ToStringBuilder;

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

}
