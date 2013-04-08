package com.toney.publish.executor;

import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.toney.dal.model.TemplateMappingModel;
import com.toney.publish.context.PublishContext;
import com.toney.publish.exception.PublishException;
import com.toney.publish.exception.ServiceException;
import com.toney.publish.service.TemplateMappingService;
import com.toney.publish.tpl.service.PublishOutputService;

import freemarker.template.TemplateException;

/**
 * 
 *************************************************************** 
 * <p>
 * 
 * @CLASS :PublishHomePageService
 * @DESCRIPTION :首页发布线程.
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Jan 6, 2013
 *       </p>
 **************************************************************** 
 */
@Service("publishHomePageService")
@Lazy(true)
public class PublishHomePageService extends AbstractPublishPageManager
implements Runnable, PublishPageManager {
	private static final XLogger logger = XLoggerFactory.getXLogger(PublishHomePageService.class);

	private PublishContext context;

	private static  PublishOutputService publishOutputService;

	
	public static PublishOutputService getPublishOutputService() {
		return publishOutputService;
	}

	public static void setPublishOutputService(PublishOutputService publishOutputService) {
		PublishHomePageService.publishOutputService = publishOutputService;
	}

	public PublishHomePageService() {

	}

	public PublishHomePageService(PublishContext context,PublishOutputService publishOutputService,TemplateMappingService templateMappingManager) {
		this.context = context;
		setPublishOutputService(publishOutputService);
		super.setTemplateMappingManager(templateMappingManager);
	}

	public void doPublish() throws PublishException {
		long startTime = System.currentTimeMillis();
		logger.debug(this.context.getPublishTplModel().getTplTypeCode() + " process start " + startTime);
		// start publish
		try {
			TemplateMappingModel tplPath = super.getTemplateMappingModel(context);
			publishOutputService.publishFile(tplPath.getOutputFilename(), tplPath.getTplPath(), new HashMap<String, Object>());
		} catch (IOException e) {
			logger.error("IOException", e);
			throw new PublishException(e);
		} catch (TemplateException e) {
			logger.error("TemplateException", e);
			throw new PublishException(e);
		} catch (PublishException e) {
			logger.error("Service Exception ", e);
			throw new PublishException(e);
		}
		// end publish
		long endTime = System.currentTimeMillis();
		logger.debug(this.context.getPublishTplModel().getTplTypeCode() + " process start " + endTime);
		logger.info("publish "+StringUtils.replace(ToStringBuilder.reflectionToString(context), "{}", "\\{\\}")+" cost time {}",String.valueOf(endTime-startTime));
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			logger.error("Thread sleep error ", e);
		}
	}

	@Override
	public void run() {
		try {
			this.doPublish();
		} catch (PublishException e) {
			logger.error(this.getClass().getName() + "process ", e);
		}
	}

}
