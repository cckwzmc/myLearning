package com.toney.dal.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.toney.bean.util.LabelValue;
import com.toney.dal.bean.PublicSearchBean;
import com.toney.dal.model.TplPublicMappingModel;

/**
 * 
 *************************************************************** 
 * <p>
 * 
 * @CLASS :TplPublicMappingDao
 * @DESCRIPTION :功能描述
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Jan 10, 2013
 *       </p>
 **************************************************************** 
 */
@Repository("tplPublicMappingDao")
public interface TplPublicMappingDao {
	
	int deleteByPrimaryKey(Integer id);

	int insert(TplPublicMappingModel record);

	int insertSelective(TplPublicMappingModel record);

	TplPublicMappingModel selectByPrimaryKey(Integer id);
	
	List<TplPublicMappingModel> getAll();

	List<TplPublicMappingModel> selectByAll(@Param("currentRecord")int currentRecord, @Param("pageSize")int pageSize, @Param("orderBy")List<LabelValue> orderBy);
	List<TplPublicMappingModel> selectBySearch(@Param("currentRecord")int currentRecord, @Param("pageSize")int pageSize, @Param("orderBy")List<LabelValue> orderBy,@Param("publicSearch")PublicSearchBean publicSearch);

}