package com.toney.web.backgrp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.toney.core.sys.biz.SysUserManager;
import com.toney.dal.model.AdministratorModel;
import com.toney.web.commons.utils.CookieStoreUtil;

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
	public void login(String userName,String password,Model model, HttpServletResponse response,HttpServletRequest request) throws Exception{
		CookieStoreUtil.clearUserCookie();
		AdministratorModel administrator=this.sysUserManager.getAdmin(userName, password);
		CookieStoreUtil.storeAdministrator(administrator);
		response.sendRedirect("main");
	}
}
