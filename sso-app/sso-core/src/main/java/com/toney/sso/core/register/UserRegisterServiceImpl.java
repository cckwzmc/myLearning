package com.toney.sso.core.register;

import java.util.Date;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toney.sso.commons.SSOConstants;
import com.toney.sso.core.constants.Constants;
import com.toney.sso.core.event.UserEventService;
import com.toney.sso.core.exception.ServiceException;
import com.toney.sso.core.util.SSOUtil;
import com.toney.sso.dto.RegisterDTO;
import com.toney.sso.dto.UserDTO;
import com.toney.sso.module.UserModule;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :UserRegisterServiceImpl.java
 * @DESCRIPTION : user register service implements
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 30, 2013
 *       </p>
 **************************************************************** 
 */
@Service("userRegisterService")
public class UserRegisterServiceImpl implements UserRegisterService {
	private static final XLogger LOGGER = XLoggerFactory.getXLogger(UserRegisterServiceImpl.class);
	@Autowired
	UserEventService userEventService;

	@Override
	public UserDTO register(RegisterDTO dto) throws ServiceException {
		UserModule module=wrapUserModule(dto);
		return this.userEventService.create(module);
	}

	private UserModule wrapUserModule(RegisterDTO dto) {
		UserModule module=new UserModule();
		if(SSOConstants.EMAIL_REGISTER.equals(dto.getRegType())){
			module.setUserName(dto.getUserName());
		}
		module.setChannelCode(dto.getChannelCode());
		module.setCreateDate(new Date());
		module.setMobile(dto.getMobile());
		module.setNickName(dto.getNickName());
		module.setPassword(SSOUtil.encodePassword(dto.getPassword()));
		module.setRegType(dto.getRegType());
		module.setStatus(Constants.Normal_STATUS);
		module.setUserType(dto.getUserType());
		return module;
	}
}
