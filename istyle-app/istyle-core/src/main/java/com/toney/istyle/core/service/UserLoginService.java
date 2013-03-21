package com.toney.istyle.core.service;

import com.toney.istyle.core.exception.ServiceException;

/**
 *************************************************************** 
 * <p>
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
	 * 使用EhCache实现Session,
	 * @param userName
	 * @return
	 * @throws ServiceException
	 */
	boolean isUserLogined(String userName) throws ServiceException;
}
