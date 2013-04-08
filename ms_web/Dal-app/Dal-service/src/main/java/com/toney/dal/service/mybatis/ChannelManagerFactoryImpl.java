package com.toney.dal.service.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toney.dal.dao.ChannelDao;
import com.toney.dal.dao.ChannelTypeDao;

/**
 * 
 *************************************************************** 
 * <p>
 * @CLASS :ChannelManagerFactoryImpl
 * @DESCRIPTION :频道管理工厂类
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Dec 28, 2012
 *       </p>
 **************************************************************** 
 */
@Service("channelManagerFactory")
public class ChannelManagerFactoryImpl implements ChannelManagerFactory {
	@Autowired
	private ChannelTypeDao channelTypeDao;
	@Autowired
	private ChannelDao channelDao;

	@Override
	public ChannelDao getChannelDao() {
		return channelDao;
	}

	@Override
	public ChannelTypeDao getChannelTypeDao() {
		return this.channelTypeDao;
	}
}
