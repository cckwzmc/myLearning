package com.toney.publish.context;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.toney.dal.model.SysConfigModel;
import com.toney.publish.model.PublishTplModel;

/**
 * 
 *************************************************************** 
 * <p>
 * 
 * @CLASS :com.toney.publish.tpl.TemplateContext
 * @DESCRIPTION :用于发布的模板上下文.
 *              <ul>
 *              <li>模板信息</li><br/>
 *              <li>模板数据</li><br/>
 *              <li>全站数据</li><br/>
 *              </ul>
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Dec 18, 2012
 *       </p>
 **************************************************************** 
 */
public class PublishContext implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 811631596618962841L;

	/**
	 * 系统配置参数
	 * 
	 * @see com.toney.core.model.SysConfigModel
	 */
	private Map<String, SysConfigModel> sysConfig;
	// 出版数据
	private Map<String, Object> data = new HashMap<String, Object>();
	// 模板参数.
	private PublishTplModel publishTplModel;
	// 是否动手出版
	private Boolean isManualPublish;

	public Boolean getIsManualPublish() {
		return isManualPublish;
	}

	public void setIsManualPublish(Boolean isManualPublish) {
		this.isManualPublish = isManualPublish;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	/**
	 * 发布请求，和模板相关的参数<br/>
	 * 
	 * @see com.toney.publish.tpl.PublishTplModel
	 */
	public PublishTplModel getPublishTplModel() {
		return publishTplModel;
	}

	/**
	 * 发布请求，和模板相关的参数<br/>
	 * 
	 * @see com.toney.publish.tpl.PublishTplModel
	 */
	public void setPublishTplModel(PublishTplModel publishTplModel) {
		this.publishTplModel = publishTplModel;
	}

	/**
	 * 系统配置参数
	 * 
	 * @see com.toney.core.model.SysConfigModel
	 */
	public Map<String, SysConfigModel> getSysConfig() {
		return sysConfig;
	}

	/**
	 * 系统配置参数
	 * 
	 * @see com.toney.core.model.SysConfigModel
	 */
	public void setSysConfig(Map<String, SysConfigModel> sysConfig) {
		this.sysConfig = sysConfig;
	}

}
