package com.toney.sso.core.biz.impl;

import java.util.Map;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toney.sso.core.app.AppConfigQueryService;
import com.toney.sso.core.biz.AppConfigsManager;
import com.toney.sso.core.exception.ManagerException;
import com.toney.sso.core.exception.ServiceException;
import com.toney.sso.module.AppConfigModule;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :AppConfigsManagerImpl.java
 * @DESCRIPTION : 系统配置管理
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Apr 1, 2013
 *       </p>
 **************************************************************** 
 */
@Service("appConfigsManager")
public class AppConfigsManagerImpl implements AppConfigsManager {

	private static final XLogger LOGGER = XLoggerFactory.getXLogger(AppConfigsManagerImpl.class);
	@Autowired
	AppConfigQueryService appConfigQueryService;

	@Override
	public Map<String, AppConfigModule> getAppConfigs() throws ManagerException {
		try {
			return appConfigQueryService.getMemMap();
		} catch (ServiceException e) {
			LOGGER.error("查询系统配置错误", e);
			throw new ManagerException(e);
		}
	}

}
