package com.toney.publish.biz.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.toney.dal.model.ArticleTinyModel;
import com.toney.publish.biz.ArticleTinyManager;

@Service("articleTinyManager")
public class ArticleTinyManagerImpl implements ArticleTinyManager {

	@Override
	public List<ArticleTinyModel> getArticleTiny(String orderBy, Integer row) {
		return null;
	}

}
