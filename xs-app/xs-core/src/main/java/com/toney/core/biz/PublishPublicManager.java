package com.toney.core.biz;

import com.toney.commons.lang.Page;
import com.toney.core.exception.BusinessException;
import com.toney.dal.bean.PublicSearchBean;
import com.toney.dal.model.TplPublicMappingModel;

/**
 * 
 *************************************************************** 
 * <p>
 * @CLASS :PublishPublicManager
 * @DESCRIPTION :出版公共页面（页头/页尾）
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Jan 24, 2013
 *       </p>
 **************************************************************** 
 */
public interface PublishPublicManager {
	
	Page<TplPublicMappingModel> getTplPublicMappingModelAll(Page<TplPublicMappingModel> page, PublicSearchBean publicSearch) throws BusinessException;
	
	void publishPublic(Long publicId) throws BusinessException;

	void modifyEnabled(Long publicId, Integer isEnabled) throws BusinessException;

	void publishPublic(Long[] publicId) throws BusinessException;

	void batchModifyEnabled(Long[] publicId) throws BusinessException;

	void batchModifyDisabled(Long[] publicId) throws BusinessException;

	void batchPublish(Long[] publicId) throws BusinessException;

}
