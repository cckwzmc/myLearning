package com.toney.web.commons.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

/** 
 *************************************************************** 
 * <p>
 * @DESCRIPTION : 
 * @AUTHOR : xiu@xiu.com 
 * @DATE :2012-5-11 上午5:59:23
 * </p>
 **************************************************************** 
 */
public final class RequestUtil {
    public static boolean isJsonRequest(HttpServletRequest request) {
        return (StringUtils.equals("json", request.getParameter("format"))
                || StringUtils.equals("jsonp", request.getParameter("format")));
    }
}
