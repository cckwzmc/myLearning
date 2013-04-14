package com.toney.istyle.core.category;

import java.util.List;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.toney.istyle.core.exception.RespositoryException;
import com.toney.istyle.dao.CategoryDao;
import com.toney.istyle.module.CategoryModule;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :CategoryRespositoryImpl.java
 * @DESCRIPTION : Category Respository implements
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 28, 2013
 *       </p>
 **************************************************************** 
 */
@Service("categoryRespository")
public class CategoryRespositoryImpl implements CategoryRespository {
	private static final XLogger LOGGER = XLoggerFactory.getXLogger(CategoryRespositoryImpl.class);

	@Autowired
	CategoryDao categoryDao;

	@Override
	@Cacheable(value = "categoryCache", key = "'getCategoryAll_list'")
	public List<CategoryModule> getCategoryAll() throws RespositoryException {
		List<CategoryModule> moduleList = this.categoryDao.selectAll();
		return moduleList;
	}

	@Override
	@CacheEvict(value = "categoryCache", key = "'getCategoryAll_list'")
	public void deleteCahce() throws RespositoryException {
	}
}
