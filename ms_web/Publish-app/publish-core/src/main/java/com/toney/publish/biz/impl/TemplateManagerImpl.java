package com.toney.publish.biz.impl;

import static com.toney.publish.constants.Constants.FILE_SEPARATOR;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.toney.dal.model.TemplateMappingModel;
import com.toney.publish.biz.TemplateManager;
import com.toney.publish.context.PublishContext;
import com.toney.publish.exception.PublishBusinessException;
import com.toney.publish.exception.ServiceException;
import com.toney.publish.model.TemplateDetailModel;
import com.toney.publish.service.TemplateMappingService;

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
@Service("templateManager")
public class TemplateManagerImpl implements TemplateManager {
	private static final XLogger logger = XLoggerFactory.getXLogger(TemplateManagerImpl.class);
	private TemplateMappingService templateMappingManager;
    private static Map<Integer, String> tplMap=new HashMap<Integer, String>();
    
    @Value("${tpl.absolute.path}")
    private String rootPath;
	/* (non-Javadoc)
	 * @see com.toney.publish.biz.TemplateManager#getTemplateDetail(com.toney.publish.context.PublishContext)
	 */
	@Override
	public TemplateDetailModel getTemplateDetail(PublishContext context) throws PublishBusinessException {
		try {
			TemplateMappingModel mpModel=this.templateMappingManager.getTemplateMappingModel(context.getPublishTplModel());
			TemplateDetailModel result=new TemplateDetailModel();
			result.setTemplateMappingModel(mpModel);
			result.setContent(this.getTemplateContent(mpModel));
		} catch (ServiceException e) {
			logger.error("获取模版",e);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取模版具体内容.
	 * @param mpModel
	 * @return
	 */
	private synchronized String getTemplateContent(TemplateMappingModel mpModel) {
		if(tplMap!=null&&StringUtils.isNotBlank(ObjectUtils.toString(tplMap.get(mpModel.getId())))){
			return tplMap.get(mpModel.getId());
		}else{
			String filePath=rootPath+FILE_SEPARATOR+mpModel.getTplPrefix()+FILE_SEPARATOR+mpModel.getTplPath();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.toney.publish.biz.TemplateManager#getTemplateDetail(java.lang.Long)
	 */
	@Override
	public TemplateDetailModel getTemplateDetail(Long publicId) throws PublishBusinessException {
		return null;
	}

}
