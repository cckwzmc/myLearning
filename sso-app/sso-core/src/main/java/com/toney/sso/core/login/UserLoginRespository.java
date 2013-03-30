package com.toney.sso.core.login;

import com.toney.sso.core.bo.UserBO;
import com.toney.sso.core.bo.UserLoginedBO;
import com.toney.sso.core.exception.RespositoryException;

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

	UserLoginedBO userLoginedInfo(String userName,String tokenId) throws RespositoryException;

	void refreshLogined(String userName,String tokenId) throws RespositoryException;

	void putLoginedInfoCache(UserLoginedBO bo) throws RespositoryException;

}
