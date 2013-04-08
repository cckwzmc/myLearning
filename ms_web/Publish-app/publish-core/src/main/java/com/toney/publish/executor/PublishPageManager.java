package com.toney.publish.executor;

import com.toney.dal.model.TemplateMappingModel;
import com.toney.publish.context.PublishContext;
import com.toney.publish.exception.PublishException;

/**
 * 
 *************************************************************** 
 * <p>
 * @CLASS :PublishPageManager
 * @DESCRIPTION :发布服务层接口
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Jan 6, 2013
 *       </p>
 **************************************************************** 
 */
public interface PublishPageManager {
	
	/**
	 * 查询模板路径.
	 * @param model
	 * @return
	 * @throws PublishException
	 */
	public TemplateMappingModel getTemplateMappingModel(PublishContext context) throws PublishException;
	/**
	 * 发布页面
	 * @param context
	 * @throws PublishException
	 */
//	public void doPublish(PublishContext context) throws PublishException;
	void doPublish() throws PublishException;
}
