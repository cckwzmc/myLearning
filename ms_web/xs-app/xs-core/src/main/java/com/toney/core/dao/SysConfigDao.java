package com.toney.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.toney.core.model.SysConfigModel;

@Repository("sysConfigDao")
public interface SysConfigDao {
	
	public List<SysConfigModel> getAll();
	
}
