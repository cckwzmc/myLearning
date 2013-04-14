package com.toney.istyle.core.biz.impl;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toney.istyle.core.biz.UserRegisterManager;
import com.toney.istyle.core.exception.ManagerException;
import com.toney.istyle.core.hessian.SsoClientService;
import com.toney.istyle.core.hessian.system.UucAdminQueryService;
import com.toney.sso.commons.SSOConstants;
import com.toney.sso.core.adminservice.UucAdminService;
import com.toney.sso.dto.RegisterDTO;
import com.toney.sso.dto.UserDTO;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :UserRegisterManagerImp.java
 * @DESCRIPTION : 用户注册
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 23, 2013
 *       </p>
 **************************************************************** 
 */
@Service("userRegisterManager")
public class UserRegisterManagerImp implements UserRegisterManager {

	private static final XLogger LOGGER = XLoggerFactory.getXLogger(UserRegisterManagerImp.class);

	@Autowired
	SsoClientService ssoClientService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.toney.istyle.core.biz.UserRegisterManager#registerUser(java.lang.String, java.lang.String, java.lang.String, java.lang.Short)
	 */
	@Override
	public UserDTO registerUser(String userName, String password, String nickName, String regType, Integer userType, String mobile) throws ManagerException {
		RegisterDTO regDTO = new RegisterDTO();
		regDTO.setUserName(userName);
		regDTO.setNickName(nickName);
		regDTO.setUserType(userType);
		regDTO.setChannelCode(SSOConstants.ISTYLE_CHANNEL);
		if (regType == null || !SSOConstants.MOBILE_LOGIN.equals(regType)) {
			regDTO.setRegType(SSOConstants.EMAIL_REGISTER);
		}
		regDTO.setPassword(password);
		regDTO.setMobile(mobile);
		return ssoClientService.register(regDTO);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.toney.istyle.core.biz.UserRegisterManager#registerUser(java.lang.String, java.lang.String, java.lang.String, java.lang.Integer)
	 */
	@Override
	public UserDTO registerUser(String userName, String password, String nickName, Integer userType, String mobile) throws ManagerException {
		return this.registerUser(userName, password, nickName, SSOConstants.EMAIL_REGISTER, userType, mobile);
	}

}
