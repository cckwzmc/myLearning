package com.toney.istyle.core.user.impl;

import java.util.List;

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

	/**
	 * 根据用户的注册类型查询用户.直接从DB中查询，只给后台管理使用.
	 * @param regType
	 * @return
	 * @throws RespositoryException
	 */
	List<UserBO> getUserByRegType(Short regType) throws RespositoryException;

	UserBO getUserById(Long id)  throws RespositoryException;

	void deleteCache(Long id) throws RespositoryException;

}
