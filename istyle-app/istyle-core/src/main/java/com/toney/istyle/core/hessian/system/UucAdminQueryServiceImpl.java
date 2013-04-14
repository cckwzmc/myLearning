package com.toney.istyle.core.hessian.system;

import java.util.List;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toney.sso.commons.Result;
import com.toney.sso.core.adminservice.UucAdminService;
import com.toney.sso.dto.UserDTO;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :UucQueryServiceImpl.java
 * @DESCRIPTION : uuc query hessian service implements
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Apr 2, 2013
 *       </p>
 **************************************************************** 
 */
@Service("uucAdminQueryService")
public class UucAdminQueryServiceImpl implements UucAdminQueryService {
	private static final XLogger LOGGER = XLoggerFactory.getXLogger(UucAdminQueryServiceImpl.class);

	@Autowired
	UucAdminService uucAdminFacade;

	@Override
	public List<UserDTO> getUserDTOByUserType(int userType) {
		Result<UserDTO> result = this.uucAdminFacade.queryUserByUserType(userType);
		if (!result.getIsSuccess()) {
			LOGGER.error("UUC查询失败 bizCode={},bizMessage={},errCode={},errMessage={}",
					new Object[] { result.getBizCode(), result.getBizMessage(), result.getErrCode(), result.getErrMessage() });
		}
		return result.getResult();
	}

}
