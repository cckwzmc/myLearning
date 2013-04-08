package com.toney.publish.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toney.dal.dao.ArticleDao;
import com.toney.dal.model.ArticleModel;
import com.toney.dal.model.ArticleQueryModel;
import com.toney.publish.biz.ArticleManager;

/**
 * @author toney.li
 * 文章管理.
 */
@Service("articleManager")
public class ArticleManagerImpl implements ArticleManager {

	@Autowired
	private ArticleDao articleDao;
	
	@Override
	public List<ArticleModel> getArticleList(String orderBy, Integer row, String channelIds, String arctag) {
		ArticleQueryModel model=new ArticleQueryModel();
		model.setOrderBy(orderBy);
		model.setRow(row);
		model.setChannelIds(channelIds);
		model.setArctag(arctag);
		List<ArticleModel> list=this.articleDao.getArticleBaseModelList(model);
		return list;
	}

}
