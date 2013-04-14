package com.toney.istyle.core.biz;

import com.toney.istyle.core.exception.ManagerException;
import com.toney.sso.commons.SSOConstants;
import com.toney.sso.core.adminservice.UucAdminService;
import com.toney.sso.dto.UserDTO;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :UserRegisterManager.java
 * @DESCRIPTION : 用户注册服务类
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 23, 2013
 *       </p>
 **************************************************************** 
 */
public interface UserRegisterManager {



	/**
	 * @param userName
	 * @param password
	 * @param nickName
	 * @param regType 注册方式：手机 还是 邮件 @see {@link SSOConstants}
	 * @param userType 注册类型：0:系统保留用户，1：系统用户 2：普通用户. @see {@link UucAdminService} 
	 * @throws ManagerException
	 */

	UserDTO registerUser(String userName, String password, String nickName, String regType, Integer userType, String mobile) throws ManagerException;


	UserDTO registerUser(String userName, String password, String nickName, Integer userType, String mobile) throws ManagerException;

}
