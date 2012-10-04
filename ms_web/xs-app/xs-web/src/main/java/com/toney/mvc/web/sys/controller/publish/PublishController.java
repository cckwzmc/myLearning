package com.toney.mvc.web.sys.controller.publish;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.toney.mvc.web.annotation.AuthRequired;

/**
 * @author toney.li
 * 出版
 */
@AuthRequired
@Controller
@RequestMapping("/sys/publish")
public class PublishController {
	private static final XLogger logger=XLoggerFactory.getXLogger(PublishController.class);
	/**
	 * @return
	 * 全站首页出版页。
	 */
	@RequestMapping("index")
	public String publishIndex(Model model){
		return "sys/publishmgr/publish_index";
	}
	/**
	 * @return
	 * 公用页面出版页。
	 */
	@RequestMapping("public")
	public String publishPublic(Model model){
		return "sys/publishmgr/publish_public";
	}
}
