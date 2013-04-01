package com.toney.sso.core.app;

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

import com.toney.sso.core.exception.ServiceException;
import com.toney.sso.dao.AppConfigDao;
import com.toney.sso.module.AppConfigModule;

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
@Service("appConfigQueryService")
public class AppConfigQueryServiceImpl implements AppConfigQueryService {

	private static final XLogger LOGGER = XLoggerFactory.getXLogger(AppConfigQueryServiceImpl.class);

	private static Map<String, AppConfigModule> memMap = new HashMap<String, AppConfigModule>();

	@Autowired
	AppConfigDao appConfigDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.toney.istyle.service.AppConfigReadService#getAppConfigAll()
	 */
	@Override
	public Map<String, AppConfigModule> getAppConfigAll() throws ServiceException {
		List<AppConfigModule> moduleList = this.appConfigDao.selectAll();
		Map<String, AppConfigModule> map = new HashMap<String, AppConfigModule>();
		for (AppConfigModule module : moduleList) {
			map.put(module.getCfgKey(), module);
		}
		return map;
	}

	@Override
	public Map<String, AppConfigModule> getMemMap() throws ServiceException {
		if (MapUtils.isEmpty(memMap)) {
			Map<String, AppConfigModule> m = this.getAppConfigAll();
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
