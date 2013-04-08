package com.toney.core.biz;

import java.util.List;

import com.toney.dal.model.ChannelDetailModel;

/**
 * @author toney.li
 * 频道管理
 */
public interface ChannelManager {

	/**
	 * @return
	 * 获取可见的顶级分类。
	 */
	List<ChannelDetailModel> getTopChannel();

}
