package com.lotteryCommend.security.vote;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * 
 * 
 * @author zhengbin
 * 
 * @version $Id: RoleInsVoter.java 3209 2009-09-18 $
 */
public class LotteryVoter implements AccessDecisionVoter {
	// ~ Instance fields
	// ===============================================================================================

	private String rolePrefix = "ROLE_";

	private AuthorityManager authorityManager;

	// ~ Methods
	// ========================================================================================================

	public String getRolePrefix() {
		return rolePrefix;
	}

	/**
	 * Allows the default role prefix of <code>ROLE_</code> to be overridden. May be set to an empty value, although this is usually not desirable.
	 * 
	 * @param rolePrefix
	 *            the new prefix
	 */
	public void setRolePrefix(String rolePrefix) {
		this.rolePrefix = rolePrefix;
	}

	public boolean supports(ConfigAttribute attribute) {
		if ((attribute.getAttribute() != null) && attribute.getAttribute().startsWith(getRolePrefix())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This implementation supports any type of class, because it does not query the presented secure object.
	 * 
	 * @param clazz
	 *            the secure object
	 * 
	 * @return always <code>true</code>
	 */
	public boolean supports(Class clazz) {
		return true;
	}

	GrantedAuthority[] extractAuthorities(Authentication authentication) {
		return authentication.getAuthorities();
	}

	public void setAuthorityManager(AuthorityManager authorityManager) {
		this.authorityManager = authorityManager;
	}

	public AuthorityManager getAuthorityManager() {
		return authorityManager;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
		int result = ACCESS_ABSTAIN;
		Iterator iter = config.getConfigAttributes().iterator();
		GrantedAuthority[] authorities = null;

		// 加入如下逻辑：
		// 如果当前访问的域名为某InsId的站点，则获得当前人的所在当前单位的角色列表，然后进行比对,
		// 不再使用默认authentication的角色列表进行判断.
		// 如果当前域名无对应单位的，则按默认处理。

		// FIXME 从本地缓存读取个人所在单位的授权信息，如果没有从远程读取，然后存入缓存

		authorities = authorityManager.userInsGrantedAuthorities(SecurityUtils.getCurrentUserId());

		if (authorities == null || authorities.length == 0) {
			authorities = extractAuthorities(authentication);
		}

		while (iter.hasNext()) {
			ConfigAttribute attribute = (ConfigAttribute) iter.next();

			if (this.supports(attribute)) {
				result = ACCESS_DENIED;

				// Attempt to find a matching granted authority
				for (int i = 0; i < authorities.length; i++) {
					if (attribute.getAttribute().equals(authorities[i].getAuthority())) {
						return ACCESS_GRANTED;
					}
				}
			}
		}
		return result;
	}
}
