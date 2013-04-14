package com.toney.istyle.core.biz.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toney.istyle.core.biz.CategoryManager;
import com.toney.istyle.core.category.CategoryEventService;
import com.toney.istyle.core.category.CategoryRespository;
import com.toney.istyle.core.exception.ManagerException;
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
	@Autowired
	CategoryEventService categoryEventService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.toney.istyle.core.category.CategoryQueryService#getCategoryAll()
	 */
	@Override
	public List<CategoryModule> getCategoryAll() throws ManagerException {
		try {
			return this.categoryRespository.getCategoryAll();
		} catch (RespositoryException e) {
			LOGGER.error("查询所有的类目失败", e);
			throw new ManagerException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.toney.istyle.core.category.CategoryQueryService#getTopCatgoryAll()
	 */
	@Override
	public List<CategoryModule> getTopCatgoryAll() throws ManagerException {
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
	 * @see com.toney.istyle.core.category.CategoryQueryService#getChildrensCatgoryById (java.lang.Integer)
	 */
	@Override
	public List<CategoryModule> getChildrensCatgoryByCatCode(String catCode) throws ManagerException {
		List<CategoryModule> catAllList = this.getCategoryAll();
		CategoryModule currentModule = null;
		for (CategoryModule bo : catAllList) {
			if (bo.getCatCode().equals(catCode)) {
				currentModule = new CategoryModule();
				currentModule = bo;
			}
		}
		if (currentModule == null) {
			return null;
		}
		List<CategoryModule> catChildrenList = new ArrayList<CategoryModule>();
		for (CategoryModule bo : catAllList) {
			if (bo.getParentId() != null && bo.getParentId() == currentModule.getId().intValue()) {
				catChildrenList.add(bo);
			}
		}
		return catChildrenList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.toney.istyle.core.biz.CategoryManager#getCategoryAllByCatCode(java.lang.String)
	 */
	@Override
	public List<CategoryModule> getCategoryAllByCatCode(String catCode) throws ManagerException {
		List<CategoryModule> catAllList = this.getCategoryAll();
		CategoryModule currentModule = null;
		for (CategoryModule bo : catAllList) {
			if (bo.getCatCode().equals(catCode)) {
				currentModule = new CategoryModule();
				currentModule = bo;
			}
		}
		if (currentModule == null) {
			return null;
		}
		List<CategoryModule> catChildrenList = new ArrayList<CategoryModule>();
		catChildrenList.add(currentModule);
		for (CategoryModule bo : catAllList) {
			if (bo.getParentId() != null && bo.getParentId() == currentModule.getId().intValue()) {
				catChildrenList.add(bo);
			}
		}
		return catChildrenList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.toney.istyle.core.biz.CategoryManager#addCategory(java.lang.String, java.lang.String)
	 */
	@Override
	public void addCategory(String parentCatCode, String catName) throws ManagerException {
		List<CategoryModule> catAllList = this.getCategoryAll();
		CategoryModule currentModule = null;
		for (CategoryModule bo : catAllList) {
			if (bo.getCatCode().equals(parentCatCode)) {
				currentModule = new CategoryModule();
				currentModule = bo;
			}
		}
		try {
			Integer catCode = 0;
			String newCatCode = "";
			for (CategoryModule bo : catAllList) {
				if ((currentModule == null || currentModule.getParentId() == null) && bo.getParentId() == null && NumberUtils.toInt(bo.getCatCode()) > catCode.intValue()) {
					catCode = NumberUtils.toInt(bo.getCatCode());
					newCatCode = bo.getCatCode();
				} else if (currentModule != null && currentModule.getParentId() != null && bo.getParentId() != null && bo.getParentId() == currentModule.getId().intValue()
						&& NumberUtils.toInt(bo.getCatCode()) > catCode.intValue()) {
					catCode = NumberUtils.toInt(bo.getCatCode());
					newCatCode = bo.getCatCode();
				}
			}
			catCode++;
			newCatCode = (newCatCode.startsWith("0") ? "0" + String.valueOf(catCode) : String.valueOf(catCode));
			CategoryModule module = new CategoryModule();
			if (currentModule != null) {
				module.setParentId(currentModule.getId());
			}
			module.setCatName(catName);
			module.setCreateDate(new Date());
			module.setCatCode(newCatCode);
			this.categoryEventService.create(module);
		} catch (ServiceException e) {
			LOGGER.error("", e);
			throw new ManagerException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.toney.istyle.core.biz.CategoryManager#getCategoryPathByChildrenId(java.lang.String)
	 */
	@Override
	public List<CategoryModule> getCategoryPathByChildrenId(String parentCatCode) throws ManagerException {
		try {
			if (StringUtils.isBlank(parentCatCode)) {
				return null;
			}
			List<CategoryModule> catAllList = this.getCategoryAll();
			CategoryModule currentModule = null;
			for (CategoryModule bo : catAllList) {
				if (bo.getCatCode().equals(parentCatCode)) {
					currentModule = new CategoryModule();
					currentModule = bo;
					break;
				}
			}
			if (currentModule == null) {
				return null;
			}
			List<CategoryModule> pathList = new ArrayList<CategoryModule>();
			CategoryModule secondModule = null;
			for (CategoryModule bo : catAllList) {
				if (currentModule.getParentId() != null && bo.getId().intValue() == currentModule.getParentId()) {
					secondModule = new CategoryModule();
					secondModule = bo;
					break;
				}
			}
			// CategoryModule thirdModule=null;
			// if(secondModule!=null){
			// for(CategoryModule bo:catAllList){
			// if(secondModule.getParentId()!=null&&bo.getId().intValue()==secondModule.getParentId()){
			// thirdModule = new CategoryModule();
			// thirdModule=bo;
			// break;
			// }
			// }
			// }
			// if(thirdModule!=null){
			// pathList.add(thirdModule);
			// }
			if (secondModule != null) {
				pathList.add(secondModule);
			}
			if (currentModule != null) {
				pathList.add(currentModule);
			}
			return pathList;
		} catch (ManagerException e) {
			LOGGER.error("", e);
			throw new ManagerException(e);
		}

	}

}
