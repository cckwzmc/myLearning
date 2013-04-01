package com.toney.sso.core.biz;

import java.util.Map;

import com.toney.sso.core.exception.ManagerException;
import com.toney.sso.module.AppConfigModule;

/**
 *************************************************************** 
 * <p>
 * @CLASS :AppConfigsManager.java
 * @DESCRIPTION : application system config manager
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Apr 1, 2013
 *       </p>
 **************************************************************** 
 */
public interface AppConfigsManager {

	Map<String,AppConfigModule> getAppConfigs() throws ManagerException;

}
