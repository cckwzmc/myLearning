package com.toney.dal.cms.dao;

import com.toney.dal.cms.model.ArchivesStatModel;

public interface ArchivesStatDao {
	int deleteById(Long aId);

	int insert(ArchivesStatModel record);

	ArchivesStatModel getById(Long id);

	int updateById(ArchivesStatModel model);
}