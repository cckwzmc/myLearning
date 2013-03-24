package com.toney.istyle.core.biz.impl;

import java.util.Date;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toney.istyle.bo.UserBO;
import com.toney.istyle.core.biz.UserRegisterManager;
import com.toney.istyle.core.exception.ManagerException;
import com.toney.istyle.core.exception.ServiceException;
import com.toney.istyle.core.user.UserEventService;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :UserRegisterManagerImp.java
 * @DESCRIPTION : 用户注册
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 23, 2013
 *       </p>
 **************************************************************** 
 */
@Service("userRegisterManager")
public class UserRegisterManagerImp implements UserRegisterManager {

	private static final XLogger LOGGER = XLoggerFactory.getXLogger(UserRegisterManagerImp.class);

	
	@Autowired
	UserEventService userEventService;
	
	/* (non-Javadoc)
	 * @see com.toney.istyle.core.biz.UserRegisterManager#registerUser(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void registerUser(String userName,String password,String nickName) throws ManagerException{
		this.registerUser(userName, password, nickName,null);
	}

	/* (non-Javadoc)
	 * @see com.toney.istyle.core.biz.UserRegisterManager#registerUser(java.lang.String, java.lang.String, java.lang.String, java.lang.Short)
	 */
	@Override
	public void registerUser(String userName, String password, String nickName, Short regType) throws ManagerException {
		UserBO bo=new UserBO();
		bo.setUserName(userName);
		bo.setNickName(nickName);
		bo.setRegType(regType);
		if(regType==null||(regType!=REG_TYPE_0&&regType!=REG_TYPE_1)){
			bo.setRegType(REG_TYPE_2);
		}
		try {
			this.userEventService.create(bo, password);
		} catch (ServiceException e) {
			LOGGER.error("注册新用户失败,,userName={},nickName={},regType={}",new Object[]{userName,nickName,regType}, e);
			throw new ManagerException(e);
		}
	}
}
