package com.toney.publish.tpl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.toney.core.model.SysConfigModel;
import com.toney.core.model.TemplateManagerModel;

public class SiteParameter implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 811631596618962841L;

	private String templateName;
	private String groupName;
	
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	private Map<String,SysConfigModel> sysConfig;
	private List<TemplateManagerModel> templateManager;
	
	public List<TemplateManagerModel> getTemplateManager() {
		return templateManager;
	}
	public Map<String, SysConfigModel> getSysConfig() {
		return sysConfig;
	}
	public void setSysConfig(Map<String, SysConfigModel> sysConfig) {
		this.sysConfig = sysConfig;
	}
	public void setTemplateManager(List<TemplateManagerModel> templateManager) {
		this.templateManager = templateManager;
	}
	
}
