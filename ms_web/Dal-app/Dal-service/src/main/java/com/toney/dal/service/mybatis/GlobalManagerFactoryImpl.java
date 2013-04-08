package com.toney.dal.service.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toney.dal.dao.AreaDao;
import com.toney.dal.dao.SysConfigDao;

/**
 * 
 *************************************************************** 
 * <p>
 * 
 * @CLASS :GlobalManagerFactoryImpl
 * @DESCRIPTION :功能描述
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Dec 28, 2012
 *       </p>
 **************************************************************** 
 */
@Service("globalManagerFactory")
public class GlobalManagerFactoryImpl implements GlobalManagerFactory {
	@Autowired
	private AreaDao areaDao;
	@Autowired
	private SysConfigDao sysConfigDao;

	@Override
	public AreaDao getAreaDao() {
		return areaDao;
	}

	@Override
	public SysConfigDao getSysConfigDao() {
		return sysConfigDao;
	}

}
