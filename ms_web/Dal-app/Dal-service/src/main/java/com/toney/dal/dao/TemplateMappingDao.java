package com.toney.dal.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.toney.dal.model.TemplateMappingModel;

/**
 * 
 *************************************************************** 
 * <p>
 * @CLASS :TemplateMappingDao
 * @DESCRIPTION :功能描述
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Dec 30, 2012
 *       </p>
 **************************************************************** 
 */
@Repository("templateMappingDao")
public interface TemplateMappingDao {

	
	/**
	 * 得到所有数据.
	 * @return
	 */
	public List<TemplateMappingModel> getAll();
}
