package com.toney.core.sys.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toney.core.constants.Constants;
import com.toney.core.dao.ChannelDao;
import com.toney.core.exception.BusinessException;
import com.toney.core.model.ChannelDetailModel;
import com.toney.core.sys.biz.SysChannelManager;

/**
 * @author toney.li
 * 频道管理的实现.
 */
@Service("sysChannelManager")
public class SysChannelManagerImpl implements SysChannelManager {

	@Autowired
	private ChannelDao channelDao;
	@Override
	public List<ChannelDetailModel> getAllEnabledChannelDetailModel()
			throws BusinessException {
		 List<ChannelDetailModel> resultLevel1=new ArrayList<ChannelDetailModel>();
		 List<ChannelDetailModel> resultLevel2=new ArrayList<ChannelDetailModel>();
		 List<ChannelDetailModel> resultLevel3=new ArrayList<ChannelDetailModel>();
		 List<ChannelDetailModel>  channelLists=this.channelDao.getAllChannelType();
		 if(CollectionUtils.isEmpty(channelLists)){
			 return null;
		 }
		 for(int i=0;i<channelLists.size();i++){
			 ChannelDetailModel model=channelLists.get(i);
			 if(model.getTopId()==Constants.CHANNEL_CHILDRENS_TYPE){
				 resultLevel1.add(channelLists.remove(i));
				 i++;
				 break;
			 }
		 }
		 for(int i=0;i<channelLists.size();i++){
			 ChannelDetailModel model=channelLists.get(i);
			 for (int j = 0; j < resultLevel1.size(); j++) {
				 if(model.getReId()==Constants.CHANNEL_CHILDRENS_TYPE){
					 resultLevel1.add(channelLists.remove(i));
					 i++;
					 break;
				 }
			}
		 }
		 for(int i=0;i<channelLists.size();i++){
			 ChannelDetailModel model=channelLists.get(i);
			 if(model.getTopId()==Constants.CHANNEL_CHILDRENS_TYPE){
				 resultLevel1.add(channelLists.remove(i));
				 i++;
				 break;
			 }
		 }
		return resultLevel1;
	}

}
