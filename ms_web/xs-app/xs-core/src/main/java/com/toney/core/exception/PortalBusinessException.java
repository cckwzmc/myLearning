package com.toney.core.exception;
/** 
 *************************************************************** 
 * <p>
 * @DESCRIPTION : 
 * @AUTHOR : xiu@xiu.com 
 * @DATE :2012-4-18 下午4:09:20
 * </p>
 **************************************************************** 
 */
public class PortalBusinessException extends PortalBaseException {

    private static final long serialVersionUID = -8073709528775688532L;

    public PortalBusinessException(String message, String code) {
        super(message, code);
    }
    
    public PortalBusinessException(String message, String code, Throwable cause) {
        super(message, code, cause);
    } 
}
