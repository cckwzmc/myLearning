package com.toney.sso.core.app;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.toney.sso.core.exception.ServiceException;
import com.toney.sso.dao.AppConfigDao;
import com.toney.sso.module.AppConfigModule;

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
	public void save(AppConfigModule module) throws ServiceException {
		Assert.notNull(module);
		this.appConfigDao.insert(module);
	}

	@Override
	public void deleteById(Long id) throws ServiceException {
		this.appConfigDao.deleteById(id);
	}

}
