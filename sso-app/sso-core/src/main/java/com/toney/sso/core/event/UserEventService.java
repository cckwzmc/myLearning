package com.toney.sso.core.event;

import com.toney.sso.core.exception.ServiceException;
import com.toney.sso.dto.UserDTO;
import com.toney.sso.module.UserModule;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :UserEventService.java
 * @DESCRIPTION : 用户操作事件
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 22, 2013
 *       </p>
 **************************************************************** 
 */
public interface UserEventService {
	

	/**
	 * 创建用户.
	 * @param userBO
	 * @param password
	 * @return 
	 * @throws ServiceException
	 */
	UserDTO create(UserModule module) throws ServiceException;
	
}
