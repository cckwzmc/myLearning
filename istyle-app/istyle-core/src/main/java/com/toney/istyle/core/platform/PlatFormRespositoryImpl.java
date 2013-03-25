package com.toney.istyle.core.platform;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.toney.istyle.bo.PlatFormBO;
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
	CacheService<PlatFormBO> cacheService;

	/* (non-Javadoc)
	 * @see com.toney.istyle.core.platform.PlatFormRespository#getPlatFormAll()
	 */
	@Override
	public List<PlatFormBO> getPlatFormAll() throws RespositoryException {
		cacheService.getObj(CACHE_MANAGER_NAME, CACHE_KEY_GETPALTFORMALL);
		List<PlatformModule> mList = this.platformDao.selectAll();
		try {
			if (CollectionUtils.isNotEmpty(mList)) {
				List<PlatFormBO> boList = new ArrayList<PlatFormBO>();
				for (PlatformModule module : mList) {
					PlatFormBO bo = new PlatFormBO();
					BeanUtils.copyProperties(bo, module);
					boList.add(bo);
					cacheService.put(CACHE_MANAGER_NAME, CACHE_KEY_BY_ID+module.getId(), bo);
				}
				cacheService.putObj(CACHE_MANAGER_NAME, CACHE_KEY_GETPALTFORMALL, mList);
				return boList;
			}
		} catch (IllegalAccessException e) {
			LOGGER.error("查询所有的平台商失败", e);
			throw new RespositoryException(e);
		} catch (InvocationTargetException e) {
			LOGGER.error("查询所有的平台商失败", e);
			throw new RespositoryException(e);
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.toney.istyle.core.platform.PlatFormRespository#getPlatFormById(java.lang.Long)
	 */
	@Override
	public PlatFormBO getPlatFormById(Long id) throws RespositoryException{
		return cacheService.get(CACHE_MANAGER_NAME, CACHE_KEY_BY_ID+id);
	}
	
	@Cacheable(value="platformCache",key="'getPaltFormAll_'")
	public void deleteCacheList() throws RespositoryException{
		
	}
	
	@Cacheable(value="platformCache",key="'getPaltFormById_'+#id")
	public void deleteCacheId(Long id) throws RespositoryException{
		
	}
}
