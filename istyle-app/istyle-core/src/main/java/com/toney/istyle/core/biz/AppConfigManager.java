package com.toney.istyle.core.biz;

import java.util.Map;

import com.toney.istyle.bo.AppConfigBO;
import com.toney.istyle.core.exception.ManagerException;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :AppConfigManager.java
 * @DESCRIPTION : 系统的配置管理
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 19, 2013
 *       </p>
 **************************************************************** 
 */
public interface AppConfigManager {

	/**
	 * 系统配置放入本的内存.
	 * 
	 * @throws ManagerException
	 */

	Map<String, AppConfigBO> getAppConfigs() throws ManagerException;
}
