package com.toney.core.sys.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
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
	public AdministratorModel getAdministratorBizInfo(String uname, String pwd)
			throws BusinessException {
		return this.administratorDao.getAdministratorModel(uname, pwd);
	}

}
