package com.toney.crawler.collection.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author toney.li
 * 缓存管理工厂.
 */
@Service("ehcacheManagerFactory")
public class CacheManagerFactoryImpl implements CacheManagerFactory{

	@Autowired
	private ApplicationCacheManager applicationCacheManager;
	
	@Override
	public ApplicationCacheManager getAppContextCacheManager() {
		return applicationCacheManager;
	}

}
