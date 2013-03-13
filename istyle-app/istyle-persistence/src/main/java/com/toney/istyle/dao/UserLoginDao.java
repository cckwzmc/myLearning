package com.toney.istyle.dao;

import org.springframework.stereotype.Repository;

import com.toney.istyle.module.UserLoginModule;

/**
 * 
 *************************************************************** 
 * <p>
 * 
 * @CLASS :UserLoginDao
 * @DESCRIPTION :功能描述
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 11, 2013
 *       </p>
 **************************************************************** 
 */
@Repository("userLoginDao")
public interface UserLoginDao {
	int insert(UserLoginModule module);
}