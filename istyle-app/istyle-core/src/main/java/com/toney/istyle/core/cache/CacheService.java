package com.toney.istyle.core.cache;

import java.util.List;

import com.toney.istyle.core.exception.ServiceException;
import com.toney.istyle.module.PlatformModule;

/**
 *************************************************************** 
 * <p>
 * @CLASS :CacheService.java
 * @DESCRIPTION : Cache service
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 23, 2013
 *       </p>
 **************************************************************** 
 */
public interface CacheService<T> {

	T get(String cacheName, String key) ;

	void put(String cacheName, String key, T t);

	String testCacheable(String test);

	void cacheManagerInfo(String cacheName);

	void putObj(String cacheName, String cacheKey, Object obj);

	Object getObj(String cacheName, String cacheKey);
	
	void remove(String cacheName,String key);
}
