package com.toney.istyle.core.user.impl;

import java.util.Date;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.toney.istyle.core.exception.ServiceException;
import com.toney.istyle.core.user.UserEventService;
import com.toney.istyle.dao.UserDao;
import com.toney.istyle.module.UserModule;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :UserEventServiceImpl.java
 * @DESCRIPTION : TODO
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 22, 2013
 *       </p>
 **************************************************************** 
 */
@Service("userEventService")
public class UserEventServiceImpl implements UserEventService {
	private static final XLogger LOGGER = XLoggerFactory.getXLogger(UserEventServiceImpl.class);

	@Autowired
	UserDao userDao;

	@Override
	public void create(UserModule module, String password) throws ServiceException {
		module.setCreateDate(new Date());
		password = DigestUtils.md5DigestAsHex(DigestUtils.md5DigestAsHex(password.getBytes()).getBytes());
		module.setPassword(password);
		this.userDao.insert(module);
	}

}
