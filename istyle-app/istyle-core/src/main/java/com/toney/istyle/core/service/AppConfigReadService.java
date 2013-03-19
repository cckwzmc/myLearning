package com.toney.istyle.core.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.toney.istyle.bo.AppConfigBO;
import com.toney.istyle.core.exception.ServiceException;

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
public interface AppConfigReadService {
	
	/**
	 * DB的方式获取数据.
	 * @return
	 * @throws ServiceException
	 */
	Map<String, AppConfigBO> getAppConfigAll() throws ServiceException;

	/**
	 * 从内存中查询数据.
	 * @return
	 * @throws ServiceException
	 */
	Map<String, AppConfigBO> getMemMap() throws ServiceException;

	/**
	 * 刷新缓存
	 * @throws ServiceException
	 */
	void refresh() throws ServiceException;

}
