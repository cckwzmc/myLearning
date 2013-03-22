package com.toney.istyle.core.product.impl;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.toney.istyle.bo.ProductBO;
import com.toney.istyle.core.exception.ServiceException;
import com.toney.istyle.dao.ProductDao;
import com.toney.istyle.module.ProductModule;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :ProductCacheServcieImpl.java
 * @DESCRIPTION : 商品缓存实现类.
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 20, 2013
 *       </p>
 **************************************************************** 
 */
@Service("productRepository")
class ProductRepositoryImpl implements ProductRepository {

	private static final XLogger LOGGER = XLoggerFactory.getXLogger(ProductRepositoryImpl.class);

	@Autowired
	private ProductDao productDao;

	@Override
	@Cacheable(value = "productCache", key = "'productId_'+#id.longValue()")
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
	@CacheEvict(value = "productCache", key = "'productId_'+#id.longValue()")
	public void deleteCache(Long id) throws ServiceException {

	}

}
