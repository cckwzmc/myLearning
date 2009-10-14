package com.lyxmq.novel.ui.struts2.action;

import com.lyxmq.novel.service.core.NovelFactory;
import com.lyxmq.novel.service.user.NovelUserManager;
import com.lyxmq.novel.ui.struts2.core.BaseAction;

public class LoginAction extends BaseAction {
	private static final long serialVersionUID = 7949879516746613822L;
	public String loginAdmin(){
		NovelUserManager userManager=NovelFactory.getNovelServiceCore().getNovelUserManager();
		//userManager.
		return SUCCESS;
	}
}
