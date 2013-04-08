package com.toney.dal.service.mybatis;

import com.toney.dal.dao.ArticleDao;
import com.toney.dal.dao.ArticleExtendDao;
import com.toney.dal.dao.ArticleSysTagDao;
import com.toney.dal.dao.ArticleTinyDao;

/**
 * 
 *************************************************************** 
 * <p>
 * @CLASS :ArticleManagerFactory
 * @DESCRIPTION :文章管理 Dao 工厂
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Dec 28, 2012
 *       </p>
 **************************************************************** 
 */
public interface ArticleManagerFactory {

	public ArticleDao getArticleDao();
	public ArticleExtendDao getArticleExtendDao();
	public ArticleTinyDao getArticleTinyDao();
	public ArticleSysTagDao getArticleSysTagDao();

}
