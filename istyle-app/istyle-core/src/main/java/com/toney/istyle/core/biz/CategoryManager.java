package com.toney.istyle.core.biz;

import java.util.List;

import com.toney.istyle.core.exception.ServiceException;
import com.toney.istyle.module.CategoryModule;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :CategoryManager.java
 * @DESCRIPTION : 类目管理 服务组合层.
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 28, 2013
 *       </p>
 **************************************************************** 
 */
public interface CategoryManager {
	List<CategoryModule> getCategoryAll() throws ServiceException;

	List<CategoryModule> getTopCatgoryAll() throws ServiceException;

	List<CategoryModule> getChildrensCatgoryById(Integer parentId) throws ServiceException;
}
