package com.toney.istyle.core.system.impl;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toney.istyle.core.exception.ServiceException;
import com.toney.istyle.core.system.KeyTableEventService;
import com.toney.istyle.dao.KeyTableDao;

/**
 *************************************************************** 
 * <p>
 * @CLASS :KeyTableEventServiceImpl.java
 * @DESCRIPTION : KeyTable EventService Implement
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Apr 15, 2013
 *       </p>
 **************************************************************** 
 */
@Service("keyTableEventService")
public class KeyTableEventServiceImpl implements KeyTableEventService {

	private static final XLogger LOGGER = XLoggerFactory.getXLogger(KeyTableEventServiceImpl.class);
	
	@Autowired
	KeyTableDao keyTableDao;
	@Override
	public void updateKeyValue(String keyName, Long keyValue) throws ServiceException {
		this.keyTableDao.updateKeyValue(keyName, keyValue);
	}

}
