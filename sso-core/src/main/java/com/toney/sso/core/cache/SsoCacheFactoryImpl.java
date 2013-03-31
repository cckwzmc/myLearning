package com.toney.sso.core.cache;

import com.toney.sso.core.exception.SsoCacheException;


public class SsoCacheFactoryImpl implements SsoCacheFactory {

	private LoginCacheManager loginCacheFactory;
	private UserInfoCacheManager userInfoCacheFactory;
	private UserExtInfoCacheManager userExtInfoCacheFactory;
	private UserSecureCacheManager userSecureCacheFactory;
	
	public void setLoginCacheFactory(LoginCacheManager loginCacheFactory) {
		this.loginCacheFactory = loginCacheFactory;
	}

	public void setUserInfoCacheFactory(UserInfoCacheManager userInfoCacheFactory) {
		this.userInfoCacheFactory = userInfoCacheFactory;
	}

	public void setUserExtInfoCacheFactory(
			UserExtInfoCacheManager userExtInfoCacheFactory) {
		this.userExtInfoCacheFactory = userExtInfoCacheFactory;
	}

	public void setUserSecureCacheFactory(
			UserSecureCacheManager userSecureCacheFactory) {
		this.userSecureCacheFactory = userSecureCacheFactory;
	}

	@Override
	public LoginCacheManager getLoginCacheFactory() throws SsoCacheException {
		return this.loginCacheFactory;
	}

	@Override
	public UserInfoCacheManager getUserInfoCacheFactory()
			throws SsoCacheException {
		return this.userInfoCacheFactory;
	}

	@Override
	public UserExtInfoCacheManager getUserExtInfoCacheFactory()
			throws SsoCacheException {
		return this.userExtInfoCacheFactory;
	}

	@Override
	public UserSecureCacheManager getUserSecureCacheFactory()
			throws SsoCacheException {
		return this.userSecureCacheFactory;
	}

}
