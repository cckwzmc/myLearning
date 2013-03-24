package com.toney.istyle.core.cache.user;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.toney.istyle.core.BaseManagerTestCase;
import com.toney.istyle.core.biz.UserLoginManager;
import com.toney.istyle.core.biz.UserRegisterManager;
import com.toney.istyle.core.exception.ServiceException;
import com.toney.istyle.core.user.UserQueryService;

public class UserManagerTestCase extends BaseManagerTestCase {
	private static final String USER_CACHE = "userCache";
	@Autowired
	UserRegisterManager userRegisterManager;
	@Autowired
	UserLoginManager userLoginManager;
	
	@Autowired
	UserQueryService userQueryService;

	@Test
	public void testGetUserByUserName() {
		try {
			for(int i=0;i<1000;i++){
				this.userQueryService.getUserByUserName("lyxmq@126.com");
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.cacheManagerInfo(USER_CACHE);
		
	}
}
