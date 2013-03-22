package com.toney.istyle.core.system;

import com.toney.istyle.bo.AreaBO;
import com.toney.istyle.core.exception.ServiceException;

/**
 *************************************************************** 
 * <p>
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

	void create(AreaBO bo) throws ServiceException;

	void deleteById(Integer id) throws ServiceException;

}
