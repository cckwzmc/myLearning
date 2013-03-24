package com.toney.istyle.core.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.Constants;
import com.toney.istyle.bo.AreaBO;
import com.toney.istyle.bo.ProductBO;
import com.toney.istyle.bo.ProductClickBO;
import com.toney.istyle.bo.ProductPicBO;
import com.toney.istyle.bo.UserBO;
import com.toney.istyle.commons.Page;
import com.toney.istyle.core.biz.ProductManager;
import com.toney.istyle.core.exception.ManagerException;
import com.toney.istyle.core.exception.ServiceException;
import com.toney.istyle.core.product.ProductPicQueryService;
import com.toney.istyle.core.product.ProductQueryServcie;
import com.toney.istyle.core.product.ProductStatQueryService;
import com.toney.istyle.core.system.AreaQueryService;
import com.toney.istyle.core.user.UserQueryService;
import com.toney.istyle.dao.ProductDao;
import com.toney.istyle.form.ProductForm;
import com.toney.istyle.form.ProductPicForm;
import com.toney.istyle.form.ProductStatForm;

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
	@Autowired
	private ProductPicQueryService productPicQueryService;
	@Autowired
	AreaQueryService areaQueryService;
	@Autowired
	UserQueryService userQueryService;

	@Override
	public Page<ProductForm> getProductInfoByCatCode(Integer page, String catCode) throws ManagerException {
		Page<ProductForm> pageForm = new Page<ProductForm>();
		try {
			pageForm.setPageNo(page);
			List<ProductBO> list = this.productQueryServcie.getProductPage(pageForm.getFirstRecord(), pageForm.getPageSize(), catCode + "%");
			if (CollectionUtils.isNotEmpty(list)) {
				List<ProductForm> formList = new ArrayList<ProductForm>();
				for (ProductBO productBO : list) {
					ProductPicBO productPicBO = productPicQueryService.getProductMasterPic(productBO.getId());
					ProductClickBO statBo = productStatQueryService.getProductClickById(productBO.getId());
					AreaBO areaBO = this.areaQueryService.getAreaById(productBO.getCityId());
					UserBO userBO=this.userQueryService.getUserById(productBO.getUploadUid());
					ProductForm form = wrapProductListForm(productBO, productPicBO, statBo, areaBO,userBO);
					formList.add(form);
				}
				pageForm.setResult(formList);
			}
		} catch (ServiceException e) {
			LOGGER.error("分页查询数据失败:page:{} pageSize:{} catCode:{}", new Object[] { page, pageForm.getPageSize(), catCode }, e);
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
	 * TODO 单个商品的封装.
	 * 
	 * @param bo
	 */
	private void wrapProductForm(ProductBO bo) {
		// TODO Auto-generated method stub

	}

	/**
	 * TODO 待完善. 列表页
	 * 
	 * @param bo
	 * @param productPicBO
	 * @param statBo
	 * @param areaBO
	 * @param userBO 
	 * @return
	 */
	private ProductForm wrapProductListForm(ProductBO bo, ProductPicBO productPicBO, ProductClickBO statBo, AreaBO areaBO, UserBO userBO) {
		ProductForm form = new ProductForm();
		form.setId(bo.getId());
		String lastUpdate = "";
		try {
			lastUpdate = DateFormatUtils.format(bo.getLastUpdate(), com.toney.istyle.constants.Constants.DATE_FORMAT_STYLE);
		} catch (Exception e) {
			LOGGER.error("wrapProductListForm", e);
		}

		form.setLastUpdate(lastUpdate);
		form.setProduceCode(bo.getProduceCode());
		form.setProductName(bo.getProductName());
		form.setProductUrl(bo.getProductUrl());
		form.setPlatformCode(bo.getPlatformCode());
		form.setCityName(areaBO.getName());	
		form.setDescription(bo.getDescription());
		List<ProductPicForm> list = new ArrayList<ProductPicForm>();
		ProductPicForm picForm = new ProductPicForm();
		picForm.setUrl(productPicBO.getUrl());
		picForm.setHeight(productPicBO.getHeight());
		picForm.setWidth(productPicBO.getWidth());
		list.add(picForm);
		form.setProductPicForm(list);
		ProductStatForm clickForm = new ProductStatForm();
		clickForm.setTotalClick(statBo.getTotalClick());
		clickForm.setCommentNumber(statBo.getCommentNumber());
		clickForm.setLikeNumber(statBo.getLikeNumber());
		clickForm.setRecommendNumber(statBo.getRecommendNumber());
		form.setProductStatForm(clickForm);
		form.setNickName(userBO.getNickName());
		return form;
	}

}
