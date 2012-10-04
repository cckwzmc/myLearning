package com.toney.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.toney.core.model.TemplateManagerModel;

/**
 * @author toney.li
 * 模版管理配置.<br/>
 * 对应表dede_templage_manager
 */
@Repository("templateManagerDao")
public interface TemplateManagerDao {
	public List<TemplateManagerModel> getAll();
}
