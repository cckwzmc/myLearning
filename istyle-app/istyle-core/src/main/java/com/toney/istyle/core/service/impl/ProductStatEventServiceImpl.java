/**
 * 
 */
package com.toney.istyle.core.service.impl;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.toney.istyle.bo.ProductClickBo;
import com.toney.istyle.core.exception.ServiceException;
import com.toney.istyle.core.service.ProductStatEventService;
import com.toney.istyle.core.service.ProductStatQueryService;
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
	ProductStatQueryService productStatQueryService;
	@Override
	public void create(ProductClickBo productClickBo) throws ServiceException{
		Assert.notNull(productClickBo);
		Assert.isTrue(productClickBo.getPid()>0);
		ProductClickModule productClickModule=new ProductClickModule();
		try {
			BeanUtils.copyProperties(productClickModule, productClickBo);
		} catch (IllegalAccessException e) {
			LOGGER.error("商品统计数据入库失败 module:{}", productClickModule.toString(),e);
			throw new ServiceException(e);
		} catch (InvocationTargetException e) {
			LOGGER.error("商品统计数据入库失败 module:{}", productClickModule.toString(),e);
			throw new ServiceException(e);
		}
		this.productClickDao.insert(productClickModule);
	}
	
	/* (non-Javadoc)
	 * @see com.toney.istyle.core.service.ProductStatEventService#delete(java.lang.Long)
	 */
	@Override
	@Cacheable(value="productClickCache",key="productClickId_#id")
	public void delete(Long id) throws ServiceException
	{
		this.productClickDao.deleteById(id);
	}
	/* (non-Javadoc)
	 * @see com.toney.istyle.core.service.ProductStatEventService#deleteCache(java.lang.Long)
	 */
	@Override
	@Cacheable(value="productClickCache",key="productClickId_#id")
	public void deleteCache(Long id) throws ServiceException
	{
		LOGGER.info("Delect productClick Cache by Id,Id :{}",id);
	}
	
	/**
	 * @param productClickBo
	 * @throws ServiceException
	 */
	@Override
	public void update(ProductClickBo productClickBo) throws ServiceException
	{
		Assert.notNull(productClickBo);
		Assert.isTrue(productClickBo.getPid()>0);
		ProductClickModule productClickModule=new ProductClickModule();
		try {
			BeanUtils.copyProperties(productClickModule, productClickBo);
		} catch (IllegalAccessException e) {
			LOGGER.error("商品统计数据入库失败 module:{}", productClickModule.toString(),e);
			throw new ServiceException(e);
		} catch (InvocationTargetException e) {
			LOGGER.error("商品统计数据入库失败 module:{}", productClickModule.toString(),e);
			throw new ServiceException(e);
		}
		this.productClickDao.updateById(productClickModule);
		this.productStatQueryService.refresh(productClickBo.getPid());
	}
}
