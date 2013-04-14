package com.toney.istyle.core.biz;

import java.util.List;

import com.toney.sso.dto.UserDTO;

/**
 *************************************************************** 
 * <p>
 * @CLASS :UserQueryManager.java
 * @DESCRIPTION : user query
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Apr 3, 2013
 *       </p>
 **************************************************************** 
 */
public interface UserQueryManager {

	UserDTO getUserDTOById(Long userId);

//	List<UserDTO> getUserDTOByUserType(Integer userType);

	/**
	 * 查询保留用户。
	 * @return
	 */
	List<UserDTO> getUserDTORepertoire();

}
