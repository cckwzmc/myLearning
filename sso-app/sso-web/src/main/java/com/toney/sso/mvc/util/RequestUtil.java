package com.toney.sso.mvc.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;


/**
 *************************************************************** 
 * <p>
 * @CLASS :RequestUtil.java
 * @DESCRIPTION : Request工具类
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 21, 2013
 *       </p>
 **************************************************************** 
 */
public final class RequestUtil {
    public static boolean isJsonRequest(HttpServletRequest request) {
        return (StringUtils.equals("json", request.getParameter("format"))
                || StringUtils.equals("jsonp", request.getParameter("format")));
    }
}
