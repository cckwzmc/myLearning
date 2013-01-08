package com.toney.core.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.toney.core.biz.AppCacheManager;
import com.toney.core.constants.CacheConstants;
import com.toney.core.exception.BusinessException;
import com.toney.dal.dao.ChannelDao;
import com.toney.dal.dao.ChannelTypeDao;
import com.toney.dal.dao.SysConfigDao;
import com.toney.dal.dao.TemplateManagerDao;
import com.toney.dal.model.ChannelDetailModel;
import com.toney.dal.model.ChannelTypeModel;
import com.toney.dal.model.SysConfigModel;
import com.toney.dal.model.TemplateManagerModel;
import com.toney.dal.service.DalMybatisManagerFactory;

/**
 * @author toney.li
 * 
 */
@Service("appCacheManager")
@Lazy(value = false)
public class AppCacheManagerImpl implements AppCacheManager {

	@Autowired
	private DalMybatisManagerFactory dalMybatisManagerFactory;

	/**
	 * 对应 applicationContext-cache.xml 中的ehcache配置。
	 */
	@Autowired
	private EhCacheCacheManager cacheManager;
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.toney.core.biz.AppCacheManager#refreshGlobalConfiguration()
	 */
	@Override
	public void refreshGlobalConfiguration() throws BusinessException {
		this.refresh(CacheConstants.KEY_WEBAPPMAPCONFIGURATION_CACHE);
		this.refresh(CacheConstants.KEY_WEBAPPMODELCONFIGURATION_CACHE);

	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.toney.core.biz.AppCacheManager#getGlobalConfiguration()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Map<String, SysConfigModel> getGlobalModelConfiguration() throws BusinessException {
		Cache cache = cacheManager.getCache(CacheConstants.CACHE_NAME);
		ValueWrapper wrapper = cache.get(CacheConstants.KEY_WEBAPPMODELCONFIGURATION_CACHE);
		if (wrapper == null) {
			return putGlobalModelConfiguration();
		}
		Map<String, SysConfigModel> map = (Map<String, SysConfigModel>) wrapper.get();
		if (MapUtils.isEmpty(map)) {
			return putGlobalModelConfiguration();
		}
		return map;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.toney.core.biz.AppCacheManager#getGlobalMapConfiguration()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Map<String, String> getGlobalMapConfiguration() throws BusinessException {
		Cache cache = cacheManager.getCache(CacheConstants.CACHE_NAME);
		ValueWrapper wrapper = cache.get(CacheConstants.KEY_WEBAPPMAPCONFIGURATION_CACHE);
		if (wrapper == null) {
			return putGlobalMapConfiguration();
		}
		Map<String, String> map = (Map<String, String>) wrapper.get();
		if (MapUtils.isEmpty(map)) {
			return putGlobalMapConfiguration();
		}
		return map;
	}

	/**
	 * 根据KEY刷新缓存
	 * 
	 * @param key
	 * @throws BusinessException
	 */
	private void refresh(String key) throws BusinessException {
		Cache cache = cacheManager.getCache(CacheConstants.CACHE_NAME);
		cache.evict(key);
	}

	/**
	 * 把值推送入缓存.
	 * 
	 * @return
	 * @throws BusinessException
	 */
	private Map<String, SysConfigModel> putGlobalModelConfiguration() throws BusinessException {
		List<SysConfigModel> rs = this.dalMybatisManagerFactory.getGlobalManagerFactory().getSysConfigDao().getAll();
		Map<String, SysConfigModel> map = new HashMap<String, SysConfigModel>();
		if (CollectionUtils.isNotEmpty(rs)) {
			for (SysConfigModel model : rs) {
				map.put(model.getCfgName(), model);
			}
		}
		Cache cache = cacheManager.getCache(CacheConstants.CACHE_NAME);
		cache.put(CacheConstants.KEY_WEBAPPMODELCONFIGURATION_CACHE, map);
		return map;
	}

	/**
	 * 把值推送入缓存.
	 * 
	 * @return
	 * @throws BusinessException
	 */
	private Map<String, String> putGlobalMapConfiguration() throws BusinessException {
		List<SysConfigModel> rs = this.dalMybatisManagerFactory.getGlobalManagerFactory().getSysConfigDao().getAll();
		Map<String, String> map = new HashMap<String, String>();
		if (CollectionUtils.isNotEmpty(rs)) {
			for (SysConfigModel model : rs) {
				map.put(model.getCfgName(), model.getCfgValue());
			}
		}
		Cache cache = cacheManager.getCache(CacheConstants.CACHE_NAME);
		cache.put(CacheConstants.KEY_WEBAPPMAPCONFIGURATION_CACHE, map);
		return map;
	}

	/**
	 * 推送频道数据
	 * @return
	 * @throws BusinessException
	 */
	private List<ChannelDetailModel> putChannelData() throws BusinessException {
		List<ChannelDetailModel> rs = this.dalMybatisManagerFactory.getChannelManagerFactory().getChannelDao().getAllChannelType();
		if (CollectionUtils.isNotEmpty(rs)) {
			Cache cache = cacheManager.getCache(CacheConstants.CACHE_NAME);
			cache.put(CacheConstants.KEY_APPCHANNEL_CACHE, rs);
		}
		return rs;
	}

	private List<ChannelTypeModel> putAppArtType() throws BusinessException {
		List<ChannelTypeModel> rs =this.dalMybatisManagerFactory.getChannelManagerFactory().getChannelTypeDao().getAllChannelType();
		if (CollectionUtils.isNotEmpty(rs)) {
			Cache cache = cacheManager.getCache(CacheConstants.CACHE_NAME);
			cache.put(CacheConstants.KEY_ARTTYPE_CACHE, rs);
		}
		return rs;
	}

	/* (non-Javadoc)
	 * @see com.toney.core.biz.AppCacheManager#getChannelListData()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ChannelDetailModel> getChannelListData() throws BusinessException {
		Cache cache = cacheManager.getCache(CacheConstants.CACHE_NAME);
		ValueWrapper wrapper = cache.get(CacheConstants.KEY_APPCHANNEL_CACHE);
		if (wrapper == null) {
			return putChannelData();
		}
		List<ChannelDetailModel> list = (List<ChannelDetailModel>) wrapper.get();
		if (CollectionUtils.isEmpty(list)) {
			return putChannelData();
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.toney.core.biz.AppCacheManager#getAppArtType()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ChannelTypeModel> getAppArtType() throws BusinessException {
		Cache cache = cacheManager.getCache(CacheConstants.CACHE_NAME);
		ValueWrapper wrapper = cache.get(CacheConstants.KEY_ARTTYPE_CACHE);
		if (wrapper == null) {
			return putAppArtType();
		}
		List<ChannelTypeModel> list = (List<ChannelTypeModel>) wrapper.get();
		if (CollectionUtils.isEmpty(list)) {
			return putAppArtType();
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.toney.core.biz.AppCacheManager#refreshChannelListData()
	 */
	@Override
	public void refreshChannelListData() throws BusinessException {
		this.refresh(CacheConstants.KEY_APPCHANNEL_CACHE);
	}

}
