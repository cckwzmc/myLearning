package com.toney.publish.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.toney.core.sys.biz.SysChannelManager;
import com.toney.publish.annotation.AuthRequired;
import com.toney.publish.contants.AuthLevel;

@Controller
@AuthRequired(AuthLevel.STRICT)
@RequestMapping("/sys/channel")
public class ChannelController {
	
	@Autowired
	private SysChannelManager sysChannelManager; 
	
	
	@RequestMapping(value="list",method=RequestMethod.GET)
	public String list(){
		
		return "sys/channelmgr/list";
	}
	
	@RequestMapping(value="create",method=RequestMethod.GET)
	public String create(){
		return "sys/channelmgr/create";
	}
	
	@RequestMapping(value="doCreate",method=RequestMethod.GET)
	public String doCreate(){
		return "sys/channelmgr/list";
	}
	
}
