package com.toney.mvc.web.interceptor.validator;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.stereotype.Component;

import com.toney.core.model.UserAuthInfo;
import com.toney.mvc.web.contants.Constants;
import com.toney.mvc.web.utils.CookieStoreUtil;
import com.toney.mvc.web.utils.IPUtil;

/**
 *************************************************************** 
 * <p>
 * 
 * @DESCRIPTION :
 * @AUTHOR : xiu@xiu.com
 * @DATE :2012-4-3 下午12:28:02
 *       </p>
 **************************************************************** 
 */
@Component("cookieValidator")
public class CookieValidator implements AuthorizationValidator {

	private static final XLogger LOGGER=XLoggerFactory.getXLogger(CookieValidator.class);
//	@Autowired
//    private SsoManager ssoManager;

	@Override
    public boolean validate(HttpServletRequest request) {

        // 1. 构造UserAuthInfo对象，以便后续的业务使用
        UserAuthInfo user = CookieStoreUtil.buildUserAuthInfoFromCookie(request);
        if (user != null) {
            user.setClientIpAddress(IPUtil.getRequestIP(request));
        }

        // 2. 检查是否有tokenId
        if ((user == null) || (StringUtils.isBlank(user.getSsoTokenId()))||(user.getSsoUserId()==null)) {
            return false;
        }

        // 3. 检查Cookies上记录数据的安全性
        if (!CookieStoreUtil.checkDataIntegrityOfUser(request)) {
            return false;
        }

//        // 4. 到SSO服务器检测tokenId是否有效
//        if (!checkSsoStatus(user.getSsoTokenId(),user.getClientIpAddress(),user.getSsoUserId())) {
//            return false;
//        }

        // 5. 构造UserAuthInfo对象，以便后续的业务使用
        request.setAttribute(Constants.USER_AUTH_INFO_ATTR, user);
        return true;
    }
//
//    /**
//      * @Title: checkSsoStatus
//      * @Description: SSO权限认证
//      * @param @param ssoTokenId
//      * @param @param ip
//      * @param @param userId
//      * @param @return
//      * @return boolean
//      * @throws
//      */
//    private boolean checkSsoStatus(String ssoTokenId,String ip,Long userId) {
//    	try{
//	    	 UserDO userDO = this.ssoManager.checkOnlineState(ssoTokenId,
//	                         String.valueOf(com.toney.core.constants.Constants.PORTAL_CHANNELID),ip);
//	         if (userDO!=null && userDO.getIsSuccess()&&userDO.getUserId() != null&&
//	        		 StringUtils.equals(userDO.getUserId(),String.valueOf(userId))) {
//	        	 return true;
//	         }
//    	}catch(Exception e){
//    		LOGGER.error("用户认证失败,tokenId={},ip={},userId={},exception={}", new Object[]{ssoTokenId,ip,userId,e});
//    		LOGGER.error("用户认证失败", e);
//    	}
//    	return false;
//    }
}
