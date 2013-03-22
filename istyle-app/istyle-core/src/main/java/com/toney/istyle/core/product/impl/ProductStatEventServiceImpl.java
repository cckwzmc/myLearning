/**
 * 
 */
package com.toney.istyle.core.product.impl;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.toney.istyle.bo.ProductClickBO;
import com.toney.istyle.core.exception.ServiceException;
import com.toney.istyle.core.product.ProductStatEventService;
import com.toney.istyle.dao.ProductClickDao;
import com.toney.istyle.module.ProductClickModule;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :ProductStatEventServiceImpl.java
 * @DESCRIPTION :商品统计数据写事件
 * @AUTHOR :ly_zy_ljh
 * @VERSION :v1.0
 * @DATE :2013-3-20
 *       </p>
 **************************************************************** 
 */
@Service("productStatEventService")
public class ProductStatEventServiceImpl implements ProductStatEventService {

	private static final XLogger LOGGER = XLoggerFactory.getXLogger(ProductStatEventServiceImpl.class);

	@Autowired
	ProductClickDao productClickDao;
	@Autowired
	ProductStatRepository productRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.toney.istyle.core.service.ProductStatEventService#create(com.toney
	 * .istyle.bo.ProductClickBo)
	 */
	@Override
	public void create(ProductClickBO productClickBo) throws ServiceException {
		Assert.notNull(productClickBo);
		Assert.isTrue(productClickBo.getPid() > 0);
		ProductClickModule productClickModule = new ProductClickModule();
		try {
			BeanUtils.copyProperties(productClickModule, productClickBo);
		} catch (IllegalAccessException e) {
			LOGGER.error("商品统计数据入库失败 module:{}", productClickModule.toString(), e);
			throw new ServiceException(e);
		} catch (InvocationTargetException e) {
			LOGGER.error("商品统计数据入库失败 module:{}", productClickModule.toString(), e);
			throw new ServiceException(e);
		}
		this.productClickDao.insert(productClickModule);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.toney.istyle.core.service.ProductStatEventService#delete(java.lang
	 * .Long)
	 */
	@Override
	public void delete(Long id) throws ServiceException {
		this.productClickDao.deleteById(id);
		this.productRepository.deleteCache(id);

	}

	/**
	 * @param productClickBo
	 * @throws ServiceException
	 */
	@Override
	public void update(ProductClickBO productClickBo) throws ServiceException {
		Assert.notNull(productClickBo);
		Assert.isTrue(productClickBo.getPid() > 0);
		ProductClickModule productClickModule = new ProductClickModule();
		try {
			BeanUtils.copyProperties(productClickModule, productClickBo);
		} catch (IllegalAccessException e) {
			LOGGER.error("商品统计数据入库失败 module:{}", productClickModule.toString(), e);
			throw new ServiceException(e);
		} catch (InvocationTargetException e) {
			LOGGER.error("商品统计数据入库失败 module:{}", productClickModule.toString(), e);
			throw new ServiceException(e);
		}
		this.productClickDao.updateById(productClickModule);
		this.refesh(productClickBo.getPid());
	}

	/**
	 * 更新数据一定要更新缓存.
	 * 
	 * @param id
	 * @throws ServiceException
	 */
	private void refesh(Long id) throws ServiceException {
		this.productRepository.deleteCache(id);
		this.productRepository.getProductClickById(id);
	}
}
