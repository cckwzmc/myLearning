package com.toney.core.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.toney.core.model.ArticleBaseModel;
import com.toney.core.model.ArticleModel;

/**
 * @author toney.li
 * 文章表对应dedecms.dede_archives
 */
@Repository("articleDao")
public interface ArticleDao {
	/**
	 * 页面展示管理使用
	 * @param map
	 * @return
	 */
	public List<ArticleModel> getPageArticleModel(Map<String,Object> map);
	
	/**
	 * 出版使用
	 * @param map
	 * @return
	 */
	public List<ArticleModel> getPublishPageArticleModel(Map<String,Object> map);
	
	
	public ArticleModel getArticleModelById(Long id);

	/**
	 * @param map
	 * 读取
	 */
	public List<ArticleModel> getArticleBaseModelList(ArticleQueryModel model);
	
}
