package com.toney.istyle.core.product.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

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
	@Cacheable(value="productPicCache",key="'productPicId_'+#pid")
	public List<ProductPicBO> getProductPicById(Long pid) throws ServiceException {
		List<ProductPicModule> moduleList=this.productPicDao.selectByPid(pid);
		if(moduleList!=null){
			try {
				List<ProductPicBO> boList=new ArrayList<ProductPicBO>();
				for(ProductPicModule m: moduleList){
					ProductPicBO bo=new ProductPicBO();
					BeanUtils.copyProperties(bo, m);
					boList.add(bo);
				}
				return boList;
			} catch (IllegalAccessException e) {
				LOGGER.error("商品图片查询失败 id:{}", pid,e);
				throw new ServiceException(e);
			} catch (InvocationTargetException e) {
				LOGGER.error("商品图片查询失败 id:{}", pid,e);
				throw new ServiceException(e);
			}
		}
		return null;
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
