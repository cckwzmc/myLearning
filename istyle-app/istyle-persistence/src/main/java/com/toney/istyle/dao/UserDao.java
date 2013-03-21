package com.toney.istyle.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.toney.istyle.module.UserModule;

/**
 * 
 *************************************************************** 
 * <p>
 * 
 * @CLASS :IstyleUserDao
 * @DESCRIPTION :用户信息
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 11, 2013
 *       </p>
 **************************************************************** 
 */
@Repository("userDao")
public interface UserDao {

	void deleteById(Long id);

	void deleteByUserName(String userName);

	int insert(UserModule module);

	List<UserModule> selectLikeUserName(String userName);

	UserModule selectById(Long id);

	UserModule selectByUserName(String userName);

	/**
	 * 用户/密码查询。
	 * @param userName
	 * @param password
	 * @return
	 */
	UserModule selectByUnAndPwd(String userName, String password);

}