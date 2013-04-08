package com.toney.publish.executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toney.dal.model.TemplateMappingModel;
import com.toney.publish.context.PublishContext;
import com.toney.publish.exception.PublishException;
import com.toney.publish.exception.ServiceException;
import com.toney.publish.model.PublishTplModel;
import com.toney.publish.service.TemplateMappingService;

/**
 * 
 *************************************************************** 
 * <p>
 * 
 * @CLASS :AbstractPublishPageManager
 * @DESCRIPTION :功能描述
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Jan 6, 2013
 *       </p>
 **************************************************************** 
 */
public abstract class AbstractPublishPageManager implements PublishPageManager {
	
	private static TemplateMappingService templateMappingManager;
	

	public void setTemplateMappingManager(TemplateMappingService templateMappingManager) {
		AbstractPublishPageManager.templateMappingManager = templateMappingManager;
	}

	@Override
	public abstract void doPublish() throws PublishException;

	/**
	 * @param context
	 * @return
	 * @throws PublishException
	 */
	@Override
	public TemplateMappingModel getTemplateMappingModel(PublishContext context) throws PublishException {
		try {
			return templateMappingManager.getTemplateMappingModel(context.getPublishTplModel());
		} catch (ServiceException e) {
			throw new PublishException(e);
		}
	}
}
