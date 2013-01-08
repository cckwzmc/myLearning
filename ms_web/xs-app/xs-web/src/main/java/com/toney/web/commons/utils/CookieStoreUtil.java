package com.toney.web.commons.utils;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

import com.toney.core.model.UserAuthInfo;
import com.toney.core.utils.SpringResourceLocator;
import com.toney.dal.model.AdministratorModel;

/**
 *************************************************************** 
 * <p>
 * 
 * @DESCRIPTION :
 * @AUTHOR : xiu@xiu.com
 * @DATE :2012-4-3 下午12:49:13
 *       </p>
 **************************************************************** 
 */
public final class CookieStoreUtil {
	private static final XLogger LOGGER = XLoggerFactory.getXLogger(CookieStoreUtil.class);

	public final static String COOKIE_DOMAIN_ROOT = ".xiu.com";
	public final static String COOKIE_DOMAIN_PORTAL = "portal.xiu.com";

	public final static String COOKIE_NAME_SSOTOKENID = "xiu.login.tokenId";
	public final static String COOKIE_NAME_SSOCUSID = "xiu.login.cusId";
	public final static String COOKIE_NAME_SSOUSERID = "xiu.login.userId"; // userId
	public final static String COOKIE_NAME_SSOCREATETIME = "xiu.login.regDate"; // 注册时间
	// TODO:需要确认
	public final static String COOKIE_NAME_LOGINFLAG = "xiu.login.proxyLoginFlag"; // 代理登录标志
	public final static String COOKIE_NAME_LOGINNAME = "xiu.login.loginName"; // 登录页面要记住且显示的登录名
	public final static String COOKIE_NAME_USERNAME = "xiu.login.userName"; // 其他页面需要显示的登录名（昵称）
	
	public final static String COOKIE_NAME_UNIONFLAG = "xiu.login.unionFlag";// 联合登陆标识：001支付宝，002平安万里通，003
																				// 139邮箱，004芒果，
																				// 005
																				// 新浪微博，
																				// 006
																				// 51返利
	public final static String COOKIE_NAME_UNIONPARTNERID = "xiu.login.partnerId";// 联合登录伙伴id

	public final static String COOKIE_NAME_XUSERID_ENCRYPTION = "xuserId2";// 对userId加密后cookieName
	public final static String COOKIE_NAME_CPS_MEDIA = "fromid";// 对userId加密后cookieName
	public final static String COOKIE_NAME_CPS_TYPE = "cps_type";// cps_type 1
	public final static String COOKIE_NAME_CHANNEL = "xiu.login.channel"; // 用户渠道

	public final static String COOKIE_NAME_LASTLOGINDATE = "xiu.login.lastDate"; // 上次登录时间
	
	public static String getSsoTokenId(HttpServletRequest request) {
		return CookieUtil.getCookieValue(request, COOKIE_DOMAIN_ROOT,
				COOKIE_NAME_SSOTOKENID);
	}

	private static String getSsoTokenId(Map<String, String> map) {
		return map.get(COOKIE_NAME_SSOTOKENID);
	}
 
	public static boolean checkDataIntegrityOfUser(HttpServletRequest request) {

		String originalUserId = CookieUtil.getCookieValue(request, COOKIE_DOMAIN_ROOT, COOKIE_NAME_SSOUSERID);
		if (StringUtils.isBlank(originalUserId)) {// userId为空
			return false;
		}

		// 加密后的userId
		String encryption_userId = CookieUtil.getCookieValue(request, COOKIE_DOMAIN_ROOT,
				COOKIE_NAME_XUSERID_ENCRYPTION);

		if (!StringUtils.isBlank(encryption_userId)) {
			String md5Code = SpringResourceLocator.getConfiguration().getString("encryption.auth_md5code");
//			originalUserId = MD5Util.MD5Encode(originalUserId + md5Code);

			if (encryption_userId.equals(originalUserId)) {
				LOGGER.debug(CookieStoreUtil.class.getName() + " UserIds are equivalent !");
				return true;
			} else {
				LOGGER.debug(CookieStoreUtil.class.getName() + " UserIds are not equivalent!");
			}

		} else {
			LOGGER.debug(CookieStoreUtil.class.getName() + " Encryption UserId mustn't be blank!");

		}
		return false;
	}

	/**
	 * 
	 * @Title: convertCookies2Map
	 * @Description:读取一次cookie，保存到map中，避免反复循环cookie
	 * @param request
	 * @return
	 * @return Map<String,String>
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	private static Map<String, String> convertCookies2Map(HttpServletRequest request) {

		Map<String, String> hashMap = null;
		Cookie[] cookieArr = request.getCookies();
		if (cookieArr != null && cookieArr.length > 0) {
			hashMap = new HashedMap();
			String value = "";
			for (Cookie cookie : cookieArr) {
				value = cookie.getValue();
				try {
					value = java.net.URLDecoder.decode(value, "utf-8");
				} catch (Exception e) {
				}
				hashMap.put(cookie.getName(), value);
			}
		}
		return hashMap;
	}

	public static UserAuthInfo buildUserAuthInfoFromCookie(HttpServletRequest request) {
		UserAuthInfo user = new UserAuthInfo();

		Map<String, String> map = convertCookies2Map(request);
		if (map != null) {
			user.setSsoTokenId(getSsoTokenId(map));
			String ssoCusId = map.get(COOKIE_NAME_SSOCUSID);
			user.setSsoCusId(StringUtils.isNotBlank(ssoCusId) ? Long.valueOf(ssoCusId) : null);
			String ssoUserId = map.get(COOKIE_NAME_SSOUSERID);
			user.setSsoUserId(StringUtils.isNotBlank(ssoUserId) ? Long.valueOf(ssoUserId) : null);
			user.setSsoCreateTime(map.get(COOKIE_NAME_SSOCREATETIME));
			user.setLoginFlag(map.get(COOKIE_NAME_LOGINFLAG));
			user.setLoginName(map.get(COOKIE_NAME_LOGINNAME));
		}
		return user;
	}

	/**
	 * 清理用户登录cookie信息。
	 */
	public static void clearUserCookie() {
		
	}

	public static void storeAdministrator(AdministratorModel administrator) {
		
	}
}
