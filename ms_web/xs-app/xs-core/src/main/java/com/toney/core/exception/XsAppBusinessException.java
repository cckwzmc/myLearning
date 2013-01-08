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
public class XsAppBusinessException extends XsAppBaseException {

    private static final long serialVersionUID = -8073709528775688532L;

    public XsAppBusinessException(String message, String code) {
        super(message, code);
    }
    
    public XsAppBusinessException(String message, String code, Throwable cause) {
        super(message, code, cause);
    } 
}
