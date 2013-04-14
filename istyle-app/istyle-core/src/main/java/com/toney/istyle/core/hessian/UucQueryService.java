package com.toney.istyle.core.hessian;

import java.util.List;

import com.toney.sso.dto.UserDTO;

/**
 *************************************************************** 
 * <p>
 * @CLASS :UucQueryService.java
 * @DESCRIPTION : uuc hessian query service 
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Apr 3, 2013
 *       </p>
 **************************************************************** 
 */
public interface UucQueryService {

	UserDTO getUserDTOById(Long userId);

	List<UserDTO> getUserDTOByUserType(Integer userType);

}
