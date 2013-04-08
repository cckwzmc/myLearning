package com.toney.dal.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.toney.dal.model.TemplatePageTypeModel;

/**
 * 
 *************************************************************** 
 * <p>
 * @CLASS :TemplatePageTypeDao
 * @DESCRIPTION :功能描述
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Dec 30, 2012
 *       </p>
 **************************************************************** 
 */
@Repository("templatePageTypeDao")
public interface TemplatePageTypeDao {
	/**
	 * 查出所有的page type code.
	 * @return
	 */
	public List<TemplatePageTypeModel> getAll();
}
