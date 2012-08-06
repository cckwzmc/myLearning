package com.toney.core.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.toney.core.utils.MD5Util;

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
