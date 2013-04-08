package com.toney.core.sys.biz;

import java.util.List;

import com.toney.core.exception.BusinessException;
import com.toney.dal.model.ChannelDetailModel;
import com.toney.dal.model.ChannelTypeModel;

/**
 * @author toney.li
 * 频道管理
 */
public interface SysChannelManager {
	
	public List<ChannelDetailModel> getAllEnabledChannelDetailModel() throws BusinessException;

	List<ChannelDetailModel> getChildrenChannelDetailList(Integer parentId, Integer level) throws BusinessException;

	/**
	 * 获取顶层的频道
	 * @return
	 * @throws BusinessException 
	 */
	public List<ChannelDetailModel> getTopChannelDetailList() throws BusinessException;

	/**
	 * 获取说有的文章类型.
	 * @return
	 * @throws BusinessException
	 */
	List<ChannelTypeModel> getChannelTypeList() throws BusinessException;
}
