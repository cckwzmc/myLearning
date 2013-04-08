package com.toney.dal.dao;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.toney.dal.model.ChannelTypeModel;
import com.toney.dal.service.DalMybatisManagerFactory;

public class ChannelTypeDaoTest extends BaseDaoTestCase {
	@Autowired 
//	private ChannelTypeDao channelTypeDao;
	private DalMybatisManagerFactory  dalMybatisManagerFactory;
	
	@Test
	public void testGetAllEnabledChannelType(){
		List<ChannelTypeModel> list=this.dalMybatisManagerFactory.getChannelManagerFactory().getChannelTypeDao().getAllEnabledChannelType();
		Assert.assertNotNull(list);
		Assert.assertEquals(6, list.size());
	}
	@Test
	public void testGetAllChannelType(){
		List<ChannelTypeModel> list=this.dalMybatisManagerFactory.getChannelManagerFactory().getChannelTypeDao().getAllChannelType();
		Assert.assertNotNull(list);
		Assert.assertEquals(6, list.size());
	}
	@Test
	public void testGetChannelTypeById(){
		ChannelTypeModel channelTypeModel=this.dalMybatisManagerFactory.getChannelManagerFactory().getChannelTypeDao().getChannelTypeById(1);
		Assert.assertNotNull(channelTypeModel);
		Assert.assertEquals(1, channelTypeModel.getId().intValue());
	}
	@Test
	public void testGetChannelTypeByNId(){
		ChannelTypeModel channelTypeModel = this.dalMybatisManagerFactory.getChannelManagerFactory().getChannelTypeDao().getChannelTypeByNId("article");
		Assert.assertNotNull(channelTypeModel);
		Assert.assertEquals("article", channelTypeModel.getNid());
	}
}
