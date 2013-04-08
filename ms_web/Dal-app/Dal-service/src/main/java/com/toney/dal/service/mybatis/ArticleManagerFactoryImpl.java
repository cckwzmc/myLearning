package com.toney.dal.service.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toney.dal.dao.ArticleDao;
import com.toney.dal.dao.ArticleExtendDao;
import com.toney.dal.dao.ArticleSysTagDao;
import com.toney.dal.dao.ArticleTinyDao;

/**
 * 
 *************************************************************** 
 * <p>
 * 
 * @CLASS :ArticleManagerFactoryImpl
 * @DESCRIPTION :功能描述
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Dec 28, 2012
 *       </p>
 **************************************************************** 
 */
@Service("articleManagerFactory")
public class ArticleManagerFactoryImpl implements ArticleManagerFactory {
	@Autowired
	private ArticleDao articleDao;
	@Autowired
	private ArticleExtendDao articleExtendDao;
	@Autowired
	private ArticleTinyDao articleTinyDao;
	@Autowired
	private ArticleSysTagDao articleSysTagDao;

	@Override
	public ArticleDao getArticleDao() {
		return articleDao;
	}

	@Override
	public ArticleExtendDao getArticleExtendDao() {
		return this.articleExtendDao;
	}

	@Override
	public ArticleTinyDao getArticleTinyDao() {
		return this.articleTinyDao;
	}

	@Override
	public ArticleSysTagDao getArticleSysTagDao() {
		return this.articleSysTagDao;
	}

}
