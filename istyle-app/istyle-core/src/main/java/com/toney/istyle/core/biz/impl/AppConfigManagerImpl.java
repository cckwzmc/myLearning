package com.toney.istyle.core.biz.impl;

import java.util.Map;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toney.istyle.bo.AppConfigBO;
import com.toney.istyle.core.biz.AppConfigManager;
import com.toney.istyle.core.exception.ManagerException;
import com.toney.istyle.core.exception.ServiceException;
import com.toney.istyle.core.service.AppConfigQueryService;

/**
 *************************************************************** 
 * <p>
 * @CLASS :AppConfigManagerImpl.java
 * @DESCRIPTION : 系统配置管理
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 19, 2013
 *       </p>
 **************************************************************** 
 */
@Service("appConfigManager")
public class AppConfigManagerImpl implements AppConfigManager {
	private static final XLogger LOGGER=XLoggerFactory.getXLogger(AppConfigManagerImpl.class);

	@Autowired
	private AppConfigQueryService appConfigReadService;
	
	/* (non-Javadoc)
	 * @see com.toney.istyle.biz.AppConfigManager#initAppConfig()
	 */
	@Override
	public Map<String, AppConfigBO> getAppConfigs() throws ManagerException {
		try {
			return this.appConfigReadService.getMemMap();
		} catch (ServiceException e) {
			LOGGER.error("查询系统配置失败",e);
			throw new ManagerException(e);
		}
	}
	

}
