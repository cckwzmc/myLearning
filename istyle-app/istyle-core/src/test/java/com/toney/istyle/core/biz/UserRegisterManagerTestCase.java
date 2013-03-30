package com.toney.istyle.core.biz;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.toney.istyle.core.BaseManagerTestCase;
import com.toney.istyle.core.constants.Constants;
import com.toney.istyle.core.exception.ManagerException;

public class UserRegisterManagerTestCase extends BaseManagerTestCase {

	@Autowired
	UserRegisterManager userRegisterManager;

	@Test
	public void testRegisterUser() {
		try {
			this.userRegisterManager.registerUser("lyxmq_admin", "123456","系统管理员", Constants.REG_TYPE_1);
		} catch (ManagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
