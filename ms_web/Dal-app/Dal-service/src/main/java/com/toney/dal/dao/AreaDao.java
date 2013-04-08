package com.toney.dal.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.toney.dal.model.AreaModel;

/**
 * @author toney.li
 * 地市对应表dedecms.dede_area
 */
@Repository("areaDao")
public interface AreaDao {
	public List<AreaModel> getAllArea();
}
