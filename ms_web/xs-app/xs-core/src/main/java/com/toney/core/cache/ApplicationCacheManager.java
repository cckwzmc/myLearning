package com.toney.core.cache;

import java.util.List;
import java.util.Map;

import com.toney.core.exception.BusinessException;
import com.toney.core.model.ChannelDetailModel;
import com.toney.core.model.ChannelTypeModel;
import com.toney.core.model.SysConfigModel;
import com.toney.core.model.TemplateManagerModel;

/**
 * @author toney.li
 * 全站上下文的缓存管理/频道数据/文章内容数据。
 */
public interface ApplicationCacheManager {
	
	/**
	 * @return
	 * @throws BusinessException
	 * 初始化全站上下文的缓存
	 */
	public Map<String, SysConfigModel> initApplicationContext() throws BusinessException;
	/**
	 * @return
	 * @throws BusinessException
	 * 获取全站上下文的缓存
	 */
	public Map<String,SysConfigModel> getApplicatonContext() throws BusinessException;
	/**
	 * @return
	 * @throws BusinessException
	 * 全站模版管理缓存
	 */
	public List<TemplateManagerModel> initTemplageManager() throws BusinessException;
	/**
	 * @return
	 * @throws BusinessException
	 * 获取全站模版管理的缓存
	 */
	public List<TemplateManagerModel> getTemplageManagerData() throws BusinessException;
	
	/**
	 * @throws BusinessException
	 * 刷新缓存
	 */
	
	public void refresh(String key) throws BusinessException;
	/**
	 * @return
	 * @throws BusinessException
	 * 频道数据。
	 */
	public List<ChannelDetailModel> initAppChannel() throws BusinessException;
	/**
	 * @return
	 * @throws BusinessException
	 * 文章类型数据。
	 */
	public List<ChannelTypeModel> initAppArtType() throws BusinessException;
	
	/**
	 * @return
	 * @throws BusinessException
	 * 获取频道数据。
	 */
	public List<ChannelDetailModel>  getAppChannel() throws BusinessException;
	/**
	 * @return
	 * @throws BusinessException
	 * 获取文章类型数据。
	 */
	public List<ChannelTypeModel> getAppArtType() throws BusinessException;
}
