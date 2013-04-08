package com.toney.publish.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.toney.core.model.AdministratorModel;
import com.toney.core.sys.biz.SysUserManager;
import com.toney.publish.utils.CookieStoreUtil;

/**
 * @author toney.li
 * 系统管理首页
 */
@Controller
@RequestMapping("/sys")
public class SysLoginContorller {
	
	@Autowired
	private SysUserManager sysUserManager;
	
	@RequestMapping(value="logined",method=RequestMethod.POST)
	public void login(String userName,String password,Model model, HttpServletResponse response) throws Exception{
		CookieStoreUtil.clearUserCookie();
		AdministratorModel administrator=this.sysUserManager.getAdmin(userName, password);
		CookieStoreUtil.storeAdministrator(administrator);
		response.sendRedirect("main");
	}
}
