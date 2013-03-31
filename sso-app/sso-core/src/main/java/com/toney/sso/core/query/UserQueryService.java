package com.toney.sso.core.query;

import java.util.List;

import com.toney.sso.core.bo.UserBO;
import com.toney.sso.core.exception.ServiceException;

/**
 *************************************************************** 
 * <p>
 * @CLASS :UserQueryService.java
 * @DESCRIPTION : 用户查询服务类
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 21, 2013
 *       </p>
 **************************************************************** 
 */
public interface UserQueryService {

	UserBO getUserByUserName(String userName) throws ServiceException;

	/**
	 * 这个接口直接走的数据库，提供给系统后台使用。
	 * @param regType
	 * @return
	 * @throws ServiceException
	 */
	List<UserBO> getUserByRegType(Integer regType) throws ServiceException;

	/**
	 * 根据用户ID查询用户.
	 * @param id
	 * @return
	 */
	UserBO getUserById(Long id) throws ServiceException;

}
		