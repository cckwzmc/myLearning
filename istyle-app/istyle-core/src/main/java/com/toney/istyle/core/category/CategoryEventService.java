package com.toney.istyle.core.category;

import com.toney.istyle.core.exception.ServiceException;
import com.toney.istyle.module.CategoryModule;

/**
 *************************************************************** 
 * <p>
 * @CLASS :CategoryEventService.java
 * @DESCRIPTION : category's  create update delete service 
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 29, 2013
 *       </p>
 **************************************************************** 
 */
public interface CategoryEventService {

	void create(CategoryModule module) throws ServiceException;

	/**
	 * 删除同时会删除子集类目
	 * @param id
	 * @throws ServiceException
	 */
	void delete(Integer id) throws ServiceException;

}
