package com.toney.sso.core.login;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.toney.sso.core.bo.UserBO;
import com.toney.sso.core.bo.UserLoginedBO;
import com.toney.sso.core.exception.RespositoryException;
import com.toney.sso.dao.UserDao;
import com.toney.sso.module.UserModule;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :UserLoginRespository.java
 * @DESCRIPTION : 用户登录Respository
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 21, 2013
 *       </p>
 **************************************************************** 
 */
@Service("userLoginRespository")
public class UserLoginRespositoryImpl implements UserLoginRespository {
	private static final XLogger LOGGER = XLoggerFactory.getXLogger(UserLoginRespositoryImpl.class);
	private static final String USERLOGINED_KEY = "loginedUserName_";
	@Autowired
	private UserDao userDao;
	@Autowired
	EhCacheCacheManager cacheManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.toney.istyle.core.service.impl.UserLoginRespository#getLoginUser(
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public UserBO getLoginUser(String userName, String password) throws RespositoryException {
		UserModule module = this.userDao.selectByUnAndPwd(userName, password);
		if (module != null) {
			UserBO bo = new UserBO();
			try {
				BeanUtils.copyProperties(bo, module);
			} catch (IllegalAccessException e) {
				LOGGER.error("根据用户查询用户信息失败 userName:{}", userName, e);
				throw new RespositoryException(e);
			} catch (InvocationTargetException e) {
				LOGGER.error("根据用户查询用户信息失败 userName:{}", userName, e);
				throw new RespositoryException(e);
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.toney.istyle.core.service.impl.UserLoginRespository#userLoginedInfo
	 * (java.lang.String)
	 */
	@Override
	public UserLoginedBO userLoginedInfo(String userName,String tokenId) throws RespositoryException {
		Cache cache = this.cacheManager.getCacheManager().getCache("userLoginedCache");
		Element e = cache.get(USERLOGINED_KEY + userName+"-"+tokenId);
		if (e != null) {
			return (UserLoginedBO) e.getObjectValue();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.toney.istyle.core.service.impl.UserLoginRespository#refreshLogined
	 * (java.lang.String)
	 */
	@Override
	public void refreshLogined(String userName,String tokenId) throws RespositoryException {
		Cache cache = this.cacheManager.getCacheManager().getCache("userLoginedCache");
		UserLoginedBO bo = this.userLoginedInfo(userName,tokenId);
		if (bo != null) {
			bo.setLastSessionDate(new Date().getTime());
			Element element = new Element(USERLOGINED_KEY + userName+"-"+tokenId, bo);
			cache.put(element);
		}
	}

	/* 
	 * (non-Javadoc)
	 * @see com.toney.istyle.core.service.impl.UserLoginRespository#putLoginedInfoCache(com.toney.istyle.bo.UserLoginedBo)
	 */
	@Override
	public void putLoginedInfoCache(UserLoginedBO bo) throws RespositoryException {
		Cache cache = this.cacheManager.getCacheManager().getCache("userLoginedCache");
		Element element = new Element(USERLOGINED_KEY + bo.getUserName()+"-"+bo.getTokenId(), bo);
		cache.put(element);
	}
}
