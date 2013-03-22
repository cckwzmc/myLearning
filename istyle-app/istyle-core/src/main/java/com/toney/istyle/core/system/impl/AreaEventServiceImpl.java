package com.toney.istyle.core.system.impl;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toney.istyle.bo.AreaBO;
import com.toney.istyle.core.exception.RespositoryException;
import com.toney.istyle.core.exception.ServiceException;
import com.toney.istyle.core.system.AreaEventService;
import com.toney.istyle.dao.AreaDao;
import com.toney.istyle.module.AreaModule;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :AreaEventServiceImpl.java
 * @DESCRIPTION : AreaEventServiceImpl
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 22, 2013
 *       </p>
 **************************************************************** 
 */
@Service("areaEventService")
public class AreaEventServiceImpl implements AreaEventService {
	private static final XLogger LOGGER = XLoggerFactory.getXLogger(AreaEventServiceImpl.class);

	@Autowired
	AreaRespository areaRespository;

	@Autowired
	AreaDao areaDao;

	@Override
	public void create(AreaBO bo) throws ServiceException {
		AreaModule areaModule = new AreaModule();
		try {
			BeanUtils.copyProperties(areaModule, bo);
		} catch (IllegalAccessException e) {
			LOGGER.error("插入地市信息失败,modeule:{}", areaModule,e);
			throw new ServiceException(e);
		} catch (InvocationTargetException e) {
			LOGGER.error("插入地市信息失败,modeule:{}", areaModule,e);
			throw new ServiceException(e);
		}
		this.areaDao.insert(areaModule);
		this.refreshCache();
	}

	@Override
	public void deleteById(Integer id) throws ServiceException{
		this.areaDao.deleteById(id);
		this.refreshCache();
	}
	
	/**
	 * @throws ServiceException
	 */
	@Override
	public void refreshCache() throws ServiceException {
		try {
			this.areaRespository.refresh();
		} catch (RespositoryException e) {
			LOGGER.error("刷新地市信息失败,modeule:{}", e);
			throw new ServiceException(e);
		}
	}
}
