package com.toney.core.exception;
/** 
 *************************************************************** 
 * <p>
 * @DESCRIPTION : 
 * @AUTHOR : xiu@xiu.com 
 * @DATE :2012-4-18 下午3:41:33
 * </p>
 **************************************************************** 
 */
public class PortalEIRuntimeException extends PortalBaseException {
    
    private static final long serialVersionUID = -7900639249596835684L;

    private String extErrCode;
    private String extMessage;
    
    public PortalEIRuntimeException(String message, String code) {
        super(message, code);
    }
    
    public PortalEIRuntimeException(String message, String code, Throwable cause) {
        super(message, code, cause);
    }    
    
    public PortalEIRuntimeException(String message, String code, String extCode, String extMessage) {
        super(message, code);
        this.extErrCode = extCode;
        this.extMessage = extMessage;
    }
    
    public String getCode(){
        return (extErrCode==null)? super.getErrCode() : super.getErrCode() + "#" + extErrCode;
    }
    
    public String getExtMessage() {
        return this.extMessage;
    }
    
    public String getExtErrCode() {
        return this.extErrCode;
    }
}
