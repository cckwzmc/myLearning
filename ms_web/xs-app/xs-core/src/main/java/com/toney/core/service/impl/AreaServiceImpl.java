package com.toney.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toney.core.service.AreaService;
import com.toney.dal.dao.AreaDao;
import com.toney.dal.model.AreaModel;

@Service("areaService")
public class AreaServiceImpl implements AreaService {

	@Autowired
	private AreaDao areaDao;
	
	/* (non-Javadoc)
	 * @see com.toney.core.service.AreaService#getAllAreaModel()
	 * TODO 数据需加入缓存.
	 */
	@Override
	public List<AreaModel> getAllAreaModel() {
		List<AreaModel> areaList=this.areaDao.getAllArea();
		for(AreaModel areaModel:areaList){
			
		}
		return null;
	}

}
