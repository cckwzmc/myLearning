package com.toney.sso.core.cache;

import com.toney.sso.core.exception.SsoCacheException;

/**
 * <p>
 * 
 * <pre>
 * SSO session 用cache代替。
 * 用户信息都缓存并保持${user.session.cache.timeout}<br/>
 * 缓存中存放两类对象<br/>
 * 1.用户的登录信息
 * 2.用户的基本信息
 * 3.用户扩张信息
 * 4.涉及到安全或敏感信息
 * </pre>
 * 
 * </p>
 * 
 * @author toney.li
 * @Email lyxmq.ljh@gmail.com
 */
public interface SsoCacheFactory {
	/**
	 * 用户登录信息缓存工厂
	 * @return
	 * @throws SsoCacheException
	 */
	public LoginCacheManager getLoginCacheFactory() throws SsoCacheException;
	/**
	 * 用户基本信息缓存工厂
	 * @return
	 * @throws SsoCacheException
	 */
	public UserInfoCacheManager getUserInfoCacheFactory() throws SsoCacheException;
	/**
	 * 用户扩展信息缓存工厂
	 * @return
	 * @throws SsoCacheException
	 */
	public UserExtInfoCacheManager getUserExtInfoCacheFactory() throws SsoCacheException;
	/**
	 * 没想好展示不实现.
	 * @return
	 * @throws SsoCacheException
	 */
	public UserSecureCacheManager getUserSecureCacheFactory() throws SsoCacheException;
}
