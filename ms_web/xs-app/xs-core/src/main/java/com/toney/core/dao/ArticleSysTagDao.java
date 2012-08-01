package com.toney.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.toney.core.model.ArticleSysTagModel;

/**
 * @author toney.li
 * 文章的系统标签。
 * dedecms.dede_arcatt
 */
@Repository("articleSysTagDao")
public interface ArticleSysTagDao {
	
	public List<ArticleSysTagModel> getAllArticleSysTagModel();
	
}
