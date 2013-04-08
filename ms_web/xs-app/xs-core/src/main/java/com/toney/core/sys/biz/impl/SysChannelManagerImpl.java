package com.toney.core.sys.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toney.core.constants.Constants;
import com.toney.core.exception.BusinessException;
import com.toney.core.sys.biz.SysChannelManager;
import com.toney.dal.dao.ChannelDao;
import com.toney.dal.model.ChannelDetailModel;
import com.toney.dal.model.ChannelTypeModel;
import com.toney.dal.service.DalMybatisManagerFactory;

/**
 * @author toney.li 频道管理的实现.
 */
@Service("sysChannelManager")
public class SysChannelManagerImpl implements SysChannelManager {

	@Autowired
	private DalMybatisManagerFactory dalMybatisManagerFactory;

	@Override
	public List<ChannelDetailModel> getAllEnabledChannelDetailModel() throws BusinessException {
		List<ChannelDetailModel> channelLists = this.dalMybatisManagerFactory.getChannelManagerFactory().getChannelDao().getAllChannelType();
		List<ChannelDetailModel> resultLevel1 =getTopChannelDetailList();
		
		// while(CollectionUtils.isNotEmpty(channelLists)){
		// ChannelDetailModel model=channelLists.remove(0);
		// this.mergeChannelDetailList(model,resultLevel1,channelLists);
		// }
		Boolean isExistSecond = false;
		for (int j = 0; j < resultLevel1.size(); j++) {
			ChannelDetailModel modelJ = resultLevel1.get(j);
			for (int i = 0; i < channelLists.size(); i++) {
				ChannelDetailModel modelI = channelLists.get(i);
				if (modelI.getTopId().intValue() == Constants.CHANNEL_SECOND_TYPE && modelJ.getId().intValue() == modelI.getReId().intValue()) {
					if (modelJ.getChildrens() == null) {
						List<ChannelDetailModel> childrenList = new ArrayList<ChannelDetailModel>();
						childrenList.add(modelI);
						modelJ.setChildrens(childrenList);
					} else {
						modelJ.getChildrens().add(channelLists.remove(i));
					}
					isExistSecond = true;
					i--;
				}
			}
		}
		if (!isExistSecond || CollectionUtils.isEmpty(channelLists)) {
			return resultLevel1;
		}
		for (int j = 0; j < resultLevel1.size(); j++) {
			ChannelDetailModel modelTop = resultLevel1.get(j);
			List<ChannelDetailModel> childrensSecond = modelTop.getChildrens();
			if (CollectionUtils.isEmpty(childrensSecond)) {
				continue;
			}
			for (int i = 0; i < childrensSecond.size(); i++) {
				ChannelDetailModel modelSecond = childrensSecond.get(i);
				for (int k = 0; k < channelLists.size(); k++) {
					ChannelDetailModel modelThird = childrensSecond.get(i);
					if (modelThird.getTopId().intValue() == Constants.CHANNEL_THIRD_TYPE && modelThird.getId().intValue() == modelSecond.getReId().intValue()) {
						if (modelSecond.getChildrens() == null) {
							List<ChannelDetailModel> childrenList = new ArrayList<ChannelDetailModel>();
							childrenList.add(modelSecond);
							modelSecond.setChildrens(childrenList);
						} else {
							modelSecond.getChildrens().add(channelLists.remove(i));
						}
						i--;
					}
				}
			}
		}
		// for(int i=0;i<channelLists.size();i++){
		// ChannelDetailModel model=channelLists.get(i);
		// if(model.getReId()==Constants.CHANNEL_THIRD_TYPE){
		// resultLevel1.add(channelLists.remove(i));
		// i++;
		// break;
		// }
		// }
		return resultLevel1;
	}

	/**
	 * @param parentId
	 * @param level
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public List<ChannelDetailModel>  getChildrenChannelDetailList(Integer parentId,Integer level) throws BusinessException{
		List<ChannelDetailModel> resultList=new ArrayList<ChannelDetailModel>();
		return this.dalMybatisManagerFactory.getChannelManagerFactory().getChannelDao().getChildrenChannelDetailList(parentId,level);
	}
	
	/**
	 * @param model
	 * @param resultChannelList
	 * @param channelLists
	 * @return
	 */
	private List<ChannelDetailModel> mergeChannelDetailList(ChannelDetailModel model, List<ChannelDetailModel> resultChannelList, List<ChannelDetailModel> channelLists) {
		Integer parentId = model.getReId();
		Integer level = model.getTopId();

		if (level.intValue() == Constants.CHANNEL_SECOND_TYPE) {
			for (ChannelDetailModel model1 : resultChannelList) {
				if (model1.getId().intValue() == parentId.intValue()) {
					List<ChannelDetailModel> childrens = model1.getChildrens();
					if (childrens == null) {
						childrens = new ArrayList<ChannelDetailModel>();
					}
					childrens.add(model);
					model1.setChildrens(childrens);
				}
			}
			return resultChannelList;
		} else {

		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.toney.core.sys.biz.SysChannelManager#getTopChannelDetailList()
	 */
	@Override
	public List<ChannelDetailModel> getTopChannelDetailList() throws BusinessException {
		List<ChannelDetailModel> resultLevel1=new ArrayList<ChannelDetailModel>();
		List<ChannelDetailModel> channelLists = this.dalMybatisManagerFactory.getChannelManagerFactory().getChannelDao().getAllChannelType();
		if (CollectionUtils.isEmpty(channelLists)) {
			return null;
		}
		for (int i = 0; i < channelLists.size(); i++) {
			ChannelDetailModel model = channelLists.get(i);
			if (model.getTopId() == Constants.CHANNEL_TOP_TYPE) {
				resultLevel1.add(channelLists.remove(i));
				i--;
			}
		}
		return resultLevel1;
	}
	
	@Override
	public List<ChannelTypeModel> getChannelTypeList() throws BusinessException{
		return this.dalMybatisManagerFactory.getChannelManagerFactory().getChannelTypeDao().getAllChannelType();
	}

}
