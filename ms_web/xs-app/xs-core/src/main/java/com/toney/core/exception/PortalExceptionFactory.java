package com.toney.core.exception;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 *************************************************************** 
 * <p>
 * @DESCRIPTION : 
 * @AUTHOR : xiu@xiu.com 
 * @DATE :2012-4-18 下午3:23:27
 * </p>
 **************************************************************** 
 */
public final class PortalExceptionFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(PortalExceptionFactory.class);
    
    private static Properties props = new Properties();
    
    static {
        InputStream is = PortalExceptionFactory.class.getResourceAsStream("/errorMessages_zh.properties");
        if(is != null){ 
            try {
                props.load(is);
            } catch (IOException e) {
                LOGGER.error("load error messages faild");
            }
        }
    }
    
    public static PortalEIRuntimeException buildPortalEIRuntimeException(String code, Throwable cause, String... params) {
        String message = formatMessage(code, params);
        if (message == null) {
            //TODO: 在constants中统一定义
            message = "未知错误";
        }
        
        return new PortalEIRuntimeException(message, code, cause);
    }
   
    public static PortalEIRuntimeException buildPortalEIRuntimeException(String code, String extCode, String extMsg, String... params) {
        String message = formatMessage(code, params);
        if (message == null) {
            message = extMsg;
        }
        return new PortalEIRuntimeException(message, code, extCode, extMsg);
    }
    
    public static PortalEIBusinessException buildPortalEIBusinessException(String code, String extCode, String extMsg, String... params) {
        String message = formatMessage(code, params);
        if (message == null) {
            message = extMsg;
        }
        return new PortalEIBusinessException(message, code, extCode, extMsg);   
    }
    
    public static PortalBusinessException buildPortalBusinessException(String code, String... params) {
        String message = formatMessage(code, params);
        if (message == null) {
            //TODO: 在constants中统一定义
            message = "未知错误";
        }
        return new PortalBusinessException(message, code);   
    }
    
    
    private static String formatMessage(String code, String... params) {
        String msg = props.getProperty(code);
        String message = null;
        
        if (StringUtils.isNotEmpty(msg)) {
            int count = StringUtils.countMatches(msg, "%s");
            Object[] values = new Object[count];
            for(int i=0; i<count; i++){
                if(i<params.length){
                    values[i] = params[i];
                }else{
                    values[i] = "";
                }
            }
            message = String.format(msg, values);
        } 
        
        return message;
    }
    
}
