package com.toney.dal.service.mybatis;

import com.toney.dal.dao.TemplateManagerDao;
import com.toney.dal.dao.TemplateMappingDao;
import com.toney.dal.dao.TemplatePageTypeDao;

/**
 * 
 *************************************************************** 
 * <p>
 * @CLASS :TemplateManagerFactory
 * @DESCRIPTION :功能描述
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Dec 28, 2012
 *       </p>
 **************************************************************** 
 */
public interface TemplateManagerFactory {

	TemplateManagerDao getTemplateManagerDao();

	TemplatePageTypeDao getTemplatePageTypeDao();

	TemplateMappingDao getTemplateMappingDao();

}
