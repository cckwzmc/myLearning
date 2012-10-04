package com.toney.crawler.common.exception;

import org.apache.commons.lang.StringUtils;

/** 
 *************************************************************** 
 * <p>
 * @DESCRIPTION : 
 * @AUTHOR : xiu@xiu.com 
 * @DATE :2012-4-18 下午3:35:01
 * </p>
 **************************************************************** 
 */
public class CrawlerCollectionBaseException extends RuntimeException {
    private static final long serialVersionUID = -7530520773050834373L;
    
    private String errCode;
    
    public String getErrCode() {
        return errCode;
    }
    
    public CrawlerCollectionBaseException(String message, String code) {
        super(message);
        errCode = code;
    }
    
    public CrawlerCollectionBaseException(String message, String code, Throwable cause) {
        super(message, cause);
        errCode = code;
    } 
    
    public String getMessageWithSupportCode() {
        if (StringUtils.isNotEmpty(getErrCode())) {
            StringBuilder builder = new StringBuilder();
            builder.append(getMessage()).append("[").append(getErrCode()).append("]");
            return builder.toString();
        }
        return getMessage();
    }
}
