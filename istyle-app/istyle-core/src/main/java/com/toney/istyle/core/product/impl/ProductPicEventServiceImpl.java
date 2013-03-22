package com.toney.istyle.core.product.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.toney.istyle.bo.ProductBO;
import com.toney.istyle.core.exception.ServiceException;
import com.toney.istyle.core.product.ProductPicEventService;
import com.toney.istyle.dao.ProductPicDao;
import com.toney.istyle.module.ProductPicModule;

/**
 *************************************************************** 
 * <p>
 * @CLASS :ProductPicEventService.java
 * @DESCRIPTION : 图片写操作事件
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 20, 2013
 *       </p>
 **************************************************************** 
 */
public class ProductPicEventServiceImpl implements ProductPicEventService{
	
	private static final XLogger LOGGER = XLoggerFactory.getXLogger(ProductPicEventService.class);
	
	@Autowired
	private ProductPicDao productPicDao;
	
	@Autowired
	private ProductPicRepository productPicRepository;
	
	/**
	 * @param productBo
	 * @throws ServiceException
	 */
	@Override
	public void create(ProductBO productBo) throws ServiceException{
		ProductPicModule module=new ProductPicModule();
		try {
			BeanUtils.copyProperties(module, productBo);
		} catch (IllegalAccessException e) {
			LOGGER.error("插入商品图片失败 module:", module.toString(),e);
			throw new ServiceException(e);
		} catch (InvocationTargetException e) {
			LOGGER.error("插入商品图片失败 module:", module.toString(),e);
			throw new ServiceException(e);
		}
		productPicDao.insert(module);
	}
	
	/**
	 * @param id
	 * @throws ServiceException
	 */
	@Override
	public void delete(Long id) throws ServiceException
	{
		this.productPicDao.deleteById(id);
		this.productPicRepository.deleteCache(id);
	}
	/**
	 * @param ids
	 * @throws ServiceException
	 */
	@Override
	public void delete(List<Long> ids) throws ServiceException
	{
		this.productPicDao.deleteByIds(ids);
		for (Long id:ids) {
			this.productPicRepository.deleteCache(id);
		}
	}
}
