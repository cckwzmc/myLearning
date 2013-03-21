package com.toney.istyle.core.biz;

import com.toney.istyle.bo.UserLoginedBO;
import com.toney.istyle.core.exception.ManagerException;


/**
 *************************************************************** 
 * <p>
 * @CLASS :UserLoginManager.java
 * @DESCRIPTION : 用户登录管理类
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 21, 2013
 *       </p>
 **************************************************************** 
 */
public interface UserLoginManager {

	UserLoginedBO checkLoginedState(String tokenId, Long userId, String userName) throws ManagerException;

}
