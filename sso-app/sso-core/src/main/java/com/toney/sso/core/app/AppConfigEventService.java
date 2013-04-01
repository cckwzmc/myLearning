package com.toney.sso.core.app;

import com.toney.sso.core.exception.ServiceException;
import com.toney.sso.module.AppConfigModule;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :AppConfigWriterService.java
 * @DESCRIPTION : AppConfig写操作
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 19, 2013
 *       </p>
 **************************************************************** 
 */
public interface AppConfigEventService {

	void save(AppConfigModule module) throws ServiceException;

	void deleteById(Long id) throws ServiceException;

}
