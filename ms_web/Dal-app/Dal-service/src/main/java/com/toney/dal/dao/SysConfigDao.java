package com.toney.dal.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.toney.dal.model.SysConfigModel;

@Repository("sysConfigDao")
public interface SysConfigDao {
	
	public List<SysConfigModel> getAll();
	
}
