package com.toney.istyle.core.biz.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toney.istyle.bo.PlatFormBO;
import com.toney.istyle.core.biz.PlatFormManager;
import com.toney.istyle.core.exception.ManagerException;
import com.toney.istyle.core.exception.ServiceException;
import com.toney.istyle.core.platform.PlatFormQueryService;
import com.toney.istyle.form.PlatFormForm;
import com.toney.istyle.module.PlatformModule;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :PlatFormManagerImpl.java
 * @DESCRIPTION : platform business service
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 25, 2013
 *       </p>
 **************************************************************** 
 */
@Service("platFormManager")
public class PlatFormManagerImpl implements PlatFormManager {
	private static final XLogger LOGGER = XLoggerFactory.getXLogger(PlatFormManagerImpl.class);

	@Autowired
	private PlatFormQueryService platFormQueryService;
	
	
	/* 
	 * (non-Javadoc)
	 * @see com.toney.istyle.core.biz.PlatFormManager#getPlatFormAll()
	 */
	@Override
	public List<PlatFormForm> getPlatFormAll() throws ManagerException {
		
		try {
			List<PlatFormBO> mList= platFormQueryService.getPlatFormAll();
			List<PlatFormForm> formList=null;
			if(CollectionUtils.isNotEmpty(formList)){
				formList=new ArrayList<PlatFormForm>();
				for(PlatFormBO bo:mList){
					PlatFormForm form=new PlatFormForm();
					BeanUtils.copyProperties(form, bo);
					formList.add(form);
				}
			}
			return  formList;
		} catch (ServiceException e) {
			LOGGER.error("获取所有平台商信息失败", e);
			throw new ManagerException(e);
		} catch (IllegalAccessException e) {
			LOGGER.error("获取所有平台商信息失败", e);
			throw new ManagerException(e);
		} catch (InvocationTargetException e) {
			LOGGER.error("获取所有平台商信息失败", e);
			throw new ManagerException(e);
		}
		
	}


	/*
	 *  (non-Javadoc)
	 * @see com.toney.istyle.core.biz.PlatFormManager#getPlatFormById(java.lang.Long)
	 * 
	 */
	@Override
	public PlatFormForm getPlatFormById(Long id) throws ManagerException {
		try {
			PlatFormBO	bo= this.platFormQueryService.getPlatFormById(id);
			if(bo!=null){
				PlatFormForm form=new PlatFormForm();
				BeanUtils.copyProperties(form, bo);
				return form;
			}
		} catch (ServiceException e) {
			LOGGER.error("根据ID获取平台商信息失败 id:{}", id);
			throw new ManagerException(e);
		} catch (IllegalAccessException e) {
			LOGGER.error("根据ID获取平台商信息失败 id:{}", id);
			throw new ManagerException(e);
		} catch (InvocationTargetException e) {
			LOGGER.error("根据ID获取平台商信息失败 id:{}", id);
			throw new ManagerException(e);
		}
		return null;
	}
}
