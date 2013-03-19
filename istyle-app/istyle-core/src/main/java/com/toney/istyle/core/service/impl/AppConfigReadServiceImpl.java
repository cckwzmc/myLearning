package com.toney.istyle.core.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toney.istyle.bo.AppConfigBO;
import com.toney.istyle.core.exception.ServiceException;
import com.toney.istyle.core.service.AppConfigReadService;
import com.toney.istyle.dao.AppConfigDao;
import com.toney.istyle.module.AppConfigModule;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :AppConfigReadServiceImpl.java
 * @DESCRIPTION : 读取系统配置
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 19, 2013
 *       </p>
 **************************************************************** 
 */
@Service("appConfigReadService")
public class AppConfigReadServiceImpl implements AppConfigReadService {

	private static final XLogger LOGGER = XLoggerFactory.getXLogger(AppConfigReadServiceImpl.class);

	private static Map<String, AppConfigBO> memMap = new HashMap<String, AppConfigBO>();

	@Autowired
	AppConfigDao appConfigDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.toney.istyle.service.AppConfigReadService#getAppConfigAll()
	 */
	@Override
	public Map<String, AppConfigBO> getAppConfigAll() throws ServiceException {
		List<AppConfigModule> moduleList = this.appConfigDao.selectAll();
		Map<String, AppConfigBO> map = new HashMap<String, AppConfigBO>();
		for (AppConfigModule module : moduleList) {
			AppConfigBO tmpModule = new AppConfigBO();
			try {
				BeanUtils.copyProperties(tmpModule, module);
			} catch (IllegalAccessException e) {
				LOGGER.error("AppConfigModule转换为AppConfigBo时失败", e);
				throw new ServiceException(e);
			} catch (InvocationTargetException e) {
				LOGGER.error("AppConfigModule转换为AppConfigBo时失败", e);
				throw new ServiceException(e);
			}
			map.put(module.getCfgKey(), tmpModule);
		}
		return map;
	}

	@Override
	public Map<String, AppConfigBO> getMemMap() throws ServiceException {
		if (MapUtils.isEmpty(memMap)) {
			Map<String, AppConfigBO> m = this.getAppConfigAll();
			for (Iterator<String> iterator = m.keySet().iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				memMap.put(key, m.get(key));
			}
		}
		return memMap;
	}

	@Override
	public void refresh() throws ServiceException {
		memMap.clear();
		getMemMap();
	}

}
