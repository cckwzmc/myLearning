package com.toney.dal.dao;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.toney.dal.model.ArticleSysTagModel;

public class ArticleSysTagDaoTest extends BaseDaoTestCase {
	@Autowired
	private ArticleSysTagDao articleSysTagDao;
	
	@Test
	public void testGetAllArticleSysTagModel(){
		List<ArticleSysTagModel> list=this.articleSysTagDao.getAllArticleSysTagModel();
		Assert.assertNotNull(list);
	}
}
