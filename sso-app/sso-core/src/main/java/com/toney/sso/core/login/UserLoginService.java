package com.toney.sso.core.login;

import com.toney.sso.core.bo.UserLoginedBO;
import com.toney.sso.core.exception.ServiceException;
import com.toney.sso.dto.LoginDTO;
import com.toney.sso.dto.UserDTO;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :UserLoginService.java
 * @DESCRIPTION : EhCache实现session持久化
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 21, 2013
 *       </p>
 **************************************************************** 
 */
public interface UserLoginService {

	/**
	 * 用户登录
	 * 
	 * @param loginDTO
	 */
	UserDTO login(LoginDTO loginDTO) throws ServiceException;

	/**
	 * 使用EhCache实现Session,
	 * 
	 * @param userName
	 * @param tokenId
	 * @param ip
	 * @param loginType
	 * @return
	 * @throws ServiceException
	 */

	UserLoginedBO checkUserLogined(String userName, String tokenId, String ip, String loginType) throws ServiceException;

}
