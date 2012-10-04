package com.toney.core.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toney.core.biz.ArticleManager;
import com.toney.core.dao.ArticleDao;
import com.toney.core.dao.ArticleQueryModel;
import com.toney.core.model.ArticleModel;

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
