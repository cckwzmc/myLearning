/**
 * 
 */
package com.toney.sso.core.service;

import com.toney.sso.commons.Result;
import com.toney.sso.dto.LoginDTO;
import com.toney.sso.dto.RegisterDTO;
import com.toney.sso.dto.UserDTO;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :SsoService.java
 * @DESCRIPTION :单点登录服务
 * @AUTHOR :ly_zy_ljh
 * @VERSION :v1.0
 * @DATE :2013-3-25
 *       </p>
 **************************************************************** 
 */
public interface SsoService {
	public Result<UserDTO> checkOnline(UserDTO userDTO);

	public Result<UserDTO> logout(UserDTO userDTO);

	public Result<UserDTO> register(RegisterDTO registerDTO);

	public Result<UserDTO> login(LoginDTO loginDTO);

}
