package com.lyxmq.novel.user;

import com.lyxmq.novel.hibernate.pojo.User;

public interface NovelUserManager {

	public boolean hasUserPermissions(User authenticatedUser, short requiredPermissions);

}
