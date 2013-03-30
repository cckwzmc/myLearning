package com.toney.istyle.core.category;

import java.util.List;

import com.toney.istyle.core.exception.RespositoryException;
import com.toney.istyle.module.CategoryModule;

/**
 *************************************************************** 
 * <p>
 * @CLASS :CategoryRespository.java
 * @DESCRIPTION : Category Respository 接口
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 28, 2013
 *       </p>
 **************************************************************** 
 */
public interface CategoryRespository {

	List<CategoryModule> getCategoryAll() throws RespositoryException;

	void deleteCahce() throws RespositoryException;

}
