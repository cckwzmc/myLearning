package com.toney.istyle.mvc.admin;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.toney.istyle.core.biz.UserRegisterManager;
import com.toney.istyle.core.exception.ManagerException;
import com.toney.istyle.mvc.annotation.AuthLevel;
import com.toney.istyle.mvc.annotation.AuthRequired;
import com.toney.istyle.util.JsonPackageWrapper;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :UserManagerController.java
 * @DESCRIPTION : 后台管理--用户管理
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 23, 2013
 *       </p>
 **************************************************************** 
 */
@AuthRequired(AuthLevel.ADMIN)
@RequestMapping("/admin-manager/user")
@Controller
public class UserManagerController {
	private static final XLogger LOGGER = XLoggerFactory.getXLogger(UserManagerController.class);
	
	@Autowired
	UserRegisterManager userRegisterManager;
	@Autowired
	
	@RequestMapping(value="doRegister",method=RequestMethod.POST)
	public String adminAddUser(String userName, String password, String regType, String nickName, Model model) throws Exception {
		Assert.notNull(nickName);
		Assert.notNull(userName);
		Assert.notNull(password);
		
		try {
			this.userRegisterManager.registerUser(userName, password, nickName, Short.valueOf(regType));
		} catch (NumberFormatException e) {
			LOGGER.error("代理注册用户失败", e);
			throw new Exception(e);
		} catch (ManagerException e) {
			LOGGER.error("代理注册用户失败", e);
			throw new Exception(e);
		}
		model.addAttribute("url", "/admin-manager/user/proxyRegister");
		return "admin-manager/commons/success-direct";
	} 
	
	@RequestMapping(value="proxyRegister",method=RequestMethod.GET)
	public String adminProxyRegister(){
			return "admin-manager/user-manager/register";
	} 
	
	@RequestMapping(value="retain",method=RequestMethod.GET,produces="application/json",params="format=json")
	public String retainUser(Model model){
		JsonPackageWrapper json=new JsonPackageWrapper();
		userRegisterManager.getRetainUserAll();
		return "";
	}
}
