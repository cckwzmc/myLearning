package com.toney.core.dao;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.toney.core.model.ChannelDetailModel;

/**
 * @author toney.li
 *
 */
public class ChannelDaoTest extends BaseDaoTestCase {
	@Autowired
	private ChannelDao channelDao;
	@Test
	public void testGetAllShowChannelType(){
		List<ChannelDetailModel> list=channelDao.getAllShowChannelType();
		Assert.assertNotNull(list);
	}
	@Test
	public void testGetAllChannelType(){
		List<ChannelDetailModel> list=channelDao.getAllChannelType();
		Assert.assertNotNull(list);
	}
	@Test
	public void testGetChannelTypeById(){
		ChannelDetailModel channelDetailModel=channelDao.getChannelTypeById(1);
		Assert.assertNotNull(channelDetailModel);
	}
	
}
