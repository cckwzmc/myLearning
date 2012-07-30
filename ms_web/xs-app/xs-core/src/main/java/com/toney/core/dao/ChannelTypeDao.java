package com.toney.core.dao;

import java.util.List;

import com.toney.core.model.ChannelTypeModel;

/**
 * @author toney.li
 * 频道类型
 * 
 */
public interface ChannelTypeDao {
	/**
	 * 获取所有可用的频道类型。
	 * @return
	 */
	public List<ChannelTypeModel> getAllEnabledChannelType();
	public List<ChannelTypeModel> getAllChannelType();
	public ChannelTypeModel getChannelTypeById(Integer id);
	/**
	 * 如:article,image,shop,soft,spec 专题,infos 分类信息 等
	 * @param nameId
	 * @return
	 */
	public ChannelTypeModel getChannelTypeByNId(String nameId);
			
}
