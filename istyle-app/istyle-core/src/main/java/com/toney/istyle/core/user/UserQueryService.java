package com.toney.istyle.core.user;

import java.util.List;

import com.toney.istyle.bo.UserBO;
import com.toney.istyle.core.exception.ServiceException;

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
	List<UserBO> getUserByRegType(Short regType) throws ServiceException;

	/**
	 * 根据用户ID查询用户.
	 * @param uploadUid
	 * @return
	 */
	UserBO getUserById(Long uploadUid) throws ServiceException;

}
		