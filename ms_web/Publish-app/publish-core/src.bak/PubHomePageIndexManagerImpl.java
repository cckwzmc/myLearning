package com.toney.publish.biz.impl;

import java.io.IOException;
import java.util.HashMap;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toney.dal.model.TemplateMappingModel;
import com.toney.publish.biz.AbstractPublishPageManager;
import com.toney.publish.biz.PubHomePageIndexManager;
import com.toney.publish.biz.TemplateMappingManager;
import com.toney.publish.exception.PublishException;
import com.toney.publish.exception.ServiceException;
import com.toney.publish.tpl.PublishContext;
import com.toney.publish.tpl.service.PublishOutputService;

import freemarker.template.TemplateException;

/**
 * 
 *************************************************************** 
 * <p>
 * 
 * @CLASS :PubHomePageIndexManagerImpl
 * @DESCRIPTION :发布首页
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Jan 6, 2013
 *       </p>
 **************************************************************** 
 */
@Service("pubHomePageIndexManager")
public class PubHomePageIndexManagerImpl extends AbstractPublishPageManager implements PubHomePageIndexManager {

	private static final XLogger logger = XLoggerFactory.getXLogger(PubHomePageIndexManagerImpl.class);

	@Autowired
	private PublishOutputService publishOutputService;
	@Autowired
	private TemplateMappingManager templateMappingManager;
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.toney.publish.biz.PublishPageManager#doPublish(com.toney.publish.
	 * tpl.PublishContext)
	 */
	public void doPublish(PublishContext context) throws PublishException {
		TemplateMappingModel tplPath = this.getTemplateMappingModel(context);
		try {
			publishOutputService.publishFile(tplPath.getOutputFilename(), tplPath.getTplPath(), new HashMap<String, Object>());
		} catch (IOException e) {
			logger.error("IOException", e);
			throw new PublishException(e);
		} catch (TemplateException e) {
			logger.error("TemplateException", e);
			throw new PublishException(e);
		}
	}

	@Override
	public TemplateMappingModel getTemplateMappingModel(PublishContext context) throws PublishException {
		try {
			return templateMappingManager.getTemplateMappingModel(context.getPublishTplModel());
		} catch (ServiceException e) {
			throw new PublishException(e);
		}
	}

	@Override
	public void doPublish() throws PublishException {
		// TODO Auto-generated method stub
		
	}

}
