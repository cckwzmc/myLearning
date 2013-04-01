package com.toney.sso.core.biz;

import com.toney.sso.core.bo.UserLoginedBO;

public interface UserLoginManager {

	UserLoginedBO checkLoginedState(String tokenId, Long userId, String userName);

}
