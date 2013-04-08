package com.toney.dal.service.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toney.dal.dao.TemplateManagerDao;
import com.toney.dal.dao.TemplateMappingDao;
import com.toney.dal.dao.TemplatePageTypeDao;

/**
 * 
 *************************************************************** 
 * <p>
 * @CLASS :TemplateManagerFactoryImpl
 * @DESCRIPTION :功能描述
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Dec 28, 2012
 *       </p>
 **************************************************************** 
 */
@Service("templateManagerFactory")
public class TemplateManagerFactoryImpl implements TemplateManagerFactory {
	@Autowired
	private TemplateManagerDao templateManagerDao;
	@Autowired
	private TemplateMappingDao templateMappingDao;
	@Autowired
	private TemplatePageTypeDao templatePageTypeDao;
	@Override
	public TemplateManagerDao getTemplateManagerDao() {
		return templateManagerDao;
	}
	@Override
	public TemplateMappingDao getTemplateMappingDao() {
		return templateMappingDao;
	}
	@Override
	public TemplatePageTypeDao getTemplatePageTypeDao() {
		return templatePageTypeDao;
	}
	
	
}
