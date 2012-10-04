package com.toney.core.cache;

/**
 * @author toney.li
 * 全站的cache管理工厂.
 */
public interface CacheManagerFactory {
	public ApplicationCacheManager getAppContextCacheManager();
}
