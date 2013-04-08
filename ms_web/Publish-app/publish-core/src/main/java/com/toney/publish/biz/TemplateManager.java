package com.toney.publish.biz;

import com.toney.publish.context.PublishContext;
import com.toney.publish.exception.PublishBusinessException;
import com.toney.publish.model.TemplateDetailModel;

/**
 * 
 *************************************************************** 
 * <p>
 * 
 * @CLASS :TemplateManagerFactory
 * @DESCRIPTION : 模版管理Factory
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Feb 1, 2013
 *       </p>
 **************************************************************** 
 */
public interface TemplateManager {

	/**
	 * 获取非公共出版页模版细节信息.
	 * @param context
	 * @return
	 * @throws PublishBusinessException
	 */
	public TemplateDetailModel getTemplateDetail(PublishContext context) throws PublishBusinessException;

	/**
	 * 获取公共出版页模版细节信息.
	 * @param publicId
	 * @return
	 * @throws PublishBusinessException
	 */
	public TemplateDetailModel getTemplateDetail(Long publicId) throws PublishBusinessException;
}
