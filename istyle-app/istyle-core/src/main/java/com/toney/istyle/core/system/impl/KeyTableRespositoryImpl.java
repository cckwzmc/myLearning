package com.toney.istyle.core.system.impl;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toney.istyle.core.exception.RespositoryException;
import com.toney.istyle.core.system.KeyTableRespository;
import com.toney.istyle.dao.KeyTableDao;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :KeyTableRespositoryImpl.java
 * @DESCRIPTION : KeyTable Respository Implement
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Apr 15, 2013
 *       </p>
 **************************************************************** 
 */
@Service("keyTableRespository")
public class KeyTableRespositoryImpl implements KeyTableRespository {
	private static final XLogger LOGGER = XLoggerFactory.getXLogger(KeyTableRespositoryImpl.class);

	@Autowired
	KeyTableDao keyTableDao;
	@Override
	public Long getValue(String keyName) throws RespositoryException {
		return keyTableDao.getKeyValue(keyName);
	}

}
