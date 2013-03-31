package com.toney.sso.core.register;

import com.toney.sso.core.exception.ServiceException;
import com.toney.sso.dto.RegisterDTO;
import com.toney.sso.dto.UserDTO;

/**
 *************************************************************** 
 * <p>
 * @CLASS :UserRegisterService.java
 * @DESCRIPTION : user register service layer.
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 30, 2013
 *       </p>
 **************************************************************** 
 */
public interface UserRegisterService {

	/**
	 * 用户注册
	 * @param dto
	 * @return 
	 * @throws ServiceException
	 */
	UserDTO register(RegisterDTO dto) throws ServiceException;

}
