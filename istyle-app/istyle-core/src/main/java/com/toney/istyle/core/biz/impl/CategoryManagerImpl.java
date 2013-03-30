package com.toney.istyle.core.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toney.istyle.core.biz.CategoryManager;
import com.toney.istyle.core.category.CategoryRespository;
import com.toney.istyle.core.exception.RespositoryException;
import com.toney.istyle.core.exception.ServiceException;
import com.toney.istyle.module.CategoryModule;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :CategoryManagerImpl.java
 * @DESCRIPTION : 类目管理 服务组合层. 实现
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 28, 2013
 *       </p>
 **************************************************************** 
 */
@Service("categoryManager")
public class CategoryManagerImpl implements CategoryManager {
	private static final XLogger LOGGER = XLoggerFactory.getXLogger(CategoryManagerImpl.class);

	@Autowired
	CategoryRespository categoryRespository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.toney.istyle.core.category.CategoryQueryService#getCategoryAll()
	 */
	@Override
	public List<CategoryModule> getCategoryAll() throws ServiceException {
		try {
			return this.categoryRespository.getCategoryAll();
		} catch (RespositoryException e) {
			LOGGER.error("查询所有的类目失败", e);
			throw new ServiceException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.toney.istyle.core.category.CategoryQueryService#getTopCatgoryAll()
	 */
	@Override
	public List<CategoryModule> getTopCatgoryAll() throws ServiceException {
		List<CategoryModule> catAllList = this.getCategoryAll();
		List<CategoryModule> catTopList = new ArrayList<CategoryModule>();
		for (CategoryModule bo : catAllList) {
			if (bo.getParentId() == null || bo.getParentId() == 0) {
				catTopList.add(bo);
			}
		}
		return catTopList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.toney.istyle.core.category.CategoryQueryService#getChildrensCatgoryById
	 * (java.lang.Integer)
	 */
	@Override
	public List<CategoryModule> getChildrensCatgoryById(Integer parentId) throws ServiceException {
		List<CategoryModule> catChildrenList = new ArrayList<CategoryModule>();
		List<CategoryModule> catAllList = this.getCategoryAll();
		for (CategoryModule bo : catAllList) {
			if (bo.getParentId() != null && bo.getParentId() == parentId.intValue()) {
				catChildrenList.add(bo);
			}
		}
		return catChildrenList;
	}

}
