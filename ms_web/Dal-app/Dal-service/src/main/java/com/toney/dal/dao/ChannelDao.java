package com.toney.dal.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.toney.dal.model.ChannelDetailModel;

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
	/**
	 * 通过父ID获取所有子频道
	 * @param parentId
	 * @param level
	 * @return
	 */
	public List<ChannelDetailModel> getChildrenChannelDetailList(@Param("parentId") Integer parentId, Integer level);
}
