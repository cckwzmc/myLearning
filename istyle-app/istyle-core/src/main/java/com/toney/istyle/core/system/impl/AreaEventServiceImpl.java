package com.toney.istyle.core.system.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;

import com.toney.istyle.core.exception.RespositoryException;
import com.toney.istyle.core.exception.ServiceException;
import com.toney.istyle.core.system.AreaEventService;
import com.toney.istyle.core.system.AreaRespository;
import com.toney.istyle.dao.AreaDao;
import com.toney.istyle.module.AreaModule;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :AreaEventServiceImpl.java
 * @DESCRIPTION : AreaEventServiceImpl
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 22, 2013
 *       </p>
 **************************************************************** 
 */
@Service("areaEventService")
public class AreaEventServiceImpl implements AreaEventService {
	private static final XLogger LOGGER = XLoggerFactory.getXLogger(AreaEventServiceImpl.class);
	private static final String AREA_LIST = "area_list";
	private static final String AREA_MAP = "area_map";
	
	@Autowired
	AreaDao areaDao;

	@Autowired
	private AreaRespository areaRespository;

	@Autowired
	EhCacheCacheManager cacheManager;

	private void cacheAreaAll() throws RespositoryException {
		Cache cache = cacheManager.getCacheManager().getCache("areaCache");
		List<AreaModule> mList = this.areaDao.selectAll();
		if (CollectionUtils.isNotEmpty(mList)) {
			Element putElement = new Element(AREA_LIST, mList);
			cache.put(putElement);
			Map<Integer, AreaModule> map = new HashMap<Integer, AreaModule>();
			for (AreaModule b : mList) {
				map.put(b.getId(), b);
			}
			Element mapElement = new Element(AREA_MAP, map);
			cache.put(mapElement);
		}
	}
	@Override
	public void create(AreaModule bo) throws ServiceException {
		AreaModule areaModule = new AreaModule();
		this.areaDao.insert(areaModule);
		this.refreshCache();
	}

	@Override
	public void deleteById(Integer id) throws ServiceException {
		this.areaDao.deleteById(id);
		this.refreshCache();
	}

	/**
	 * @throws ServiceException
	 */
	@Override
	public void refreshCache() throws ServiceException {
		try {
			this.cacheAreaAll();
		} catch (RespositoryException e) {
			LOGGER.error("刷新地市信息失败,modeule:{}", e);
			throw new ServiceException(e);
		}
	}

}
