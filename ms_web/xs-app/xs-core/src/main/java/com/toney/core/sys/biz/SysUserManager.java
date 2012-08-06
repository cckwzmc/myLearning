package com.toney.core.sys.biz;

import com.toney.core.exception.BusinessException;
import com.toney.core.model.AdministratorModel;

/**
 * @author toney.li
 * 系统管理用户业务层
 */
public interface SysUserManager {
	/**
	 * @param uname
	 * @param pwd
	 * @return
	 * 根据用户/密码查询用户信息
	 */
	public AdministratorModel getAdministratorBizInfo(String uname,String pwd) throws BusinessException;
}
