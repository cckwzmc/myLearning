package com.toney.istyle.core.category;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toney.istyle.core.exception.RespositoryException;
import com.toney.istyle.core.exception.ServiceException;
import com.toney.istyle.dao.CategoryDao;
import com.toney.istyle.module.CategoryModule;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :CategoryEventServiceImpl.java
 * @DESCRIPTION : Category Event Service : create update delete
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 29, 2013
 *       </p>
 **************************************************************** 
 */
@Service("categoryEventService")
public class CategoryEventServiceImpl implements CategoryEventService {
	private static final XLogger LOGGER = XLoggerFactory.getXLogger(CategoryEventServiceImpl.class);

	@Autowired
	CategoryDao categoryDao;
	@Autowired
	CategoryRespository categoryRespository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.toney.istyle.core.category.CategoryEventService#create(com.toney.istyle.module.CategoryModule)
	 */
	@Override
	public void create(CategoryModule module) throws ServiceException {
		this.categoryDao.insert(module);
		try {
			this.categoryRespository.deleteCahce();
		} catch (RespositoryException e) {
			throw new ServiceException(e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.toney.istyle.core.category.CategoryEventService#delete(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws ServiceException {
		this.categoryDao.deleteById(id);
		try {
			this.categoryRespository.deleteCahce();
		} catch (RespositoryException e) {
			throw new ServiceException(e);
		}
	}

}
