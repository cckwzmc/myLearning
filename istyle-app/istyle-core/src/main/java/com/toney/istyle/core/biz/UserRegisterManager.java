package com.toney.istyle.core.biz;

import com.toney.istyle.core.exception.ManagerException;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :UserRegisterManager.java
 * @DESCRIPTION : 用户注册服务类
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 23, 2013
 *       </p>
 **************************************************************** 
 */
public interface UserRegisterManager {

	/**
	 * 注册类型：0:系统保留用户，1：系统用户 2：普通用户.
	 */
	public static final short REG_TYPE_0 = 0;
	/**
	 * 注册类型：0:系统保留用户，1：系统用户 2：普通用户.
	 */
	public static final short REG_TYPE_1 = 1;
	/**
	 * 注册类型：0:系统保留用户，1：系统用户 2：普通用户.
	 */
	public static final short REG_TYPE_2 = 2;

	void registerUser(String userName, String password, String nickName) throws ManagerException;

	/**
	 * 注册类型：0:系统保留用户，1：系统用户 2：普通用户.
	 * 
	 * @param userName
	 * @param password
	 * @param nickName
	 * @param regType
	 * @throws ManagerException
	 */
	void registerUser(String userName, String password, String nickName, Short regType) throws ManagerException;

}
