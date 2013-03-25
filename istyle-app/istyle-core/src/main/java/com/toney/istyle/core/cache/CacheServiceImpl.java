package com.toney.istyle.core.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;

import com.toney.istyle.core.exception.ServiceException;

/**
 *************************************************************** 
 * <p>
 * 
 * @param <T>
 * @CLASS :CacheServiceImple.java
 * @DESCRIPTION : cache service impl
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 23, 2013
 *       </p>
 **************************************************************** 
 */
@Service("cacheService")
public class CacheServiceImpl<T> implements CacheService<T> {
	private static final XLogger LOGGER = XLoggerFactory.getXLogger(CacheServiceImpl.class);
	@Autowired
	EhCacheCacheManager cacheManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.toney.istyle.core.cache.CacheService#get(java.lang.String,
	 * java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T get(String cacheName, String key) {
		Cache cache = this.cacheManager.getCacheManager().getCache(cacheName);
		Element element = cache.get(key);
		if (element != null) {
			return (T) element.getObjectValue();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.toney.istyle.core.cache.CacheService#put(java.lang.String,
	 * java.lang.String, java.lang.Object)
	 */
	@Override
	public void put(String cacheName, String key, T t) {
		Cache cache = this.cacheManager.getCacheManager().getCache(cacheName);
		Element element = new Element(key, t);
		cache.put(element);
	}

	/**
	 * @param test
	 */
	@Override
	@Cacheable(value = "userCache", key = "'test_'+#test")
	public String testCacheable(String test) {
		LOGGER.info("~~~~~~testCacheable~~~ test:{}", test);
		return null;
	}

	@Override
	public void cacheManagerInfo(String cacheName) {
		Cache cache = this.cacheManager.getCacheManager().getCache(cacheName);
		// 得到缓存中的对象数
		LOGGER.info("~~~~~~cache.getSize~~~" + cache.getSize());
		// 得到缓存对象占用内存的大小
		LOGGER.info("~~~~~~cache.getMemoryStoreSize()~~~" + cache.getMemoryStoreSize());
		// 得到缓存读取的命中次数
		LOGGER.info("~~~~~~cache.getStatistics().getCacheHits()~~~" + cache.getStatistics().getCacheHits());
		// 得到缓存读取的错失次数
		LOGGER.info("~~~~~~cache.getStatistics().getCacheMisses()~~~" + cache.getStatistics().getCacheMisses());
		LOGGER.info("~~~~~~cache.getStatistics()~~~" + cache.getStatistics());
	}

	@Override
	public void putObj(String cacheName, String cacheKey, Object obj) {
		Cache cache = this.cacheManager.getCacheManager().getCache(cacheName);
		Element element = new Element(cacheKey, obj);
		cache.put(element);
	}

	/* (non-Javadoc)
	 * @see com.toney.istyle.core.cache.CacheService#getObj(java.lang.String, java.lang.String)
	 */
	@Override
	public Object getObj(String cacheName, String cacheKey) {
		Cache cache = this.cacheManager.getCacheManager().getCache(cacheName);
		Element element = cache.get(cacheKey);
		if (element != null) {
			return element.getObjectValue();
		}
		return null;
	}

	@Override
	public void remove(String cacheName, String key) {
		Cache cache = this.cacheManager.getCacheManager().getCache(cacheName);
		cache.remove(key);
	}
}
