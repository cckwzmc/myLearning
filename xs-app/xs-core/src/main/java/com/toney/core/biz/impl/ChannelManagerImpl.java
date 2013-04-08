package com.toney.core.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.toney.core.biz.ChannelManager;
import com.toney.core.constants.Constants;
import com.toney.dal.dao.ChannelDao;
import com.toney.dal.model.ChannelDetailModel;

/**
 * @author toney.li
 *
 */
@Service("channelManager")
@Transactional
public class ChannelManagerImpl implements ChannelManager {
	@Autowired
	private ChannelDao channelDao;
	
	/* (non-Javadoc)
	 * @see com.toney.core.biz.ChannelManager#getTopChannel()
	 */
	@Transactional(readOnly=true)
	@Override
	public List<ChannelDetailModel> getTopChannel(){
		List<ChannelDetailModel>  allChannel=this.channelDao.getAllShowChannelType();
		List<ChannelDetailModel> resultList=new ArrayList<ChannelDetailModel>();
		for(ChannelDetailModel channel:allChannel){
			if(channel.getTopId()==Constants.CHANNEL_TOP_TYPE){
				resultList.add(channel);
			}
		}
		return resultList;
	}
	
}
