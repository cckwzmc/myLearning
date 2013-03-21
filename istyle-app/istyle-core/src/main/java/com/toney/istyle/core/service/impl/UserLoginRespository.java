package com.toney.istyle.core.service.impl;

import com.toney.istyle.bo.UserBO;
import com.toney.istyle.bo.UserLoginedBO;
import com.toney.istyle.core.exception.RespositoryException;

/**
 *************************************************************** 
 * <p>
 * @CLASS :UserLoginRespository.java
 * @DESCRIPTION : 用户登录Respository
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 21, 2013
 *       </p>
 **************************************************************** 
 */
public interface UserLoginRespository {

	UserBO getLoginUser(String userName, String password) throws RespositoryException;

	UserLoginedBO userLoginedInfo(String userName) throws RespositoryException;

	void refreshLogined(String userName) throws RespositoryException;

	void putLoginedInfoCache(UserLoginedBO bo) throws RespositoryException;

}
