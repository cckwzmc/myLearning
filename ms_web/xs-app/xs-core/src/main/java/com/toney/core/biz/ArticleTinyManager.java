package com.toney.core.biz;

import java.util.List;

import com.toney.core.model.ArticleTinyModel;

/**
 * @author toney.li
 * 文件简表管理。
 */
public interface ArticleTinyManager {
	public List<ArticleTinyModel> getArticleTiny(String orderBy,Integer row);
}
