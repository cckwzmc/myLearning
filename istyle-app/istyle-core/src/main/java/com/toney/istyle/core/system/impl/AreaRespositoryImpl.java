package com.toney.istyle.core.system.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;

import com.toney.istyle.bo.AreaBO;
import com.toney.istyle.core.exception.RespositoryException;
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
class AreaRespositoryImpl implements AreaRespository {

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
			List<AreaBO> boList = new ArrayList<AreaBO>();
			for (AreaModule m : mList) {
				AreaBO b = new AreaBO();
				try {
					BeanUtils.copyProperties(b, m);
					boList.add(b);
				} catch (IllegalAccessException e) {
					LOGGER.error("查询地市信息失败", e);
					throw new RespositoryException(e);
				} catch (InvocationTargetException e) {
					LOGGER.error("查询地市信息失败", e);
					throw new RespositoryException(e);
				}
			}
			Element putElement = new Element(AREA_LIST, boList);
			cache.put(putElement);
			Map<Integer, AreaBO> map = new HashMap<Integer, AreaBO>();
			for (AreaBO b : boList) {
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
	public List<AreaBO> getAreaListAll() throws RespositoryException {
		Cache cache = cacheManager.getCacheManager().getCache("areaCache");
		Element element = cache.get(AREA_LIST);
		if (element != null) {
			return (List<AreaBO>) element.getObjectValue();
		}
		this.cacheAreaAll();
		element = cache.get(AREA_LIST);
		return (List<AreaBO>) element.getObjectValue();
	}

	/**
	 * 
	 * @return
	 * @throws RespositoryException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<Integer,AreaBO> getAreaMapAll() throws RespositoryException {
		Cache cache = cacheManager.getCacheManager().getCache("areaCache");
		Element element = cache.get(AREA_MAP);
		if (element != null) {
			return  (Map<Integer, AreaBO>) element.getObjectValue();
		}
		this.cacheAreaAll();
		element = cache.get(AREA_MAP);
		return (Map<Integer, AreaBO>) element.getObjectValue();
	}

	/* 
	 * (non-Javadoc)
	 * @see com.toney.istyle.core.system.AreaRespository#refresh()
	 */
	@Override
	public void refresh() throws RespositoryException {
		Cache cache = cacheManager.getCacheManager().getCache("areaCache");
		cache.remove(AREA_LIST);
		cache.remove(AREA_MAP);
		this.cacheAreaAll();
	}
}
