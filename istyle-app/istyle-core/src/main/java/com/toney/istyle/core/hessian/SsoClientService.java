package com.toney.istyle.core.hessian;

import com.toney.sso.dto.RegisterDTO;
import com.toney.sso.dto.UserDTO;

/**
 *************************************************************** 
 * <p>
 * @CLASS :SsoClientService.java
 * @DESCRIPTION : sso hessian client service
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Apr 3, 2013
 *       </p>
 **************************************************************** 
 */
public interface SsoClientService {

	UserDTO register(RegisterDTO regDTO);

}
