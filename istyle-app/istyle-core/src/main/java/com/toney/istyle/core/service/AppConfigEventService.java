package com.toney.istyle.core.service;

import com.toney.istyle.bo.AppConfigBO;
import com.toney.istyle.core.exception.ServiceException;

/**
 *************************************************************** 
 * <p>
 * @CLASS :AppConfigWriterService.java
 * @DESCRIPTION : AppConfig写操作
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 19, 2013
 *       </p>
 **************************************************************** 
 */
public interface AppConfigEventService {
	

	void save(AppConfigBO bo) throws ServiceException;

	void deleteById(Long id) throws ServiceException;

}
