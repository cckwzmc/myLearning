package com.toney.istyle.core.platform;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.toney.istyle.core.cache.CacheService;
import com.toney.istyle.core.exception.RespositoryException;
import com.toney.istyle.dao.PlatformDao;
import com.toney.istyle.module.PlatformModule;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :PlatFormRespositoryImpl.java
 * @DESCRIPTION : PlatForm Respository implements
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 25, 2013
 *       </p>
 **************************************************************** 
 */
@Service("platFormRespository")
public class PlatFormRespositoryImpl implements PlatFormRespository {

	private static final XLogger LOGGER = XLoggerFactory.getXLogger(PlatFormRespositoryImpl.class);

	private static final String CACHE_MANAGER_NAME = "platformCache";
	private static final String CACHE_KEY_GETPALTFORMALL = "getPaltFormAll_";
	private static final String CACHE_KEY_BY_ID = "getPaltFormById_";

	@Autowired
	PlatformDao platformDao;

	@Autowired
	CacheService<PlatformModule> cacheService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.toney.istyle.core.platform.PlatFormRespository#getPlatFormAll()
	 */
	@Override
	public List<PlatformModule> getPlatFormAll() throws RespositoryException {
		cacheService.getObj(CACHE_MANAGER_NAME, CACHE_KEY_GETPALTFORMALL);
		List<PlatformModule> mList = this.platformDao.selectAll();
		if (CollectionUtils.isNotEmpty(mList)) {
			for (PlatformModule module : mList) {
				cacheService.put(CACHE_MANAGER_NAME, CACHE_KEY_BY_ID + module.getId(), module);
			}
			cacheService.putObj(CACHE_MANAGER_NAME, CACHE_KEY_GETPALTFORMALL, mList);
			return mList;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.toney.istyle.core.platform.PlatFormRespository#getPlatFormById(java
	 * .lang.Long)
	 */
	@Override
	public PlatformModule getPlatFormById(Long id) throws RespositoryException {
		return cacheService.get(CACHE_MANAGER_NAME, CACHE_KEY_BY_ID + id);
	}

	@Cacheable(value = "platformCache", key = "'getPaltFormAll_'")
	public void deleteCacheList() throws RespositoryException {

	}

	@Cacheable(value = "platformCache", key = "'getPaltFormById_'+#id")
	public void deleteCacheId(Long id) throws RespositoryException {

	}
}
