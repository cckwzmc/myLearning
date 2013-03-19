/**
 * 
 */
package com.toney.istyle.core.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.toney.istyle.bo.ProductBO;
import com.toney.istyle.core.exception.ServiceException;
import com.toney.istyle.core.service.ProductQueryServcie;
import com.toney.istyle.dao.ProductDao;
import com.toney.istyle.module.ProductModule;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :ProductReadServcieImpl.java
 * @DESCRIPTION :商品读取服务类.
 * @AUTHOR :ly_zy_ljh
 * @VERSION :v1.0
 * @DATE :2013-3-19
 *       </p>
 **************************************************************** 
 */
@Service("productQueryServcie")
public class ProductQueryServcieImpl implements ProductQueryServcie {
	private static final XLogger LOGGER = XLoggerFactory.getXLogger(ProductQueryServcieImpl.class);

	@Autowired
	private ProductDao productDao;

	@Override
	@Cacheable(value = "productCache", key = "productId_#id")
	public ProductBO getProductById(Long id) throws ServiceException {
		ProductModule productModule = this.productDao.selectById(id);
		ProductBO bo = null;
		if (productModule != null) {
			bo = new ProductBO();
			try {
				BeanUtils.copyProperties(bo, productModule);
			} catch (IllegalAccessException e) {
				LOGGER.error("getProductById 查询商品信息失败 id:{}", id, e);
				throw new ServiceException(e);
			} catch (InvocationTargetException e) {
				LOGGER.error("getProductById 查询商品信息失败 id:{}", id, e);
				throw new ServiceException(e);
			}
		}
		return bo;
	}

	@Override
	@CacheEvict(value = "productCache", key = "productId_#id")
	public void refresh(Long id) throws ServiceException {
		this.getProductById(id);
	}

	@Override
	public List<ProductBO> getProductList() throws ServiceException {
		List<ProductBO> resultList = null;
		return resultList;
	}

}
