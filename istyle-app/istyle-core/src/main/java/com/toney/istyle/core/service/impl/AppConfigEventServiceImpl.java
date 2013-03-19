package com.toney.istyle.core.service.impl;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.toney.istyle.bo.AppConfigBO;
import com.toney.istyle.core.exception.ServiceException;
import com.toney.istyle.core.service.AppConfigEventService;
import com.toney.istyle.dao.AppConfigDao;
import com.toney.istyle.module.AppConfigModule;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :AppConfigWriterService.java
 * @DESCRIPTION : AppConfig写操作
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 19, 2013
 *       </p>
 **************************************************************** 
 */
@Service("appConfigEventService")
public class AppConfigEventServiceImpl implements AppConfigEventService {
	private static final XLogger LOGGER = XLoggerFactory.getXLogger(AppConfigEventServiceImpl.class);

	@Autowired
	private AppConfigDao appConfigDao;

	@Override
	public void save(AppConfigBO bo) throws ServiceException {
		Assert.notNull(bo);
		AppConfigModule module = new AppConfigModule();
		this.appConfigDao.insert(module);
	}

	@Override
	public void deleteById(Long id) throws ServiceException {
		this.appConfigDao.deleteById(id);
	}

}
