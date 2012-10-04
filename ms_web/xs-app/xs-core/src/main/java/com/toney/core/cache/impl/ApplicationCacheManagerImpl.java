package com.toney.core.cache.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.toney.core.cache.ApplicationCacheManager;
import com.toney.core.cache.CacheConstants;
import com.toney.core.dao.ChannelDao;
import com.toney.core.dao.ChannelTypeDao;
import com.toney.core.dao.SysConfigDao;
import com.toney.core.dao.TemplateManagerDao;
import com.toney.core.exception.BusinessException;
import com.toney.core.model.ChannelDetailModel;
import com.toney.core.model.ChannelTypeModel;
import com.toney.core.model.SysConfigModel;
import com.toney.core.model.TemplateManagerModel;

/**
 * @author toney.li
 *
 */
@Service("applicationCacheManager")
@Lazy(value=false)
public class ApplicationCacheManagerImpl implements ApplicationCacheManager {
	@Autowired
	private SysConfigDao sysConfigDao;
	@Autowired
	private ChannelDao channelDao;
	@Autowired
	private ChannelTypeDao channelTypeDao;
	@Autowired
	private EhCacheCacheManager cacheManager;
	@Autowired
	private TemplateManagerDao templateManagerDao; 
	
	/* (non-Javadoc)
	 * @see com.toney.core.cache.ApplicationCacheManager#getApplicatonContext()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Map<String, SysConfigModel> getApplicatonContext() throws BusinessException {
		Cache cache=cacheManager.getCache(CacheConstants.CACHE_NAME);
		ValueWrapper wrapper= cache.get(CacheConstants.KEY_APPCONTEXT_CACHE);
		if(wrapper==null){
			return initApplicationContext();
		}
		Map<String, SysConfigModel> map = (Map<String, SysConfigModel>)wrapper.get();
		if(MapUtils.isEmpty(map)){
			return initApplicationContext();
		}
		return map;
	}

	/* (non-Javadoc)
	 * @see com.toney.core.cache.ApplicationCacheManager#refresh(java.lang.String)
	 */
	@Override
	public void refresh(String key) throws BusinessException {
		Cache cache=cacheManager.getCache(CacheConstants.CACHE_NAME);
		cache.evict(key);
		this.initApplicationContext();
	}

	/* (non-Javadoc)
	 * @see com.toney.core.cache.ApplicationCacheManager#initApplicationContext()
	 */
	@Override
	//@Cacheable(value="siteContext",key="#key")
	public Map<String, SysConfigModel> initApplicationContext() throws BusinessException {
		List<SysConfigModel> rs=this.sysConfigDao.getAll();
		Map<String,SysConfigModel> map=new HashMap<String, SysConfigModel>();
		if(CollectionUtils.isNotEmpty(rs)){
			for(SysConfigModel model:rs){
				map.put(model.getCfgName(), model);
			}
		}
		Cache cache=cacheManager.getCache(CacheConstants.CACHE_NAME);
		cache.put(CacheConstants.KEY_APPCONTEXT_CACHE, map);
		return map;
	}
	@Override
	//@Cacheable(value="siteContext",key="#key")
	public List<ChannelDetailModel> initAppChannel() throws BusinessException {
		List<ChannelDetailModel> rs=this.channelDao.getAllChannelType();
		if(CollectionUtils.isNotEmpty(rs)){
			Cache cache=cacheManager.getCache(CacheConstants.CACHE_NAME);
			cache.put(CacheConstants.KEY_APPCHANNEL_CACHE, rs);
		}
		return rs;
	}
	@Override
	//@Cacheable(value="siteContext",key="#key")
	public List<ChannelTypeModel> initAppArtType() throws BusinessException {
		List<ChannelTypeModel> rs=this.channelTypeDao.getAllChannelType();
		if(CollectionUtils.isNotEmpty(rs)){
			Cache cache=cacheManager.getCache(CacheConstants.CACHE_NAME);
			cache.put(CacheConstants.KEY_ARTTYPE_CACHE, rs);
		}
		return rs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ChannelDetailModel> getAppChannel() throws BusinessException {
		Cache cache=cacheManager.getCache(CacheConstants.CACHE_NAME);
		ValueWrapper wrapper= cache.get(CacheConstants.KEY_APPCHANNEL_CACHE);
		if(wrapper==null){
			return initAppChannel();
		}
		List<ChannelDetailModel> list = (List<ChannelDetailModel>)wrapper.get();
		if(CollectionUtils.isEmpty(list)){
			return initAppChannel();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ChannelTypeModel> getAppArtType() throws BusinessException {
		Cache cache=cacheManager.getCache(CacheConstants.CACHE_NAME);
		ValueWrapper wrapper= cache.get(CacheConstants.KEY_ARTTYPE_CACHE);
		if(wrapper==null){
			return initAppArtType();
		}
		List<ChannelTypeModel> list = (List<ChannelTypeModel>)wrapper.get();
		if(CollectionUtils.isEmpty(list)){
			return initAppArtType();
		}
		return list;
	}

	@Override
	public List<TemplateManagerModel> initTemplageManager() throws BusinessException {
		List<TemplateManagerModel> rs=this.templateManagerDao.getAll();
		if(CollectionUtils.isNotEmpty(rs)){
			Cache cache=cacheManager.getCache(CacheConstants.CACHE_NAME);
			cache.put(CacheConstants.KEY_TPLMANAGER_CACHE, rs);
		}
		return rs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TemplateManagerModel> getTemplageManagerData() throws BusinessException {
		Cache cache=cacheManager.getCache(CacheConstants.CACHE_NAME);
		ValueWrapper wrapper= cache.get(CacheConstants.KEY_TPLMANAGER_CACHE);
		if(wrapper==null){
			return initTemplageManager();
		}
		List<TemplateManagerModel> list = (List<TemplateManagerModel>)wrapper.get();
		if(CollectionUtils.isEmpty(list)){
			return initTemplageManager();
		}
		return list;
	}

}
