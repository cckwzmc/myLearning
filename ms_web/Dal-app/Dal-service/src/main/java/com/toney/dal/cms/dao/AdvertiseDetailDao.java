package com.toney.dal.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.toney.dal.bean.AdDetailSearchBean;
import com.toney.dal.cms.model.AdvertiseDetailModel;

/**
 * @ClassName: AdvertiseDetailDao
 * @Description: 广告详情
 * @author toney.li
 * @date 2013-7-3 上午10:43:43
 * 
 */
public interface AdvertiseDetailDao {

	int deleteById(Integer id);

	/**
	 * 批量删除
	 * 
	 * @param ids
	 * @return
	 */
	int deleteByIds(List<Integer> ids);

	int insert(AdvertiseDetailModel model);

	List<AdvertiseDetailModel> getBySearch(@Param("search") AdDetailSearchBean search, @Param("startRecord") Integer startRecord,
			@Param("endRecord") Integer endRecord);

	AdvertiseDetailModel getById(Integer id);

	int updateById(AdvertiseDetailModel model);
}