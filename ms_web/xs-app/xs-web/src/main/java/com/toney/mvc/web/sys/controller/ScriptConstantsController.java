package com.toney.mvc.web.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
  * @ClassName: GenScriptConstantsController
  * @Description: 动态生成JS常量
  * @author toney.li
  * @date 2012-4-28 下午2:59:12
  *
  */
@Controller
@RequestMapping(value="/sys/script")
public class ScriptConstantsController {
	
	@RequestMapping(value="constants.js",method=RequestMethod.GET)
	public String genScriptConstants(Model model){
		return "sys/js_constants";
	}

}
