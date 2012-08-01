package com.toney.core.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.toney.core.model.ArticleTinyModel;

/**
 * @author toney.li
 * 对文章的一些独立处理.对应dedecms.dede_arctiny
 */
@Repository("articleTinyDao")
public interface ArticleTinyDao {
	/**
	 * 页面展示管理使用
	 * @param map
	 * @return
	 */
	public List<ArticleTinyModel> getPageArticleTiny(Map<String,Object> map);
	
	/**
	 * 出版使用
	 * @param map
	 * @return
	 */
	public List<ArticleTinyModel> getPublishPageArticleTiny(Map<String,Object> map);
	
	
	public ArticleTinyModel getArticleTinyById(Long id);
}
