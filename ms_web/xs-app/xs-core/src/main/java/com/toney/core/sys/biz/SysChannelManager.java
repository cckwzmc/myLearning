package com.toney.core.sys.biz;

import java.util.List;

import com.toney.core.exception.BusinessException;
import com.toney.core.model.ChannelDetailModel;

/**
 * @author toney.li
 * 频道管理
 */
public interface SysChannelManager {
	
	public List<ChannelDetailModel> getAllEnabledChannelDetailModel() throws BusinessException;
}
