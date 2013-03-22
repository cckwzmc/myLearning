package com.toney.istyle.core.system.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toney.istyle.bo.AreaBO;
import com.toney.istyle.core.exception.RespositoryException;
import com.toney.istyle.core.exception.ServiceException;
import com.toney.istyle.core.system.AreaQueryService;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :AreaQueryServiceImpl.java
 * @DESCRIPTION : Area Query Service
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 22, 2013
 *       </p>
 **************************************************************** 
 */
@Service("areaQueryService")
public class AreaQueryServiceImpl implements AreaQueryService {
	private static final XLogger LOGGER = XLoggerFactory.getXLogger(AreaQueryServiceImpl.class);
	@Autowired
	AreaRespository areaRespository;

	@Override
	public List<AreaBO> getAreaListAll() throws ServiceException {
		try {
			return this.areaRespository.getAreaListAll();
		} catch (RespositoryException e) {
			LOGGER.error("查询地市信息失败", e);
			throw new ServiceException(e);
		}
	}

	@Override
	public Map<Integer, AreaBO> getAreaMapAll() throws ServiceException {
		try {
			return this.areaRespository.getAreaMapAll();
		} catch (RespositoryException e) {
			LOGGER.error("查询地市信息失败", e);
			throw new ServiceException(e);
		}
	}

	@Override
	public AreaBO getAreaById(Integer cityId) throws ServiceException {
		return this.getAreaMapAll().get(cityId);
	}

}
