package com.toney.core.biz.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.toney.core.biz.ArticleTinyManager;
import com.toney.core.model.ArticleTinyModel;

@Service("articleTinyManager")
public class ArticleTinyManagerImpl implements ArticleTinyManager {

	@Override
	public List<ArticleTinyModel> getArticleTiny(String orderBy, Integer row) {
		return null;
	}

}
