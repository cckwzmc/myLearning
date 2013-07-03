package com.toney.dal.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.toney.dal.bean.ArchiveSearchBean;
import com.toney.dal.cms.model.ArchivesBaseModel;

public interface ArchivesBaseDao {
	int insert(ArchivesBaseModel model);
	
    int deleteById(Long id);

    int deleteBatchIds(List<Long> ids);

    List<ArchivesBaseModel> getByArchiveSearch(@Param("search")ArchiveSearchBean search);

    ArchivesBaseModel getById(Long id);

    int updateByPrimaryKeySelective(ArchivesBaseModel record);

}