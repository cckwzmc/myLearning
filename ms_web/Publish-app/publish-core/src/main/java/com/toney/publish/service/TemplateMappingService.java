package com.toney.publish.service;

import com.toney.dal.model.TemplateMappingModel;
import com.toney.publish.exception.ServiceException;
import com.toney.publish.model.PublishTplModel;

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
public interface TemplateMappingService {


	/**
	 * 根据publishTplModel计算出模板地址.
	 * @param publishTplModel
	 * @return
	 * @throws ServiceException
	 */
	TemplateMappingModel getTemplateMappingModel(PublishTplModel publishTplModel) throws ServiceException;
}
