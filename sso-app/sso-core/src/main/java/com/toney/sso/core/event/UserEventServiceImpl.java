package com.toney.sso.core.event;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toney.sso.commons.SSOConstants;
import com.toney.sso.core.exception.ServiceException;
import com.toney.sso.core.login.UserLoginService;
import com.toney.sso.dao.UserDao;
import com.toney.sso.dto.LoginDTO;
import com.toney.sso.dto.UserDTO;
import com.toney.sso.module.UserModule;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :UserEventServiceImpl.java
 * @DESCRIPTION : TODO
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 22, 2013
 *       </p>
 **************************************************************** 
 */
@Service("userEventService")
public class UserEventServiceImpl implements UserEventService {
	private static final XLogger LOGGER = XLoggerFactory.getXLogger(UserEventServiceImpl.class);

	@Autowired
	UserDao userDao;
	@Autowired
	UserLoginService userLoginService;

	@Override
	public UserDTO create(UserModule module) throws ServiceException {
		this.userDao.insert(module);
		LoginDTO loginDTO = wrapLoginDTO(module);
		return this.userLoginService.login(loginDTO);
	}

	private LoginDTO wrapLoginDTO(UserModule module) {
		LoginDTO dto = new LoginDTO();
		if (SSOConstants.EMAIL_REGISTER.equals(module.getRegType())) {
			dto.setUserName(module.getUserName());
		}
		if (SSOConstants.MOBILE_REGISTER.equals(module.getRegType())) {
			dto.setMobile(module.getUserName());
		}
		dto.setLoginType(module.getRegType());
		dto.setChannelCode(module.getChannelCode());
		dto.setPassword(module.getPassword());
		return dto;
	}

}
