package com.toney.mvc.web.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

import com.toney.core.model.UserAuthInfo;

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
	private static final XLogger LOGGER=XLoggerFactory.getXLogger(CookieStoreUtil.class);
	
    public final static String COOKIE_DOMAIN_ROOT = ".xiu.com";
    public final static String COOKIE_DOMAIN_PORTAL = "portal.xiu.com";

    public final static String COOKIE_NAME_SSOTOKENID = "xiu.login.tokenId";
    public final static String COOKIE_NAME_SSOCUSID = "xiu.login.cusId";
    public final static String COOKIE_NAME_SSOUSERID = "xiu.login.userId"; // userId
    public final static String COOKIE_NAME_SSOCREATETIME = "xiu.login.regDate"; // 注册时间
    // TODO:需要确认
    public final static String COOKIE_NAME_LOGINFLAG = "xiu.login.loginFlag"; // 注册时间

    public final static String COOKIE_NAME_UNIONFLAG = "xiu.login.unionFlag";// 联合登陆标识：001支付宝，002平安万里通，003 139邮箱，004芒果，
                                                                             // 005 新浪微博， 006 51返利
    public final static String COOKIE_NAME_UNIONPARTNERID = "xiu.login.partnerId";// 联合登录伙伴id
    
	public final static String COOKIE_NAME_XUSERID_ENCRYPTION = "xuserId2";//对userId加密后cookieName

    public static String getSsoTokenId(HttpServletRequest request) {
        return CookieUtil.getCookieValue(request, COOKIE_DOMAIN_ROOT, COOKIE_NAME_SSOTOKENID);
    }

    public static boolean checkDataIntegrityOfUser(HttpServletRequest request) {
    	
    	String originalUserId=CookieUtil.getCookieValue(request, COOKIE_DOMAIN_ROOT, COOKIE_NAME_SSOUSERID);
		if(StringUtils.isBlank(originalUserId)){//userId为空
			return false;
		}
		
		// 加密后的userId
		String encryption_userId = CookieUtil.getCookieValue(request,COOKIE_DOMAIN_ROOT, COOKIE_NAME_XUSERID_ENCRYPTION);
		
		if(!StringUtils.isBlank(encryption_userId)){
			String md5Code ="SZXIU2";// SpringResourceLocator.getConfiguration().getString("SSO_USERID_AUTH_MD5CODE");
			originalUserId = org.springframework.util.DigestUtils.md5DigestAsHex((originalUserId + md5Code).getBytes());
			
			if(encryption_userId.equals(originalUserId)){
				LOGGER.debug(CookieStoreUtil.class.getName()+" UserIds are equivalent !");
				return true;
			}else{
				LOGGER.debug(CookieStoreUtil.class.getName()+" UserIds are not equivalent!");
			}
			
		}else{
			LOGGER.debug(CookieStoreUtil.class.getName()+" Encryption UserId mustn't be blank!");
			
		}
        return false;
    }

    public static UserAuthInfo buildUserAuthInfoFromCookie(HttpServletRequest request) {
        UserAuthInfo user = new UserAuthInfo();

        user.setSsoTokenId(getSsoTokenId(request));
        String ssoCusId = CookieUtil.getCookieValue(request, COOKIE_DOMAIN_ROOT, COOKIE_NAME_SSOCUSID);
		user.setSsoCusId(StringUtils.isNotBlank(ssoCusId)?Long.valueOf(ssoCusId):null);
        String ssoUserId = CookieUtil.getCookieValue(request, COOKIE_DOMAIN_ROOT, COOKIE_NAME_SSOUSERID);
		user.setSsoUserId(StringUtils.isNotBlank(ssoUserId)?Long.valueOf(ssoUserId):null);
        user.setSsoCreateTime(CookieUtil.getCookieValue(request, COOKIE_DOMAIN_ROOT, COOKIE_NAME_SSOCREATETIME));

        user.setLoginFlag(CookieUtil.getCookieValue(request, COOKIE_DOMAIN_ROOT, COOKIE_NAME_LOGINFLAG));

        user.setUnionFlag(CookieUtil.getCookieValue(request, COOKIE_DOMAIN_ROOT, COOKIE_NAME_UNIONFLAG));
        user.setUnionPartnerId(CookieUtil.getCookieValue(request, COOKIE_DOMAIN_ROOT, COOKIE_NAME_UNIONPARTNERID));

        return user;
    }

}
