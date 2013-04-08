package com.toney.dal.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.toney.digest.util.MD5Util;

public class AdministratorDaoTest extends BaseDaoTestCase {
	@Autowired
	AdministratorDao administratorDao; 

	@Test
	public void testGetAdministratorModel(){
		String enMd5=MD5Util.getMD5LowerCase("admin");
		System.out.println(enMd5);
		this.administratorDao.getAdministratorModel("admin",enMd5);
	}
}
