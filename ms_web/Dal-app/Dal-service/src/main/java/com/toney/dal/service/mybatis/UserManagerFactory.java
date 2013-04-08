package com.toney.dal.service.mybatis;

import com.toney.dal.dao.AdministratorDao;

/**
 * 
 *************************************************************** 
 * <p>
 * @CLASS :UserManagerFactory
 * @DESCRIPTION :用户相关的包括系统管理员和普通用户.
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Dec 26, 2012
 *       </p>
 **************************************************************** 
 */ 
public interface UserManagerFactory {

	public AdministratorDao getAdministratorDao();
	
}
