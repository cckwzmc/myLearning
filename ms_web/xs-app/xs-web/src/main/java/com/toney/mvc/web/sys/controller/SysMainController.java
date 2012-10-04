package com.toney.mvc.web.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.toney.mvc.web.annotation.AuthRequired;
import com.toney.mvc.web.contants.AuthLevel;

@Controller
@AuthRequired(AuthLevel.STRICT)
@RequestMapping("/sys")
public class SysMainController {
	
	@RequestMapping(value="main",method=RequestMethod.GET)
	public String main(){
		return "sys/main";
	}
	@RequestMapping(value="left",method=RequestMethod.GET)
	public String left(){
		return "sys/left";
	}
	@RequestMapping(value="right",method=RequestMethod.GET)
	public String right(){
		return "sys/right";
	}
	@RequestMapping(value="top",method=RequestMethod.GET)
	public String top(){
		return "sys/top";
	}
	
	
}
