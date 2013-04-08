package com.toney.dal.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.toney.bean.util.LabelValue;
import com.toney.dal.bean.PublicSearchBean;
import com.toney.dal.model.TplPublicMappingModel;

public class TplPublicMappingDaoTest extends BaseDaoTestCase {
	
	@Autowired
	private TplPublicMappingDao tplPublicMappingDao;
	
	@Test
	public void testSelectByAll(){
		List<LabelValue> orderBy=new ArrayList<LabelValue>();
		LabelValue e=new LabelValue();
		e.setLabel("id");
		e.setValue("desc");
		orderBy.add(e);
		PublicSearchBean bean=new PublicSearchBean();
//		bean.setDescription("页头导航-nav");
		bean.setIsEnabled(1);
		List<TplPublicMappingModel> list1=this.tplPublicMappingDao.selectByAll(0, 15, orderBy);
		List<TplPublicMappingModel> list= this.tplPublicMappingDao.selectBySearch(0, 15, orderBy,bean);
		Assert.assertNotNull(list1);
		Assert.assertTrue(list.size()>0);
	}
}
