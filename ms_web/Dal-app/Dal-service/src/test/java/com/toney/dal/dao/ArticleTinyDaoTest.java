package com.toney.dal.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.toney.dal.constants.Constants;
import com.toney.dal.model.ArticleTinyModel;

public class ArticleTinyDaoTest extends BaseDaoTestCase {
	@Autowired
	private ArticleTinyDao articleTinyDao;
	
	@Test
	public void testGetPageArticleTiny(){
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("beginRow", 0);
		map.put("pageSize", Constants.DEFAULT_PAGE_SIZE);
		List<ArticleTinyModel> list=this.articleTinyDao.getPageArticleTiny(map);
		Assert.assertNotNull(list);
		Assert.assertEquals(1,list.size());
	}
	@Test
	public void testGetPublishPageArticleTiny(){
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("beginRow", 0);
		map.put("pageSize", Constants.DEFAULT_PAGE_SIZE);
		List<ArticleTinyModel> list=this.articleTinyDao.getPublishPageArticleTiny(map);
		Assert.assertNotNull(list);
		Assert.assertEquals(1,list.size());
	}
	@Test
	public void testGetArticleTinyById(){
		ArticleTinyModel articleTinyModel=this.articleTinyDao.getArticleTinyById(1l);
		Assert.assertNotNull(articleTinyModel);
	}
}
