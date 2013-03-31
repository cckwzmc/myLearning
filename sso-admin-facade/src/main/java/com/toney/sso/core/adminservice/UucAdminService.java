/**
 * 
 */
package com.toney.sso.core.adminservice;

import com.toney.sso.commons.Result;
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
public interface UucAdminService {
	/**
	 * 注册类型：0:系统保留用户，1：系统用户 2：普通用户.
	 */
	public static final int REG_TYPE_0 = 0;
	/**
	 * 注册类型：0:系统保留用户，1：系统用户 2：普通用户.
	 */
	public static final int REG_TYPE_1 = 1;
	/**
	 * 注册类型：0:系统保留用户，1：系统用户 2：普通用户.
	 */
	public static final int REG_TYPE_2 = 2;

	public Result<UserDTO> queryUserByUserType(Integer userType);

}
