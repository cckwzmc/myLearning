package com.toney.istyle.core.cache;

import com.toney.istyle.core.exception.ServiceException;

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
}
