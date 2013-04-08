package com.toney.publish.service.impl;

import static com.toney.commons.constants.CommonsConstants.TPL_HOME_PAGE_INDEX;

import java.util.List;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toney.dal.model.TemplateMappingModel;
import com.toney.dal.service.DalMybatisManagerFactory;
import com.toney.publish.exception.ServiceException;
import com.toney.publish.model.PublishTplModel;
import com.toney.publish.service.TemplateMappingService;

/**
 * 
 *************************************************************** 
 * <p>
 * @CLASS :TemplateMappingManager
 * @DESCRIPTION :模板管理
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Dec 31, 2012
 *       </p>
 **************************************************************** 
 */
@Service("templateMappingManager")
public class TemplateMappingServiceImpl implements TemplateMappingService {
	private static final XLogger logger=XLoggerFactory.getXLogger(TemplateMappingServiceImpl.class);
	@Autowired
	DalMybatisManagerFactory dalMybatisManagerFactory;
	
	@Override
	public TemplateMappingModel getTemplateMappingModel(PublishTplModel publishTplModel) throws ServiceException{
		List<TemplateMappingModel> mappingList=this.dalMybatisManagerFactory.getTemplateManagerFactory().getTemplateMappingDao().getAll();
		if(TPL_HOME_PAGE_INDEX.equals(publishTplModel.getTplTypeCode())){
			for(TemplateMappingModel model:mappingList){
				if(TPL_HOME_PAGE_INDEX.equalsIgnoreCase(model.getPageTypeCode())){
					return model;
				}
			}
		}
		return null;
	}
}
