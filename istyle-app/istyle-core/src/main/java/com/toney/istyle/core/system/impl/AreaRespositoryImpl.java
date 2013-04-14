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
import com.toney.istyle.core.system.AreaRespository;
import com.toney.istyle.dao.AreaDao;
import com.toney.istyle.module.AreaModule;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :AreaRespositoryImpl.java
 * @DESCRIPTION : Area Respository implements
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 22, 2013
 *       </p>
 **************************************************************** 
 */
@Service("areaRespository")
public class AreaRespositoryImpl implements AreaRespository {

	private static final XLogger LOGGER = XLoggerFactory.getXLogger(AreaRespositoryImpl.class);
	private static final String AREA_LIST = "area_list";
	private static final String AREA_MAP = "area_map";

	@Autowired
	EhCacheCacheManager cacheManager;

	@Autowired
	AreaDao areaDao;

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

	/**
	 * 
	 * @return
	 * @throws RespositoryException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AreaModule> getAreaListAll() throws RespositoryException {
		Cache cache = cacheManager.getCacheManager().getCache("areaCache");
		Element element = cache.get(AREA_LIST);
		if (element != null) {
			return (List<AreaModule>) element.getObjectValue();
		}
		this.cacheAreaAll();
		element = cache.get(AREA_LIST);
		return (List<AreaModule>) element.getObjectValue();
	}

	/**
	 * 
	 * @return
	 * @throws RespositoryException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<Integer, AreaModule> getAreaMapAll() throws RespositoryException {
		Cache cache = cacheManager.getCacheManager().getCache("areaCache");
		Element element = cache.get(AREA_MAP);
		if (element != null) {
			return (Map<Integer, AreaModule>) element.getObjectValue();
		}
		this.cacheAreaAll();
		element = cache.get(AREA_MAP);
		return (Map<Integer, AreaModule>) element.getObjectValue();
	}
}
