package com.toney.istyle.core.biz;

import java.util.List;

import com.toney.istyle.core.exception.ManagerException;
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
	List<CategoryModule> getCategoryAll() throws  ManagerException;

	List<CategoryModule> getTopCatgoryAll() throws ManagerException;

	List<CategoryModule> getChildrensCatgoryByCatCode(String catCode) throws ManagerException;

	/**
	 * 查询catcode 自身和下一级分类
	 * @param catCode
	 * @return
	 */
	List<CategoryModule> getCategoryAllByCatCode(String catCode) throws ManagerException;

	/**
	 * 新增分类
	 * @param parentCatCode
	 * @param catName
	 * @throws ManagerException 
	 */
	void addCategory(String parentCatCode, String catName) throws ManagerException;

	List<CategoryModule> getCategoryPathByChildrenId(String catCode) throws ManagerException;
}
