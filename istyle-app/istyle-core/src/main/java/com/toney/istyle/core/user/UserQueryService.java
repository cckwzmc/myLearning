package com.toney.istyle.core.user;

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


}
		