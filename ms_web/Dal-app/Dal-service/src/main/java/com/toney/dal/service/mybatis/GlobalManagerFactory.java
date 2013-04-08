package com.toney.dal.service.mybatis;

import com.toney.dal.dao.AreaDao;
import com.toney.dal.dao.SysConfigDao;

/**
 * 
 *************************************************************** 
 * <p>
 * @CLASS :GlobalManagerFactory
 * @DESCRIPTION :全局参数管理 
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Dec 28, 2012
 *       </p>
 **************************************************************** 
 */
public interface GlobalManagerFactory {

	AreaDao getAreaDao();

	SysConfigDao getSysConfigDao();

}
