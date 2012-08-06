package com.toney.core.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.toney.core.model.AdministratorModel;

/**
 * @author toney.li
 * 对应dede_admin
 */
@Repository("administratorDao")
public interface AdministratorDao {
	public AdministratorModel getAdministratorModel(@Param("uname") String uname,@Param("pwd") String pwd);
	public AdministratorModel getAdministratorModelById(Long id );
}
