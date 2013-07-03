package com.toney.dal.cms.dao;

import java.util.List;

import com.toney.dal.cms.model.AppConfigModel;

public interface AppconfigsDao {
	public List<AppConfigModel> getAllAppconfigs();
	public int insert(AppConfigModel model);
	public int updateById(AppConfigModel model);
}