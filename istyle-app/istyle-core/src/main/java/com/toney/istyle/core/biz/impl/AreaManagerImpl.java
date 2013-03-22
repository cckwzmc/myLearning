package com.toney.istyle.core.biz.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toney.istyle.bo.AreaBO;
import com.toney.istyle.core.biz.AreaManager;
import com.toney.istyle.core.exception.ManagerException;
import com.toney.istyle.core.exception.ServiceException;
import com.toney.istyle.core.system.AreaQueryService;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :AreaManagerImpl.java
 * @DESCRIPTION : 地市服务层
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 22, 2013
 *       </p>
 **************************************************************** 
 */
@Service("areaManager")
public class AreaManagerImpl implements AreaManager {
	private static final XLogger LOGGER = XLoggerFactory.getXLogger(AreaManagerImpl.class);

	@Autowired
	private AreaQueryService areaQueryService;

	@Override
	public List<AreaBO> getAreaList() throws ManagerException {
		try {
			return this.areaQueryService.getAreaListAll();
		} catch (ServiceException e) {
			LOGGER.error("查询地市信息失败", e);
			throw new ManagerException(e);
		}
	}
	@Override
	public Map<Integer,AreaBO> getAreaMap() throws ManagerException {
		try {
			return this.areaQueryService.getAreaMapAll();
		} catch (ServiceException e) {
			LOGGER.error("查询地市信息失败", e);
			throw new ManagerException(e);
		}
	}
}
