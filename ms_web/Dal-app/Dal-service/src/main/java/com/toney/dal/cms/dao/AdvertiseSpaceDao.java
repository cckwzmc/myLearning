package com.toney.dal.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.toney.dal.bean.AdSpaceSearchBean;
import com.toney.dal.cms.model.AdvertiseSpaceModel;

public interface AdvertiseSpaceDao {
	List<AdvertiseSpaceModel> getBySearch(@Param("search") AdSpaceSearchBean search, @Param("startRecord") Integer startRecord,
			@Param("endRecord") Integer endRecord);

	AdvertiseSpaceModel getById(Integer id);
	int deleteById(Integer id);
	
	int insert(AdvertiseSpaceModel record);

	int updateById(AdvertiseSpaceModel model);
}