package com.toney.dal.service.mybatis;

import org.springframework.stereotype.Service;

import com.toney.dal.dao.ChannelDao;
import com.toney.dal.dao.ChannelTypeDao;

/**
 * 
 *************************************************************** 
 * <p>
 * @CLASS :ChannelManagerFactory
 * @DESCRIPTION :频道 Dao 管理
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Dec 28, 2012
 *       </p>
 **************************************************************** 
 */
@Service
public interface ChannelManagerFactory {
	
	public ChannelTypeDao getChannelTypeDao();

	public  ChannelDao getChannelDao();

}
