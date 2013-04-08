package com.toney.publish.model;

import com.toney.dal.model.TemplateMappingModel;

/**
 * 
 *************************************************************** 
 * <p>
 * @CLASS :TemplateDetailModel
 * @DESCRIPTION :功能描述
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Feb 1, 2013
 *       </p>
 **************************************************************** 
 */
/**
 * 
 *************************************************************** 
 * <p>
 * 
 * @CLASS :TemplateDetailModel
 * @DESCRIPTION :功能描述
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Feb 1, 2013
 *       </p>
 **************************************************************** 
 */
public class TemplateDetailModel {
	private String content;
	private TemplateMappingModel templateMappingModel;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public TemplateMappingModel getTemplateMappingModel() {
		return templateMappingModel;
	}

	public void setTemplateMappingModel(TemplateMappingModel templateMappingModel) {
		this.templateMappingModel = templateMappingModel;
	}

}
