package com.lyxmq.novel.service.user;

import com.lyxmq.novel.hibernate.pojo.User;

public interface NovelUserManager {

	public boolean hasUserPermissions(User authenticatedUser, short requiredPermissions);

	public void save();
}
