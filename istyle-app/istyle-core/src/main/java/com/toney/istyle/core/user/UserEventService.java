package com.toney.istyle.core.user;

import com.toney.istyle.bo.UserBO;
import com.toney.istyle.core.exception.ServiceException;

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
	 * @throws ServiceException
	 */
	void create(UserBO userBO, String password) throws ServiceException;
	
}
