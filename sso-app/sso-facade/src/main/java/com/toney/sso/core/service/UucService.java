package com.toney.sso.core.service;

import com.toney.sso.commons.Result;
import com.toney.sso.dto.UserDTO;

/**
 *************************************************************** 
 * <p>
 * @CLASS :UucService.java
 * @DESCRIPTION : about user info query /update 
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Apr 3, 2013
 *       </p>
 **************************************************************** 
 */
public interface UucService {
	public Result<UserDTO> queryUserInfo(UserDTO userDTO);

	public Result<UserDTO> queryUserById(UserDTO userDTO);
}
