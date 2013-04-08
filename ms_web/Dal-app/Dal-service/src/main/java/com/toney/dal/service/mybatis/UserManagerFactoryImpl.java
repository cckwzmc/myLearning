package com.toney.dal.service.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toney.dal.dao.AdministratorDao;

/**
 * 
 *************************************************************** 
 * <p>
 * @CLASS :UserManagerFactoryImpl
 * @DESCRIPTION :功能描述
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Dec 26, 2012
 *       </p>
 **************************************************************** 
 */
@Service
public class UserManagerFactoryImpl implements UserManagerFactory {
	
	@Autowired
	private AdministratorDao administratorDao;
	
	@Override
	public AdministratorDao getAdministratorDao(){
		return this.administratorDao;
	}
}
