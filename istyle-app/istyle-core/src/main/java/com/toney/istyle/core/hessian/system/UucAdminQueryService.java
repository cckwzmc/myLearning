package com.toney.istyle.core.hessian.system;

import java.util.List;

import com.toney.sso.dto.UserDTO;

/**
 *************************************************************** 
 * <p>
 * @CLASS :UucQueryService.java
 * @DESCRIPTION : uuc query hession service
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Apr 2, 2013
 *       </p>
 **************************************************************** 
 */
public interface UucAdminQueryService {

	List<UserDTO> getUserDTOByUserType(int userType);

}
