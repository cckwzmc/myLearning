package com.toney.istyle.core.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toney.istyle.bo.ProductBO;
import com.toney.istyle.commons.Page;
import com.toney.istyle.core.biz.ProductManager;
import com.toney.istyle.core.exception.ManagerException;
import com.toney.istyle.core.exception.ServiceException;
import com.toney.istyle.core.service.ProductQueryServcie;
import com.toney.istyle.core.service.ProductStatQueryService;
import com.toney.istyle.dao.ProductDao;
import com.toney.istyle.form.ProductForm;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :ProductManagerImpl
 * @DESCRIPTION :商品管理
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 10, 2013
 *       </p>
 **************************************************************** 
 */
@Service("productManager")
public class ProductManagerImpl implements ProductManager {

	private static final XLogger LOGGER = XLoggerFactory.getXLogger(ProductManagerImpl.class);

	@Autowired
	private ProductDao productDao;

	@Autowired
	private ProductQueryServcie productQueryServcie;
	@Autowired
	private ProductStatQueryService productStatQueryService;

	@Override
	public Page<ProductForm> getProductInfoByCatCode(Integer page, Integer pageSize, String catCode) throws ManagerException {
		Page<ProductForm> pageForm = new Page<ProductForm>();
		try {
			pageForm.setPageNo(page);
			List<ProductBO> list = this.productQueryServcie.getProductPage(pageForm.getFirstRecord(), pageSize, catCode + "%");
			if (CollectionUtils.isNotEmpty(list)) {
				List<ProductForm> formList = new ArrayList<ProductForm>();
				for (ProductBO bo : list) {
					ProductForm form = wrapProductForm(bo);
					formList.add(form);
				}
				pageForm.setResult(formList);
			}
		} catch (ServiceException e) {
			LOGGER.error("分页查询数据失败:page:{} pageSize:{} catCode:{}", new Object[] { page, pageSize, catCode }, e);
			throw new ManagerException(e);
		}
		return pageForm;
	}

	@Override
	public ProductForm getProductInfoById(Long id) throws ManagerException {
		ProductForm form = new ProductForm();
		try {
			ProductBO bo = this.productQueryServcie.getProductById(id);
			this.wrapProductForm(bo);
		} catch (ServiceException e) {
			LOGGER.error("分页查询数据失败:id{}", new Object[] { id }, e);
			throw new ManagerException(e);
		}

		return form;
	}

	/**
	 * TODO 待完善.
	 * 
	 * @param bo
	 * @return
	 */
	private ProductForm wrapProductForm(ProductBO bo) {
		ProductForm form = new ProductForm();
		form.setId(bo.getId());
		form.setLastUpdate(bo.getLastUpdate());
		form.setProduceCode(bo.getProduceCode());
		form.setProductName(bo.getProductName());
		form.setProductUrl(bo.getProductUrl());
		form.setPlatformCode(bo.getPlatformCode());
		return form;
	}

}
