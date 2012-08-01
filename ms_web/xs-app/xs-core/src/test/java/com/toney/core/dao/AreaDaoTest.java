package com.toney.core.dao;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.toney.core.model.AreaModel;

/**
 * @author toney.li
 *
 */
public class AreaDaoTest extends BaseDaoTestCase {
	@Autowired
	private AreaDao areaDao;
	
	@Test
	public void testGetAllArea(){
		List<AreaModel> list=areaDao.getAllArea();
		Assert.assertNotNull(list);
	}
}
