package com.toney.core.dao;

import java.util.List;

import com.toney.core.model.ChannelDetalModel;

/**
 * @author toney.li
 * 频道对应dede_arctype
 */
public interface ChannelDao {
	/**
	 * 获取未隐藏的频道。
	 * @return
	 */
	public List<ChannelDetalModel> getAllShowChannelType();
	/**
	 * 获取所有隐藏的频道。
	 * @return
	 */
	public List<ChannelDetalModel> getAllChannelType();
	public ChannelDetalModel getChannelTypeById(Integer id);
}
