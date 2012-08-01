package com.toney.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.toney.core.model.ChannelDetailModel;

/**
 * @author toney.li
 * 频道对应dede_arctype
 */
@Repository("channelDao")
public interface ChannelDao {
	/**
	 * 获取未隐藏的频道。
	 * @return
	 */
	public List<ChannelDetailModel> getAllShowChannelType();
	/**
	 * 获取所有隐藏的频道。
	 * @return
	 */
	public List<ChannelDetailModel> getAllChannelType();
	public ChannelDetailModel getChannelTypeById(Integer id);
}
