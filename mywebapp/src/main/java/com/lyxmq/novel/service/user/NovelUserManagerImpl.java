package com.lyxmq.novel.service.user;

import org.springframework.stereotype.Service;

import com.lyxmq.novel.hibernate.pojo.User;
import com.lyxmq.novel.service.core.NovelServiceBase;

@Service("novelUserManager")
public class NovelUserManagerImpl extends NovelServiceBase implements NovelUserManager{

	@Override
	public boolean hasUserPermissions(User authenticatedUser, short requiredPermissions) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void save() {
	}

}
