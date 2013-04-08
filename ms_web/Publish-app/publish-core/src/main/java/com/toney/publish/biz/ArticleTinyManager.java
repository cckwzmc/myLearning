package com.toney.publish.biz;

import java.util.List;

import com.toney.dal.model.ArticleTinyModel;

/**
 * @author toney.li
 * 文件简表管理。
 */
public interface ArticleTinyManager {
	public List<ArticleTinyModel> getArticleTiny(String orderBy,Integer row);
}
