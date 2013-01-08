package com.toney.core.exception;
/** 
 *************************************************************** 
 * <p>
 * @DESCRIPTION : 
 * @AUTHOR : xiu@xiu.com 
 * @DATE :2012-4-18 下午4:12:49
 * </p>
 **************************************************************** 
 */
public class XsAppEIBusinessException extends XsAppBusinessException {

    private static final long serialVersionUID = 3665325278700219286L;
    private String extErrCode;
    private String extMessage;
    
    public XsAppEIBusinessException(String message, String code, String extCode, String extMessage) {
        super(message, code);
        this.extErrCode = extCode;
        this.extMessage = extMessage;
    }
    
    public String getExtMessage() {
        return this.extMessage;
    }
    
    public String getExtErrCode() {
        return this.extErrCode;
    }
}
