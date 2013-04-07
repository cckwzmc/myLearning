package com.toney.istyle.core.user;

import java.util.List;

import com.toney.istyle.core.exception.ServiceException;
import com.toney.istyle.module.UserModule;

/**
 *************************************************************** 
 * <p>
 * @CLASS :UserQueryService.java
 * @DESCRIPTION : 用户查询服务类,请使用hessian接口
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 21, 2013
 *       </p>
 **************************************************************** 
 */
@Deprecated
public interface UserQueryService {

	UserModule getUserByUserName(String userName) throws ServiceException;

	/**
	 * 这个接口直接走的数据库，提供给系统后台使用。
	 * @param regType
	 * @return
	 * @throws ServiceException
	 */
	List<UserModule> getUserByRegType(Short regType) throws ServiceException;

	/**
	 * 根据用户ID查询用户.
	 * @param uploadUid
	 * @return
	 */
	UserModule getUserById(Long uploadUid) throws ServiceException;

	List<UserModule> getRetainUserAll() throws ServiceException;

}
		