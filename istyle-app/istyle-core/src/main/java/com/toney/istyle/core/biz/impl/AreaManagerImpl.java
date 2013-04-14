package com.toney.istyle.core.biz.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toney.istyle.core.biz.AreaManager;
import com.toney.istyle.core.exception.ManagerException;
import com.toney.istyle.core.exception.RespositoryException;
import com.toney.istyle.core.system.AreaRespository;
import com.toney.istyle.module.AreaModule;

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
	AreaRespository areaRespository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.toney.istyle.core.biz.AreaManager#getAreaList()
	 */
	@Override
	public List<AreaModule> getAreaList() throws ManagerException {
		try {
			List<AreaModule> boList = this.areaRespository.getAreaListAll();
			return boList;
		} catch (RespositoryException e) {
			LOGGER.error("查询地市信息失败", e);
			throw new ManagerException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.toney.istyle.core.biz.AreaManager#getAreaMap()
	 */
	@Override
	public Map<Integer, AreaModule> getAreaMap() throws ManagerException {
		try {
			Map<Integer, AreaModule> boMap = this.areaRespository.getAreaMapAll();
			return boMap;
		} catch (RespositoryException e) {
			LOGGER.error("查询地市信息失败", e);
			throw new ManagerException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.toney.istyle.core.biz.AreaManager#getTopArea()
	 */
	@Override
	public List<AreaModule> getTopArea() throws ManagerException {
		List<AreaModule> moduleList = this.getAreaList();
		List<AreaModule> topList = new ArrayList<AreaModule>();
		for (AreaModule module : moduleList) {
			if (module.getParentId() == null || module.getParentId() == 0) {
				topList.add(module);
			}
		}
		return topList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.toney.istyle.core.biz.AreaManager#getAreaById(java.lang.Integer)
	 */
	@Override
	public AreaModule getAreaById(Integer cityId) throws ManagerException {
		org.springframework.util.Assert.notNull(cityId);
		return this.getAreaMap().get(cityId);
	}

	/* (non-Javadoc)
	 * @see com.toney.istyle.core.biz.AreaManager#getChildrenArea(java.lang.Integer)
	 */
	@Override
	public List<AreaModule> getChildrenArea(Integer id) throws ManagerException {
		org.springframework.util.Assert.notNull(id);
		List<AreaModule> moduleList = this.getAreaList();
		List<AreaModule> topList = new ArrayList<AreaModule>();
		for (AreaModule module : moduleList) {
			if (module.getParentId() != null && module.getParentId() == id.intValue()) {
				topList.add(module);
			}
		}
		return topList;
	}

}
