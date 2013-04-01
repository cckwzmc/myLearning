package com.toney.sso.core.app;

import java.util.Map;

import com.toney.sso.core.exception.ServiceException;
import com.toney.sso.module.AppConfigModule;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :ApplicationConfigService.java
 * @DESCRIPTION :系统配置，计入缓存
 * @AUTHOR :ly_zy_ljh
 * @VERSION :v1.0
 * @DATE :2013-3-18
 *       </p>
 **************************************************************** 
 */
public interface AppConfigQueryService {

	/**
	 * DB的方式获取数据.
	 * 
	 * @return
	 * @throws ServiceException
	 */
	Map<String, AppConfigModule> getAppConfigAll() throws ServiceException;

	/**
	 * 从内存中查询数据.
	 * 
	 * @return
	 * @throws ServiceException
	 */
	Map<String, AppConfigModule> getMemMap() throws ServiceException;

	/**
	 * 刷新缓存
	 * 
	 * @throws ServiceException
	 */
	void refresh() throws ServiceException;

}
