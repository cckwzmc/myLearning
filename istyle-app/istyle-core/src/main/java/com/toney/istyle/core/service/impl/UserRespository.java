package com.toney.istyle.core.service.impl;

import com.toney.istyle.bo.UserBO;
import com.toney.istyle.core.exception.RespositoryException;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :UserRespository.java
 * @DESCRIPTION : 用户repository服务
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 21, 2013
 *       </p>
 **************************************************************** 
 */
public interface UserRespository {

	UserBO getUserByUserName(String userName) throws RespositoryException;

	void deleteCache(String userName) throws RespositoryException;

}
