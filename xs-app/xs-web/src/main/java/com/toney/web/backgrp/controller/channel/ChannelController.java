package com.toney.web.backgrp.controller.channel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.toney.core.constants.Constants;
import com.toney.core.sys.biz.SysChannelManager;
import com.toney.dal.model.ChannelDetailModel;
import com.toney.web.commons.annotation.AuthRequired;
import com.toney.web.commons.constants.AuthLevel;
import com.toney.web.commons.utils.JsonPackageWrapper;

@Controller
@AuthRequired(AuthLevel.STRICT)
@RequestMapping("/sys/channel")
public class ChannelController {
	
	@Autowired
	private SysChannelManager sysChannelManager; 
	
	
	@RequestMapping(value="list",method=RequestMethod.GET)
	public String list(Model model) throws Exception{
		 List<ChannelDetailModel>  list=sysChannelManager.getTopChannelDetailList();
		 model.addAttribute("channelList",list);
		return "sys/channelmgr/list";
	}
	@RequestMapping(value="create",method=RequestMethod.GET)
	public String create(Integer level,Integer parentId,Model model) throws Exception{
		Assert.notNull(level);
		model.addAttribute("typeList",this.sysChannelManager.getChannelTypeList());
		return "sys/channelmgr/create";
	}
	
	@RequestMapping(value="edit/{channelId}",method=RequestMethod.GET)
	public String edit(Model model) throws Exception{
		model.addAttribute("typeList",this.sysChannelManager.getChannelTypeList());
		return "sys/channelmgr/create";
	}
	
	@RequestMapping(value="doCreate",method=RequestMethod.GET)
	public String doCreate(){
		return "sys/channelmgr/list";
	}
	
	@RequestMapping(value="getChildrens",method=RequestMethod.GET,produces = "application/json", params = "format=json")
	public String getChildrens(Integer parentId,Integer level,Model model) throws Exception{
		 List<ChannelDetailModel>  list=this.sysChannelManager.getChildrenChannelDetailList(parentId, level);
		 JsonPackageWrapper json=new JsonPackageWrapper();
		 json.setData(list);
		 model.addAttribute(Constants.JSON_MODEL_DATA,json);
		 return null;
	}
	
}
