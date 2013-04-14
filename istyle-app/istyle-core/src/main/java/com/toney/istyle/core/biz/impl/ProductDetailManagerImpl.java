package com.toney.istyle.core.biz.impl;

import java.util.Date;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toney.istyle.bo.ProductBO;
import com.toney.istyle.core.biz.ProductDetailManager;
import com.toney.istyle.core.exception.ManagerException;
import com.toney.istyle.core.exception.RespositoryException;
import com.toney.istyle.core.exception.ServiceException;
import com.toney.istyle.core.product.ProductEventService;
import com.toney.istyle.core.system.KeyTableEventService;
import com.toney.istyle.core.system.KeyTableRespository;
import com.toney.istyle.core.util.ProductUtil;
import com.toney.istyle.form.ProductBaseForm;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :ProductDetailManagerImpl.java
 * @DESCRIPTION : 商品明细管理实现。
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Apr 8, 2013
 *       </p>
 **************************************************************** 
 */
@Service("productDetailManager")
public class ProductDetailManagerImpl implements ProductDetailManager {

	private static final String PRODUCT_ID_KEY = "PRODUCT_ID_KEY";

	private static final XLogger LOGGER = XLoggerFactory.getXLogger(ProductDetailManagerImpl.class);

	@Autowired
	ProductEventService productEventService;
	@Autowired
	KeyTableRespository keyTableRespository;
	@Autowired
	KeyTableEventService keyTableEventService;

	@Override
	public void ceateBaseInfo(ProductBaseForm form) throws ManagerException {

		int onSale = 0;
		try {
			ProductBO productBo = wrapProductBO(form, onSale);
			this.productEventService.save(productBo);
		} catch (ServiceException e) {
			LOGGER.error("创建商品基本信息失败", e);
			throw new ManagerException(e);
		} catch (RespositoryException e) {
			LOGGER.error("创建商品基本信息失败", e);
			throw new ManagerException(e);
		}
	}

	/**
	 * ProductBaseForm convert ProductBo;
	 * 
	 * @param form
	 * @param onSale
	 * @throws RespositoryException
	 * @throws ServiceException
	 */
	private ProductBO wrapProductBO(ProductBaseForm form, Integer onSale) throws RespositoryException, ServiceException {
		if (form != null) {
			ProductBO productBo = new ProductBO();
			productBo.setCatCode(form.getCatCode());
			productBo.setCityId(form.getCityId());
			productBo.setCreateDate(new Date());
			productBo.setLastUpdate(new Date());
			productBo.setOnsale(onSale.shortValue());
			productBo.setPlatformCode(form.getPlatFormCode());
			Long productCodeKey = this.keyTableRespository.getValue(PRODUCT_ID_KEY);
			++productCodeKey;
			this.keyTableEventService.updateKeyValue(PRODUCT_ID_KEY, productCodeKey);
			productBo.setProduceCode(ProductUtil.genProductCode(form.getPlatFormCode(), productCodeKey));
			productBo.setProductName(form.getProductName());
			productBo.setProductUrl(form.getUrlDetail());
			productBo.setUploadUid(form.getUploadUid());
			return productBo;
		}
		return null;

	}
}
