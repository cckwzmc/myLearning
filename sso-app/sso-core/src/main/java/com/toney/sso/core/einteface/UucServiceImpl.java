package com.toney.sso.core.einteface;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.toney.sso.commons.Result;
import com.toney.sso.core.bo.UserBO;
import com.toney.sso.core.exception.ServiceException;
import com.toney.sso.core.query.UserQueryService;
import com.toney.sso.core.service.UucService;
import com.toney.sso.dto.UserDTO;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :UucServiceImpl.java
 * @DESCRIPTION : uuc servcie implements
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Apr 3, 2013
 *       </p>
 **************************************************************** 
 */
public class UucServiceImpl implements UucService {

	@Autowired
	UserQueryService userQueryService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.toney.sso.core.service.SsoService#queryUserInfo(com.toney.sso.dto
	 * .UserDTO)
	 */
	@Override
	public Result<UserDTO> queryUserInfo(UserDTO userDTO) {
		Result<UserDTO> result = validateQueryUser(userDTO);
		return null;
	}

	private Result<UserDTO> validateQueryUser(UserDTO userDTO) {
		Result<UserDTO> result = new Result<UserDTO>();
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.toney.sso.core.service.UucService#queryUserById(com.toney.sso.dto
	 * .UserDTO)
	 */
	@Override
	public Result<UserDTO> queryUserById(UserDTO userDTO) {
		Assert.notNull(userDTO.getId());
		Assert.isTrue(userDTO.getId()>0,"User id must greater than zero");
		Result<UserDTO> result = validateQueryUser(userDTO);
		if (result.getIsSuccess()) {
			UserDTO dto = new UserDTO();
			try {
				UserBO userBO = this.userQueryService.getUserById(userDTO.getId());
				dto.setUserName(userBO.getUserName());
				dto.setUserType(userBO.getUserType());
				dto.setId(userBO.getId());
				dto.setNickName(userBO.getNickName());
				dto.setEmail(userBO.getEmail());
			} catch (ServiceException e) {
				result.setIsSuccess(false);
				return result;
			}
			List<UserDTO> dtoList = new ArrayList<UserDTO>();
			dtoList.add(dto);
			result.setResult(dtoList);
			return result;
		}
		return null;
	}

}
