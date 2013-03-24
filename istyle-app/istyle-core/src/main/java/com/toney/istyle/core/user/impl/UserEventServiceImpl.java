package com.toney.istyle.core.user.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.toney.istyle.bo.UserBO;
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
	public void create(UserBO userBO,String password) throws ServiceException {
		UserModule module=new UserModule();
		try {
			BeanUtils.copyProperties(module, userBO);
			module.setCreateDate(new Date());
			password=DigestUtils.md5DigestAsHex(DigestUtils.md5DigestAsHex(password.getBytes()).getBytes());
			module.setPassword(password);
		} catch (IllegalAccessException e) {
			LOGGER.error("创建新用户失败，userModule {}",module,e);
			throw new ServiceException(e);
		} catch (InvocationTargetException e) {
			LOGGER.error("创建新用户失败，userModule {}",module,e);
			throw new ServiceException(e);
		}
		this.userDao.insert(module);
	}

}
