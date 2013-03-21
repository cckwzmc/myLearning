package com.toney.istyle.core.service.impl;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.toney.istyle.bo.ProductPicBO;
import com.toney.istyle.core.exception.ServiceException;
import com.toney.istyle.dao.ProductPicDao;
import com.toney.istyle.module.ProductPicModule;

/**
 *************************************************************** 
 * <p>
 * @CLASS :ProductPicRepositoryImpl.java
 * @DESCRIPTION : 商品图片Repository实现
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 20, 2013
 *       </p>
 **************************************************************** 
 */
@Service("productPicRepository")
class ProductPicRepositoryImpl implements ProductPicRepository {
	
	private static final XLogger LOGGER = XLoggerFactory.getXLogger(ProductPicRepositoryImpl.class);
	
	@Autowired
	private ProductPicDao productPicDao;
	
	/* (non-Javadoc)
	 * @see com.toney.istyle.core.service.impl.ProductPicRepository#getProductPicById(java.lang.Long)
	 */
	@Override
	@Cacheable(value="productPicCache",key="'productPicId_'+#id")
	public ProductPicBO getProductPicById(Long id) throws ServiceException {
		ProductPicModule module=this.productPicDao.selectById(id);
		ProductPicBO bo=null;
		if(module!=null){
			try {
				BeanUtils.copyProperties(bo, module);
			} catch (IllegalAccessException e) {
				LOGGER.error("商品图片查询失败 id:{}", id,e);
				throw new ServiceException(e);
			} catch (InvocationTargetException e) {
				LOGGER.error("商品图片查询失败 id:{}", id,e);
				throw new ServiceException(e);
			}
		}
		return bo;
	}
	
	/* (non-Javadoc)
	 * @see com.toney.istyle.core.service.impl.ProductPicRepository#deleteCache(java.lang.Long)
	 */
	@Override
	@CacheEvict(value="productPicCache",key="'productPicId_'+#id")
	public void deleteCache(Long id) throws ServiceException
	{
		
	}
}
