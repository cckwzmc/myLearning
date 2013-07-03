package com.toney.dal.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.toney.dal.cms.model.ChannelBaseModel;

public interface ChannelBaseDao {
	/**
	 * 插入新记录
	 * 
	 * @param model
	 * @return
	 */
	public int insert(@Param("model") ChannelBaseModel model);

	/**
	 * @param model
	 * @return
	 */
	public int updateById(@Param("model") ChannelBaseModel model);

	public ChannelBaseModel getById(Integer id);

	public int deleteById(Integer id);
	
	public List<ChannelBaseModel> getAllChannelBase();

}