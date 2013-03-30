package com.toney.istyle.mvc.area;

import java.util.List;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.toney.istyle.constants.ErrConstants;
import com.toney.istyle.core.biz.AreaManager;
import com.toney.istyle.core.exception.ManagerException;
import com.toney.istyle.module.AreaModule;
import com.toney.istyle.util.JsonPackageWrapper;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :AreaController.java
 * @DESCRIPTION : area controller
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 29, 2013
 *       </p>
 **************************************************************** 
 */
@Controller
@RequestMapping("/area")
public class AreaController {
	private static final XLogger LOGGER = XLoggerFactory.getXLogger(AreaController.class);

	@Autowired
	AreaManager areaManager;

	@RequestMapping(value = "topArea", method = RequestMethod.GET, produces = "application/json", params = "format=json")
	public String topArea(Model model) {
		JsonPackageWrapper json = new JsonPackageWrapper();
		try {
			List<AreaModule> mList = areaManager.getTopArea();
			json.setData(mList);
		} catch (ManagerException e) {
			json.setScode(JsonPackageWrapper.S_ERR);
			json.setSmsg(e.getMessage());
		} catch (Exception e) {
			json.setScode(ErrConstants.GENERAL_ERR_CODE);
			json.setSmsg(ErrConstants.GENERAL_COMM_ERR_MSG);
		}
		model.addAttribute(com.toney.istyle.mvc.constants.Constants.JSON_MODEL_DATA, json);
		return "";
	}

	@RequestMapping(value = "childrenArea", method = RequestMethod.GET, produces = "application/json", params = "format=json")
	public String childrenArea(Integer id, Model model) {
		JsonPackageWrapper json = new JsonPackageWrapper();
		try {
			List<AreaModule> mList = areaManager.getChildrenArea(id);
			json.setData(mList);
		} catch (ManagerException e) {
			json.setScode(JsonPackageWrapper.S_ERR);
			json.setSmsg(e.getMessage());
		} catch (Exception e) {
			json.setScode(ErrConstants.GENERAL_ERR_CODE);
			json.setSmsg(ErrConstants.GENERAL_COMM_ERR_MSG);
		}
		model.addAttribute(com.toney.istyle.mvc.constants.Constants.JSON_MODEL_DATA, json);
		return "";
	}
}
