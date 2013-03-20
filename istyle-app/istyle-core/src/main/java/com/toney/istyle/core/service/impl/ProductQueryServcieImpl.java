/**
 * 
 */
package com.toney.istyle.core.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired
	private ProductRepository productRepository;

	@Override
	public ProductBO getProductById(Long id) throws ServiceException {
		return this.productRepository.getProductById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.toney.istyle.core.service.ProductQueryServcie#getProductPage(java
	 * .lang.Integer, java.lang.Integer, java.lang.String)
	 */
	@Override
	public List<ProductBO> getProductPage(Integer startRecord, Integer endRecord, String catCode) throws ServiceException {
		List<Long> idList = this.productDao.selectPageId(startRecord, endRecord, catCode);
		return this.getProductIdList(idList);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.toney.istyle.core.service.ProductQueryServcie#getProductList()
	 */
	@Override
	public List<ProductBO> getProductList() throws ServiceException {
		List<ProductBO> resultList = null;
		return resultList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.toney.istyle.core.service.ProductQueryServcie#getProductIdList(java
	 * .util.List)
	 */
	@Override
	public List<ProductBO> getProductIdList(List<Long> ids) throws ServiceException {
		List<ProductBO> cacheList = new ArrayList<ProductBO>();
		List<Long> queryIds = new ArrayList<Long>();
		// 先从缓存中查找，找不到的放queryIds中再到数据库中查询.
		for (Long id : ids) {
			ProductBO bo = this.getProductById(id);
			if (bo != null) {
				cacheList.add(bo);
				continue;
			}
			queryIds.add(id);
		}
		// 从数据库中查询
		List<ProductBO> queryList = null;
		if (CollectionUtils.isNotEmpty(queryIds)) {
			queryList = this.getProductIdListDB(queryIds);
		}

		List<ProductBO> resultList = new ArrayList<ProductBO>();
		// 按顺序拼装数据.
		for (Long id : ids) {
			boolean isExist = false;
			if (CollectionUtils.isNotEmpty(cacheList)) {
				for (ProductBO bo : cacheList) {
					if (id.longValue() == bo.getId().longValue()) {
						resultList.add(bo);
						isExist = true;
						break;
					}
				}
			}
			if (isExist) {
				continue;
			}
			if (CollectionUtils.isNotEmpty(queryList)) {
				for (ProductBO bo : cacheList) {
					if (id.longValue() == bo.getId().longValue()) {
						resultList.add(bo);
						isExist = true;
						break;
					}
				}
			}
		}
		return resultList;
	}

	/**
	 * 根据ids从数据库中查询
	 * 
	 * @param ids
	 * @return
	 * @throws ServiceException
	 */
	private List<ProductBO> getProductIdListDB(List<Long> ids) throws ServiceException {
		List<ProductBO> resultList = new ArrayList<ProductBO>();
		List<ProductModule> queryList = this.productDao.selectByIds(ids);
		if (CollectionUtils.isNotEmpty(queryList)) {
			try {
				for (ProductModule module : queryList) {
					ProductBO bo = new ProductBO();
					BeanUtils.copyProperties(bo, module);
					resultList.add(bo);
				}
			} catch (IllegalAccessException e) {
				LOGGER.error("getProductIdListDB 查询失败: ids:{}", ids.toString(), e);
				throw new ServiceException(e);
			} catch (InvocationTargetException e) {
				LOGGER.error("getProductIdListDB 查询失败: ids:{}", ids.toString(), e);
				throw new ServiceException(e);
			}
		}
		return resultList;
	}

}
