package com.toney.core.sys.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.toney.core.dao.AdministratorDao;
import com.toney.core.exception.BusinessException;
import com.toney.core.model.AdministratorModel;
import com.toney.core.sys.biz.SysUserManager;

/**
 * @author toney.li
 *
 */
@Service("sysUserManager")
public class SysUserManagerImpl implements SysUserManager{

	@Autowired
	private AdministratorDao administratorDao;

	@Override
	public void addAdmin() throws BusinessException{
		
	}
	@Override
	public void removeAdmin() throws BusinessException{
		
	}

	@Override
	public AdministratorModel getAdmin(String userName, String password)
			throws BusinessException {
		
		return this.administratorDao.getAdministratorModel(userName, password);
	}
	@Override
	public AdministratorModel getAdminById(Long id) throws BusinessException {
		return this.administratorDao.getAdministratorModelById(id);
	}
}
