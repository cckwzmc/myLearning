/**
 * 
 */
package com.toney.sso.core.service;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.toney.sso.commons.Result;
import com.toney.sso.core.login.UserLoginService;
import com.toney.sso.core.query.UserQueryService;
import com.toney.sso.dto.LoginDTO;
import com.toney.sso.dto.RegisterDTO;
import com.toney.sso.dto.UserDTO;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :SsoServiceImpl.java
 * @DESCRIPTION :SSO 的实现
 * @AUTHOR :ly_zy_ljh
 * @VERSION :v1.0
 * @DATE :2013-3-25
 *       </p>
 **************************************************************** 
 */
public class SsoServiceImpl implements SsoService {
	private static final XLogger LOGGER = XLoggerFactory.getXLogger(SsoServiceImpl.class);
	@Autowired
	UserQueryService userQueryService;
	@Autowired
	UserLoginService userLoginService;

	/* (non-Javadoc)
	 * @see com.toney.sso.core.service.SsoService#checkOnline(com.toney.sso.dto.UserDTO)
	 */
	@Override
	public Result<UserDTO> checkOnline(UserDTO userDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.toney.sso.core.service.SsoService#logout(com.toney.sso.dto.UserDTO)
	 */
	@Override
	public Result<UserDTO> logout(UserDTO userDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.toney.sso.core.service.SsoService#queryUserInfo(com.toney.sso.dto.UserDTO)
	 */
	@Override
	public Result<UserDTO> queryUserInfo(UserDTO userDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.toney.sso.core.service.SsoService#register(com.toney.sso.dto.RegisterDTO)
	 */
	@Override
	public Result<UserDTO> register(RegisterDTO registerDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.toney.sso.core.service.SsoService#login(com.toney.sso.dto.UserDTO)
	 */
	@Override
	public Result<UserDTO> login(LoginDTO loginDTO) {
		this.userLoginService.login(loginDTO);
		return null;
	}

}
