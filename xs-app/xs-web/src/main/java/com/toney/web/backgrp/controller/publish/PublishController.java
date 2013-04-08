package com.toney.web.backgrp.controller.publish;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.toney.core.biz.PublicManagerFactory;
import com.toney.core.biz.PublishPublicManager;
import com.toney.core.exception.BusinessException;
import com.toney.web.commons.annotation.AuthRequired;

/**
 * @author toney.li
 * 出版
 */
@AuthRequired
@Controller
@RequestMapping("/sys/publish")
public class PublishController {
	private static final XLogger logger=XLoggerFactory.getXLogger(PublishController.class);
	@Autowired
	PublishPublicManager publishPublicManager; 
	
	/**
	 * @return
	 * 全站首页出版页。
	 */
	@RequestMapping("index")
	public String publishIndex(Model model){
		return "sys/publishmgr/publish_index";
	}
}
