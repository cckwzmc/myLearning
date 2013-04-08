package com.toney.publish.tpl.tag;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.toney.publish.biz.ChannelManager;
import com.toney.publish.tpl.frermarker.FreemarkerUtils;

import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * @author toney.li
 * 栏目页面tag抽象类。
 */
public abstract class AbstractChannelTag implements TemplateDirectiveModel {

	/**
	 * 输入参数，父栏目ID。存在时，获取该栏目的子栏目，不存在时获取顶级栏目。
	 */
	public static final String PARENT_ID = "parentId";
	/**
	 * 输入参数，站点ID。存在时，获取该站点顶级栏目，不存在时获取当前站点顶级栏目。（仅在parentId不存在时起作用）
	 */
	public static final String SITE_ID = "siteId";
	/**
	 * 输入参数，是否有内容。默认所有，否则只获取有内容的栏目。
	 */
	public static final String HAS_CONTENT = "hasContent";

	protected boolean getHasContentOnly(Map<String, TemplateModel> params)
			throws TemplateException {
		Boolean hasContent = FreemarkerUtils.getBool(HAS_CONTENT, params);
		return hasContent != null && hasContent;
	}
	@Autowired(required=false)
	protected ChannelManager channelManager;

	public ChannelManager getChannelManager() {
		return channelManager;
	}

	public void setChannelManager(ChannelManager channelManager) {
		this.channelManager = channelManager;
	}
	
}
