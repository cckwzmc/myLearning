package com.toney.istyle.core.system;

import com.toney.istyle.core.exception.ServiceException;
import com.toney.istyle.module.AreaModule;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :AreaEventService.java
 * @DESCRIPTION : Area Event Service
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 22, 2013
 *       </p>
 **************************************************************** 
 */
public interface AreaEventService {

	void refreshCache() throws ServiceException;

	void create(AreaModule bo) throws ServiceException;

	void deleteById(Integer id) throws ServiceException;

}
