package com.toney.istyle.core.biz.impl;

import java.util.List;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toney.istyle.core.biz.PlatFormManager;
import com.toney.istyle.core.exception.ManagerException;
import com.toney.istyle.core.exception.ServiceException;
import com.toney.istyle.core.platform.PlatFormQueryService;
import com.toney.istyle.module.PlatformModule;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :PlatFormManagerImpl.java
 * @DESCRIPTION : platform business service
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 25, 2013
 *       </p>
 **************************************************************** 
 */
@Service("platFormManager")
public class PlatFormManagerImpl implements PlatFormManager {
	private static final XLogger LOGGER = XLoggerFactory.getXLogger(PlatFormManagerImpl.class);

	@Autowired
	private PlatFormQueryService platFormQueryService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.toney.istyle.core.biz.PlatFormManager#getPlatFormAll()
	 */
	@Override
	public List<PlatformModule> getPlatFormAll() throws ManagerException {

		try {
			return platFormQueryService.getPlatFormAll();
		} catch (ServiceException e) {
			LOGGER.error("获取所有平台商信息失败", e);
			throw new ManagerException(e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.toney.istyle.core.biz.PlatFormManager#getPlatFormById(java.lang.Long)
	 */
	@Override
	public PlatformModule getPlatFormById(Long id) throws ManagerException {
		try {
			PlatformModule module = this.platFormQueryService.getPlatFormById(id);
			return module;
		} catch (ServiceException e) {
			LOGGER.error("根据ID获取平台商信息失败 id:{}", id);
			throw new ManagerException(e);
		}
	}
}
