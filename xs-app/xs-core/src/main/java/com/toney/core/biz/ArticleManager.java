package com.toney.core.biz;

import java.util.List;

import com.toney.dal.model.ArticleModel;

/**
 * @author toney.li
 * 文章管理
 */
public interface ArticleManager {

	/**
	 * 根据条件查询文件
	 * @param orderby 排序条件
	 * @param row	行数
	 * @param channelIds 有效的频道Id.
	 * @return
	 */
	public List<ArticleModel> getArticleList(String orderby, Integer row, String channelIds,String arctag);

}
