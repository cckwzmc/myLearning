package com.toney.istyle.core.user.impl;

import java.util.List;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.toney.istyle.core.cache.CacheService;
import com.toney.istyle.core.exception.RespositoryException;
import com.toney.istyle.dao.UserDao;
import com.toney.istyle.module.UserModule;

/**
 *************************************************************** 
 * <p>
 * @CLASS :UserRespositoryImpl.java
 * @DESCRIPTION : 用户repository服务
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 21, 2013
 *       </p>
 **************************************************************** 
 */
@Service("userRespository")
public class UserRespositoryImpl implements UserRespository {
	private static final XLogger LOGGER = XLoggerFactory.getXLogger(UserRespositoryImpl.class);

	private static final String CACHE_MANAGER_NAME = "userCache";
	private static final String CACHE_KEY_USERNAME = "byUserName_";
	private static final String CACHE_KEY_Id = "byUserId_";
	@Autowired
	UserDao userDao;

	@Autowired
	CacheService<UserModule> cacheService;

	@Override
	public UserModule getUserByUserName(String userName) throws RespositoryException {
		UserModule cacheModule = cacheService.get(CACHE_MANAGER_NAME, CACHE_KEY_USERNAME + userName);
		if (cacheModule != null) {
			return cacheModule;
		}
		UserModule module = this.userDao.selectByUserName(userName);
		if (module != null) {
			cacheService.put(CACHE_MANAGER_NAME, CACHE_KEY_USERNAME + userName, module);
			cacheService.put(CACHE_MANAGER_NAME, CACHE_KEY_Id + module.getId(), module);
			return module;
		}
		return null;
	}

	@Override
	@Cacheable(value = "userCache", key = "'userName_'+#userName")
	public void deleteCache(String userName) throws RespositoryException {

	}

	@Override
	@Cacheable(value = "userCache", key = "'userId_'+#id")
	public void deleteCache(Long id) throws RespositoryException {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.toney.istyle.core.user.impl.UserRespository#getUserByRegType(java
	 * .lang.Short)
	 */
	@Override
	public List<UserModule> getUserByRegType(Short regType) throws RespositoryException {
		List<UserModule> mList = this.userDao.selectByRegType(regType);
		return mList;
	}

	@Override
	public UserModule getUserById(Long id) throws RespositoryException {
		UserModule cacheBo = cacheService.get(CACHE_MANAGER_NAME, CACHE_KEY_USERNAME + id);
		if (cacheBo != null) {
			return cacheBo;
		}
		UserModule module = this.userDao.selectById(id);
		return module;
	}

}
