package com.toney.crawler.collection.web.interceptor.validator;

import javax.servlet.http.HttpServletRequest;

import com.toney.crawler.collection.web.annotation.AuthLevel;

/**
 *************************************************************** 
 * <p>
 * 
 * @DESCRIPTION :
 * @AUTHOR : xiu@xiu.com
 * @DATE :2012-4-3 上午10:04:53
 *       </p>
 **************************************************************** 
 */
public interface AuthorizationValidator {
    public boolean validate(HttpServletRequest request, AuthLevel level);
}
