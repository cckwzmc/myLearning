package com.toney.istyle.core.service.impl;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.toney.istyle.bo.ProductClickBo;
import com.toney.istyle.core.exception.ServiceException;
import com.toney.istyle.dao.ProductClickDao;
import com.toney.istyle.module.ProductClickModule;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :ProductStatCacheService.java
 * @DESCRIPTION : 商品统计Repository层
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 20, 2013
 *       </p>
 **************************************************************** 
 */
@Service("productStatRepository")
class ProductStatRepositoryImpl implements ProductStatRepository {
	private static final XLogger LOGGER = XLoggerFactory.getXLogger(ProductStatRepositoryImpl.class);

	@Autowired
	private ProductClickDao productClickDao;

	@Override
	@Cacheable(value = "productClickCache", key = "'productClickId_'+#id.longValue()")
	public ProductClickBo getProductClickById(Long id) throws ServiceException {
		ProductClickModule module = this.productClickDao.selectById(id);
		ProductClickBo bo = null;
		if (module != null) {
			bo = new ProductClickBo();
			try {
				BeanUtils.copyProperties(bo, module);
			} catch (IllegalAccessException e) {
				LOGGER.error("查询商品点击统计数据失败 id:{}", id);
				throw new ServiceException(e);
			} catch (InvocationTargetException e) {
				LOGGER.error("查询商品点击统计数据失败 id:{}", id);
				throw new ServiceException(e);
			}
		}
		return bo;
	}

	@Override
	@Cacheable(value = "productClickCache", key = "'productClickId_'+#id.longValue()")
	public void deleteCache(Long id) throws ServiceException {
	}

}
