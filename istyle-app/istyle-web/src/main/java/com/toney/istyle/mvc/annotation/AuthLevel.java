package com.toney.istyle.mvc.annotation;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :AuthLevel.java
 * @DESCRIPTION : 权限级别定义 <br/>
 *              none不做认证<br/>
 *              basic基于Cookie中的信息进行认证<br/>
 *              strict严格认证，必须经过服务器端的认证，如果有SSO还必须SSO的认证
 *              ADMIN必须认证为系统管理员.
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 21, 2013
 *       </p>
 **************************************************************** 
 */
public enum AuthLevel {
	NONE, BASIC, STRICT,ADMIN;
}
