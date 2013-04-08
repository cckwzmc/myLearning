package com.toney.publish.utils;

import org.springframework.util.Assert;

/**
 *************************************************************** 
 * <p>
 * 
 * @DESCRIPTION :
 * @AUTHOR : xiu@xiu.com
 * @DATE :2012-4-7 下午9:36:13
 *       </p>
 **************************************************************** 
 */
public class SysUserAuthInfoHolder {
    private static final ThreadLocal<SysUserAuthInfoHolder> threadLocal = new ThreadLocal<SysUserAuthInfoHolder>(); // NOPMD

    public static SysUserAuthInfoHolder getSysUserAuthInfoHolder() {
        return threadLocal.get();
    }

    public static void setSysUserAuthInfoHolder(SysUserAuthInfoHolder sysUser) {
        Assert.notNull(sysUser, "Only non-null UserAuthInfo instances are permitted");
        threadLocal.set(sysUser);
    }

    public static void clear() {
        threadLocal.remove();
    }
}
