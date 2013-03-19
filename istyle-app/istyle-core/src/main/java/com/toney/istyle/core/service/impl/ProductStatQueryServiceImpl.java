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

import com.toney.istyle.bo.ProductClickBo;
import com.toney.istyle.core.exception.ServiceException;
import com.toney.istyle.core.service.ProductStatQueryService;
import com.toney.istyle.dao.ProductClickDao;
import com.toney.istyle.module.ProductClickModule;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :ProductStatQueryServiceImpl.java
 * @DESCRIPTION :TODO
 * @AUTHOR :ly_zy_ljh
 * @VERSION :v1.0
 * @DATE :2013-3-19
 *       </p>
 **************************************************************** 
 */
@Service("productStatQueryService")
public class ProductStatQueryServiceImpl implements ProductStatQueryService {

	private static final XLogger LOGGER = XLoggerFactory.getXLogger(ProductStatQueryServiceImpl.class);

	@Autowired
	private ProductClickDao productClickDao;
	
	@Override
	@Cacheable(value="productClickCache",key="productClickId_#id")
	public ProductClickBo getProductClickById(Long id) throws ServiceException {
		ProductClickModule module=this.productClickDao.selectById(id);
		ProductClickBo bo=null;
		if(module!=null){
			bo=new ProductClickBo();
			try {
				BeanUtils.copyProperties(bo, module);
			} catch (IllegalAccessException e) {
				LOGGER.error("查询商品点击统计数据失败 id:{}",id);
				throw new ServiceException(e);
			} catch (InvocationTargetException e) {
				LOGGER.error("查询商品点击统计数据失败 id:{}",id);
				throw new ServiceException(e);
			}
		}
		return bo;
	}

	@Override
	@Cacheable(value="productClickCache",key="productClickId_#id")
	public void refresh(Long id) throws ServiceException{
		this.getProductClickById(id);
	}
}
