package com.toney.sso.core.einteface;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toney.sso.commons.Result;
import com.toney.sso.core.adminservice.UucAdminService;
import com.toney.sso.core.bo.UserBO;
import com.toney.sso.core.constants.ErrConstants;
import com.toney.sso.core.exception.ServiceException;
import com.toney.sso.core.query.UserQueryService;
import com.toney.sso.dto.UserDTO;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :UucAdminServiceImpl.java
 * @DESCRIPTION : Uuc Admin Service implements
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 30, 2013
 *       </p>
 **************************************************************** 
 */
public class UucAdminServiceImpl implements UucAdminService {
	private static final XLogger LOGGER = XLoggerFactory.getXLogger(UucAdminServiceImpl.class);

	@Autowired
	UserQueryService userQueryService;

	/* (non-Javadoc)
	 * @see com.toney.sso.core.adminservice.UucAdminService#queryUserByUserType(java.lang.Integer)
	 */
	@Override
	public Result<UserDTO> queryUserByUserType(Integer userType) {
		Result<UserDTO> result = new Result<UserDTO>();
		List<UserBO> list;
		try {
			list = userQueryService.getUserByRegType(userType);
			if (CollectionUtils.isNotEmpty(list)) {
				List<UserDTO> dtoList = new ArrayList<UserDTO>();
				for (UserBO bo : list) {
					UserDTO dto = new UserDTO();
					dto.setId(bo.getId());
					dto.setUserName(bo.getUserName());
					dto.setEmail(bo.getEmail());
					dto.setMobile(bo.getMobile());
					dto.setNickName(bo.getNickName());
					dto.setRegType(bo.getRegType());
					dtoList.add(dto);
				}
				result.setResult(dtoList);
			}
		} catch (ServiceException e) {
			result.setIsSuccess(false);
			result.setErrCode(ErrConstants.GENERAL_ERR_CODE);
		}

		return result;
	}

}
