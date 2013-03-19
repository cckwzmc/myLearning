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
import org.springframework.stereotype.Service;

import com.toney.istyle.bo.ProductBO;
import com.toney.istyle.core.exception.ServiceException;
import com.toney.istyle.core.service.ProductQueryServcie;
import com.toney.istyle.core.service.ProductEventService;
import com.toney.istyle.dao.ProductDao;
import com.toney.istyle.module.ProductModule;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :ProductEventServiceImpl.java
 * @DESCRIPTION :商品写
 * @AUTHOR :ly_zy_ljh
 * @VERSION :v1.0
 * @DATE :2013-3-19
 *       </p>
 **************************************************************** 
 */
@Service("productEventService")
public class ProductEventServiceImpl implements ProductEventService {
	private static final XLogger LOGGER = XLoggerFactory.getXLogger(ProductEventServiceImpl.class);
	@Autowired
	ProductDao productDao;

	@Autowired
	ProductQueryServcie productQueryServcie;
	/* (non-Javadoc)
	 * @see com.toney.istyle.core.service.ProductWriterService#save(com.toney.istyle.bo.ProductBO)
	 */
	@Override
	public void save(ProductBO productBo) throws ServiceException{
		ProductModule module=new ProductModule();
		try {
			BeanUtils.copyProperties(module, productBo);
		} catch (IllegalAccessException e) {
			LOGGER.error("保存商品信息失败 productBo :{}", productBo.toString(),e);
			throw new ServiceException(e);
		} catch (InvocationTargetException e) {
			LOGGER.error("保存商品信息失败 productBo :{}", productBo.toString(),e);
			throw new ServiceException(e);
		}
		this.productDao.insert(module);
	}

	/* (non-Javadoc)
	 * @see com.toney.istyle.core.service.ProductWriterService#delete(java.lang.Long)
	 */
	@Override
	@CacheEvict(value="productCache",key="productId_#id")
	public void delete(Long id) throws ServiceException {
		this.productDao.deleteById(id);
	}
	
	@Override
	public void delete(List<Long> ids) throws ServiceException{
		this.productDao.deleteByIds(ids);
		for(Long id:ids){
			this.deleteCache(id);
		}
	}

	/**
	 * @param id
	 */
	@CacheEvict(value="productCache",key="productId_#id")
	private void deleteCache(Long id) {
		LOGGER.info("Delete product id:{} cache",id);
	}

	/* (non-Javadoc)
	 * @see com.toney.istyle.core.service.ProductWriterService#update(com.toney.istyle.bo.ProductBO)
	 */
	@Override
	public void update(ProductBO productBo) throws ServiceException {
		ProductModule module=new ProductModule();
		try {
			BeanUtils.copyProperties(module, productBo);
		} catch (IllegalAccessException e) {
			LOGGER.error("保存商品信息失败 productBo :{}", productBo.toString(),e);
			throw new ServiceException(e);
		} catch (InvocationTargetException e) {
			LOGGER.error("保存商品信息失败 productBo :{}", productBo.toString(),e);
			throw new ServiceException(e);
		}
		this.productDao.updateById(module);
		this.productQueryServcie.refresh(productBo.getId());
	}
}
