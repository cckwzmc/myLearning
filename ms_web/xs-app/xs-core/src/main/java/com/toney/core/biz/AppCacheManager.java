package com.toney.core.biz;

import java.util.List;
import java.util.Map;

import com.toney.core.exception.BusinessException;
import com.toney.dal.model.ChannelDetailModel;
import com.toney.dal.model.ChannelTypeModel;
import com.toney.dal.model.SysConfigModel;

/**
 * 
 *************************************************************** 
 * <p>
 * 
 * @CLASS :AppCacheManager
 * @DESCRIPTION :web 层的缓存管理接口
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Jan 4, 2013
 *       </p>
 **************************************************************** 
 */
/**
 * 
 *************************************************************** 
 * <p>
 * @CLASS :AppCacheManager
 * @DESCRIPTION :功能描述
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Jan 4, 2013
 *       </p>
 **************************************************************** 
 */
public interface AppCacheManager {

	/**
	 * 频道数据
	 * @return 
	 * @throws BusinessException 
	 */
	public List<ChannelDetailModel> getChannelListData() throws BusinessException;

	/**
	 * 更新频道数据
	 * @throws BusinessException 
	 */
	public void refreshChannelListData() throws BusinessException;

	/**
	 * 全局配置 
	 * @return key /value 
	 * @throws BusinessException
	 */
	Map<String, String> getGlobalMapConfiguration() throws BusinessException;

	/**
	 * 全局配置
	 * @return  Map<String, SysConfigModel>
	 * @throws BusinessException
	 */
	Map<String, SysConfigModel> getGlobalModelConfiguration() throws BusinessException;
	/**
	 * 更新全局配置缓存
	 * 
	 * @throws BusinessException
	 */
	void refreshGlobalConfiguration() throws BusinessException;

	/**
	 * 文章类型 image/article/software etc..
	 * @return
	 * @throws BusinessException
	 */
	List<ChannelTypeModel> getAppArtType() throws BusinessException;

}
