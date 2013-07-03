package com.toney.dal.cms.dao;

import com.toney.dal.cms.model.ArchivesArticleModel;

public interface ArchivesArticleDao {
	public ArchivesArticleModel getById(Long id);

	public int insert(ArchivesArticleModel model);

	public int updateById(ArchivesArticleModel model);

	public int deleteById(Long id);
}