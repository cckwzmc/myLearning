package com.toney.core.sys.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toney.core.exception.BusinessException;
import com.toney.core.sys.biz.SysUserManager;
import com.toney.dal.dao.AdministratorDao;
import com.toney.dal.model.AdministratorModel;
import com.toney.dal.service.DalMybatisManagerFactory;

/**
 * @author toney.li
 *
 */
@Service("sysUserManager")
public class SysUserManagerImpl implements SysUserManager{

	@Autowired
	private DalMybatisManagerFactory dalMybatisManagerFactory;

	@Override
	public void addAdmin() throws BusinessException{
		
	}
	@Override
	public void removeAdmin() throws BusinessException{
		
	}

	@Override
	public AdministratorModel getAdmin(String userName, String password)
			throws BusinessException {
		
		return this.dalMybatisManagerFactory.getUserManagerFactory().getAdministratorDao().getAdministratorModel(userName, password);
	}
	@Override
	public AdministratorModel getAdminById(Long id) throws BusinessException {
		return this.dalMybatisManagerFactory.getUserManagerFactory().getAdministratorDao().getAdministratorModelById(id);
	}
}
