/**
 * 
 */
package com.toney.sso.core.einteface;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.toney.sso.commons.Result;
import com.toney.sso.commons.SSOConstants;
import com.toney.sso.core.constants.ErrConstants;
import com.toney.sso.core.exception.ServiceException;
import com.toney.sso.core.login.UserLoginService;
import com.toney.sso.core.query.UserQueryService;
import com.toney.sso.core.register.UserRegisterService;
import com.toney.sso.core.service.SsoService;
import com.toney.sso.core.util.ValidateUtil;
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
	@Autowired
	UserRegisterService userRegisterService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.toney.sso.core.service.SsoService#checkOnline(com.toney.sso.dto.UserDTO
	 * )
	 */
	@Override
	public Result<UserDTO> checkOnline(UserDTO userDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.toney.sso.core.service.SsoService#logout(com.toney.sso.dto.UserDTO)
	 */
	@Override
	public Result<UserDTO> logout(UserDTO userDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.toney.sso.core.service.SsoService#queryUserInfo(com.toney.sso.dto
	 * .UserDTO)
	 */
	@Override
	public Result<UserDTO> queryUserInfo(UserDTO userDTO) {
		Result<UserDTO> result=validateQueryUser(userDTO);
		return null;
	}

	private Result<UserDTO> validateQueryUser(UserDTO userDTO) {
		Result<UserDTO> result=new Result<UserDTO>();
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.toney.sso.core.service.SsoService#register(com.toney.sso.dto.RegisterDTO
	 * )
	 */
	@Override
	public Result<UserDTO> register(RegisterDTO registerDTO) {
		Result<UserDTO> result = validateRegisterParams(registerDTO);
		if (result.getIsSuccess() == null || !result.getIsSuccess()) {
			return result;
		}
		try {
			UserDTO dto = this.userRegisterService.register(registerDTO);
			List<UserDTO> list = new ArrayList<UserDTO>();
			list.add(dto);
			result.setResult(list);
		} catch (ServiceException e) {
			result.setIsSuccess(false);
			result.setErrCode(ErrConstants.GENERAL_ERR_CODE);
		}
		return result;
	}

	private Result<UserDTO> validateRegisterParams(RegisterDTO registerDTO) {
		Result<UserDTO> result = new Result<UserDTO>();
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.toney.sso.core.service.SsoService#login(com.toney.sso.dto.UserDTO)
	 */
	@Override
	public Result<UserDTO> login(LoginDTO loginDTO) {
		Result<UserDTO> result = validateLoginParams(loginDTO);
		if (result.getIsSuccess() == null || !result.getIsSuccess()) {
			return result;
		}
		try {
			UserDTO dto = new UserDTO();
			dto = this.userLoginService.login(loginDTO);
			List<UserDTO> list = new ArrayList<UserDTO>();
			list.add(dto);
			result.setResult(list);
		} catch (ServiceException e) {
			result.setIsSuccess(false);
			result.setErrCode(ErrConstants.GENERAL_ERR_CODE);
		}
		return result;
	}

	/**
	 * 对用户登录进行参数校验
	 * 
	 * @param loginDTO
	 * @return
	 */
	private Result<UserDTO> validateLoginParams(LoginDTO loginDTO) {
		Result<UserDTO> result = new Result<UserDTO>();
		if (SSOConstants.EMAIL_LOGIN.equals(loginDTO.getLoginType())) {
			ValidateUtil.validateEmail(loginDTO.getUserName());
		} else if (SSOConstants.MOBILE_LOGIN.equals(loginDTO.getLoginType())) {

		}
		result.setIsSuccess(true);
		return result;
	}

	@Override
	public Result<UserDTO> queryUserById(UserDTO userDTO) {
		// TODO Auto-generated method stub
		return null;
	}

}
