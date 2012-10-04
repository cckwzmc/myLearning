package com.toney.core.biz.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toney.core.biz.ServiceStartInitManager;
import com.toney.core.cache.CacheManagerFactory;
import com.toney.core.exception.BusinessException;

/**
 * @author toney.li
 *
 */
@Service("serviceStartInitManager")
public class ServiceStartInitManagerImpl implements ServiceStartInitManager {
	 private static final Logger LOGGER = LoggerFactory.getLogger(ServiceStartInitManagerImpl.class);
	
	@Autowired
	private CacheManagerFactory ehcacheManagerFactory;
	
	@Override
	public void initApplicationData() throws BusinessException {
		ehcacheManagerFactory.getAppContextCacheManager().initAppArtType();
		ehcacheManagerFactory.getAppContextCacheManager().initAppChannel();
		ehcacheManagerFactory.getAppContextCacheManager().initApplicationContext();
	}

}
