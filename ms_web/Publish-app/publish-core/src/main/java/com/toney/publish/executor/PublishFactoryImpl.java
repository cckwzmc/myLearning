package com.toney.publish.executor;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.toney.commons.constants.CommonsConstants;
import com.toney.publish.context.PublishContext;
import com.toney.publish.exception.PublishException;
import com.toney.publish.service.TemplateMappingService;
import com.toney.publish.tpl.service.PublishOutputService;

import freemarker.template.TemplateException;

/**
 * 
 *************************************************************** 
 * <p>
 * 
 * @CLASS :com.toney.publish.tpl.service.PublishFactory
 * @DESCRIPTION :功能描述
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Dec 18, 2012
 *       </p>
 **************************************************************** 
 */
@Service("publishFactory")
public class PublishFactoryImpl implements PublishFactory {
	private static final XLogger logger=XLoggerFactory.getXLogger(PublishFactoryImpl.class);
	/**
	 * 详细页出版线程池。
	 */
	@Autowired
	private ThreadPoolTaskExecutor detailTaskExecutor;
	@Autowired
	private ThreadPoolTaskExecutor channelTaskExecutor;
	@Autowired
	private ThreadPoolTaskExecutor activityTaskExecutor;
	/**
	 * 不在上面三个的线程使用这个线程池。
	 */
	@Autowired
	private ThreadPoolTaskExecutor commonTaskExecutor;
	
	@Autowired
	PublishOutputService publishOutputService;
	@Autowired
	TemplateMappingService templateMappingManager;
	@Autowired
	PulicPagePublishManager pulicPagePublishManager; 
	
	@Override
	public void publish(PublishContext context) throws PublishException{
		//如果是手动出版调整线程的等级为最高
		if(context.getIsManualPublish()!=null&&context.getIsManualPublish()){
			
		}
		if(StringUtils.isBlank(context.getPublishTplModel().getTplTypeCode())){
			throw new PublishException("context.getPublishTplModel().getTplTypeCode() is null !");
		}
		if(CommonsConstants.TPL_HOME_PAGE_INDEX.equals(context.getPublishTplModel().getTplTypeCode())){
			this.commonTaskExecutor.execute(new PublishHomePageService(context,publishOutputService,templateMappingManager));
		}else{
			
		}
	}
	
	/**
	 * 用户于直接出版，
	 * 这个接口暂不对外开放.
	 * @param outFile 输出目录
	 * @param tpl 模板路径
	 * @param data 绑定数据
	 * @throws PublishException
	 */
	@Deprecated
	public void directPublish(String outFile,String tpl,Map<String,Object> data) throws PublishException{
		try {
			this.publishOutputService.publishFile(outFile, tpl, data);
		} catch (IOException e) {
			logger.error(" IO Exception ",e);
			throw new PublishException(e);
		} catch (TemplateException e) {
			logger.error("Template Exception ",e);
			throw new PublishException(e);
		}
	}

	@Override
	public void publishPublic(Long publicId) throws PublishException {
		pulicPagePublishManager.publish(publicId);
	}
}
