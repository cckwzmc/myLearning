package com.toney.istyle.mvc.admin;

import java.util.List;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.toney.istyle.constants.ErrConstants;
import com.toney.istyle.core.biz.PlatFormManager;
import com.toney.istyle.core.exception.ManagerException;
import com.toney.istyle.module.PlatformModule;
import com.toney.istyle.mvc.annotation.AuthLevel;
import com.toney.istyle.mvc.annotation.AuthRequired;
import com.toney.istyle.mvc.constants.Constants;
import com.toney.istyle.util.JsonPackageWrapper;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :PlatFormManagerController.java
 * @DESCRIPTION : 平台商 管理
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 25, 2013
 *       </p>
 **************************************************************** 
 */
@AuthRequired(AuthLevel.ADMIN)
@Controller
@RequestMapping("/admin-manager/platform")
public class PlatFormManagerController {
	private static final XLogger LOGGER = XLoggerFactory.getXLogger(PlatFormManagerController.class);
	@Autowired
	PlatFormManager platFormManager;

	@RequestMapping(value = "all", method = RequestMethod.GET, produces = "application/json", params = "format=json")
	public String platFormAll(Model model) {
		JsonPackageWrapper json = new JsonPackageWrapper();
		try {
			List<PlatformModule> moduleList = this.platFormManager.getPlatFormAll();
			json.setData(moduleList);
		} catch (ManagerException e) {
			json.setScode(JsonPackageWrapper.S_ERR);
			json.setSmsg(e.getMessage());
		} catch (Exception e) {
			json.setErrorCode(ErrConstants.GENERAL_ERR_CODE);
			json.setScode(JsonPackageWrapper.S_ERR);
			json.setSmsg(ErrConstants.GENERAL_COMM_ERR_MSG);
		}
		model.addAttribute(Constants.JSON_MODEL_DATA, json);
		return "";
	}

}
