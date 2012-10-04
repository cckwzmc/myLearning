package com.toney.core.sys.biz;

import com.toney.core.exception.BusinessException;
import com.toney.core.model.AdministratorModel;

/**
 * @author toney.li
 * 系统后台用户管理
 */
public interface SysUserManager {

	void addAdmin() throws BusinessException;

	void removeAdmin() throws BusinessException;

	AdministratorModel getAdmin(String userName,String password) throws BusinessException;
	
	AdministratorModel getAdminById(Long id) throws BusinessException;
	
}
