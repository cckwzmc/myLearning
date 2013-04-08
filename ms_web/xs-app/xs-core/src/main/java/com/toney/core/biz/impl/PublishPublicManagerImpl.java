package com.toney.core.biz.impl;

import java.util.List;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toney.commons.lang.Page;
import com.toney.core.biz.PublishPublicManager;
import com.toney.core.exception.BusinessException;
import com.toney.dal.bean.PublicSearchBean;
import com.toney.dal.dao.TplPublicMappingDao;
import com.toney.dal.model.TplPublicMappingModel;
import com.toney.publish.hessian.PublishHessian;

/**
 * 
 *************************************************************** 
 * <p>
 * @CLASS :PublishPublicManagerImpl
 * @DESCRIPTION :出版公共页面实现。
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Jan 24, 2013
 *       </p>
 **************************************************************** 
 */
@Service("publishPublicManager")
public class PublishPublicManagerImpl implements PublishPublicManager {

	private static final XLogger logger=XLoggerFactory.getXLogger(PublishPublicManagerImpl.class);
	
	@Autowired
	private TplPublicMappingDao tplPublicMappingDao;
	@Autowired
	private PublishHessian publishHessianClient;
	
	@Override
	public Page<TplPublicMappingModel> getTplPublicMappingModelAll(Page<TplPublicMappingModel> page,PublicSearchBean publicSearch) throws BusinessException {
		 List<TplPublicMappingModel> list =this.tplPublicMappingDao.selectBySearch(page.getFirstRecord(),page.getPageSize(),page.getOrderBy(),publicSearch);
		 page.setResult(list);
		 return page;
	}

	@Override
	public void publishPublic(Long publicId) throws BusinessException {
		try{
			this.publishHessianClient.publicPublish(publicId);
		}catch(Exception e){
			logger.error("发布公共页面失败,publicId="+publicId,e);
			throw new BusinessException(e);
		}
	}

	@Override
	public void modifyEnabled(Long publicId, Integer isEnabled) throws BusinessException {
		
	}

	@Override
	public void publishPublic(Long[] publicId) throws BusinessException {
		
	}

	@Override
	public void batchModifyEnabled(Long[] publicId) throws BusinessException {
		
	}

	@Override
	public void batchModifyDisabled(Long[] publicId) throws BusinessException {
		
	}

	@Override
	public void batchPublish(Long[] publicId) throws BusinessException {
		
	}

}
