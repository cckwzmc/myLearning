package com.toney.sso.core.user.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;

import com.toney.istyle.bo.UserBO;
import com.toney.istyle.core.cache.CacheService;
import com.toney.istyle.core.exception.RespositoryException;
import com.toney.istyle.core.exception.ServiceException;
import com.toney.istyle.module.UserModule;
import com.toney.sso.dao.UserDao;

/**
 *************************************************************** 
 * <p>
 * 
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
	CacheService<UserBO> cacheService;

	@Override
	public UserBO getUserByUserName(String userName) throws RespositoryException {
		try {
			UserBO cacheBo = cacheService.get(CACHE_MANAGER_NAME, CACHE_KEY_USERNAME + userName);
			if (cacheBo != null) {
				return cacheBo;
			}
			UserModule module = this.userDao.selectByUserName(userName);
			if (module != null) {
				UserBO bo = new UserBO();
				BeanUtils.copyProperties(bo, module);
				cacheService.put(CACHE_MANAGER_NAME, CACHE_KEY_USERNAME + userName, bo);
				cacheService.put(CACHE_MANAGER_NAME, CACHE_KEY_Id + bo.getId(), bo);
				return bo;
			}
		} catch (IllegalAccessException e) {
			LOGGER.error("根据用户名查询用户信息失败 userName:{}", userName, e);
			throw new RespositoryException(e);
		} catch (InvocationTargetException e) {
			LOGGER.error("根据用户名查询用户信息失败 userName:{}", userName, e);
			throw new RespositoryException(e);
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
	public List<UserBO> getUserByRegType(Short regType) throws RespositoryException {
		List<UserModule> mList = this.userDao.selectByRegType(regType);
		if (CollectionUtils.isNotEmpty(mList)) {
			List<UserBO> boList = new ArrayList<UserBO>();
			try {
				for (UserModule m : mList) {
					UserBO bo = new UserBO();
					BeanUtils.copyProperties(bo, m);
					boList.add(bo);
				}
			} catch (IllegalAccessException e) {
				LOGGER.error("根据用户注册类型查询用户信息失败 regType:{}", regType, e);
				throw new RespositoryException(e);
			} catch (InvocationTargetException e) {
				LOGGER.error("根据用户注册类型查询用户信息失败 regType:{}", regType, e);
				throw new RespositoryException(e);
			}
			return boList;
		}
		return null;
	}

	@Override
	public UserBO getUserById(Long id) throws RespositoryException {
		UserBO cacheBo = cacheService.get(CACHE_MANAGER_NAME, CACHE_KEY_USERNAME + id);
		if (cacheBo != null) {
			return cacheBo;
		}
		UserModule module = this.userDao.selectById(id);
		if (module != null) {
			UserBO bo = new UserBO();
			try {
				BeanUtils.copyProperties(bo, module);
				cacheService.put(CACHE_MANAGER_NAME, CACHE_KEY_USERNAME + bo.getUserName(), bo);
				cacheService.put(CACHE_MANAGER_NAME, CACHE_KEY_Id + bo.getId(), bo);
				return bo;
			} catch (IllegalAccessException e) {
				LOGGER.error("根据用户ID查询用户信息失败 id:{}", id, e);
				throw new RespositoryException(e);
			} catch (InvocationTargetException e) {
				LOGGER.error("根据用户ID查询用户信息失败 id:{}", id, e);
				throw new RespositoryException(e);
			}
		}
		return null;
	}

}
