package com.toney.core.dao;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.toney.core.model.ChannelTypeModel;

public class ChannelTypeDaoTest extends BaseDaoTestCase {
	@Autowired 
	private ChannelTypeDao channelTypeDao;
	
	@Test
	public void testGetAllEnabledChannelType(){
		List<ChannelTypeModel> list=this.channelTypeDao.getAllEnabledChannelType();
		Assert.assertNotNull(list);
		Assert.assertEquals(6, list.size());
	}
	@Test
	public void testGetAllChannelType(){
		List<ChannelTypeModel> list=this.channelTypeDao.getAllChannelType();
		Assert.assertNotNull(list);
		Assert.assertEquals(6, list.size());
	}
	@Test
	public void testGetChannelTypeById(){
		ChannelTypeModel channelTypeModel=this.channelTypeDao.getChannelTypeById(1);
		Assert.assertNotNull(channelTypeModel);
		Assert.assertEquals(1, channelTypeModel.getId().intValue());
	}
	@Test
	public void testGetChannelTypeByNId(){
		ChannelTypeModel channelTypeModel=this.channelTypeDao.getChannelTypeByNId("article");
		Assert.assertNotNull(channelTypeModel);
		Assert.assertEquals("article", channelTypeModel.getNid());
	}
}
