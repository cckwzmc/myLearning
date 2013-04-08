package com.toney.web.backgrp.controller.publish;

import java.util.List;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.toney.core.biz.ChannelManager;
import com.toney.dal.model.ChannelDetailModel;
import com.toney.web.commons.annotation.AuthRequired;
import com.toney.web.commons.constants.AuthLevel;

/**
 * 
 *************************************************************** 
 * <p>
 * @CLASS :PublishChannelController
 * @DESCRIPTION :频道页出版功能描述
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Feb 3, 2013
 *       </p>
 **************************************************************** 
 */
@Controller
@AuthRequired(AuthLevel.STRICT)
@RequestMapping("/sys/publish/channel")
public class PublishChannelController {
	private static final XLogger logger=XLoggerFactory.getXLogger(PublishChannelController.class);
	
	@Autowired
	private ChannelManager channelManager; 
	
	@RequestMapping(value="list",method=RequestMethod.GET)
	public String list(Model model) throws Exception{
		List<ChannelDetailModel> channelList=this.channelManager.getTopChannel();
		model.addAttribute("channelList", channelList);
		return "sys/publishmgr/channel/list";
	}
}
