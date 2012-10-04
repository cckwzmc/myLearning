package com.toney.crawler.common.exception;
/** 
 *************************************************************** 
 * <p>
 * @DESCRIPTION : 
 * @AUTHOR : xiu@xiu.com 
 * @DATE :2012-4-18 下午4:09:20
 * </p>
 **************************************************************** 
 */
public class CrawlerCollectionBusinessException extends CrawlerCollectionBaseException {

    private static final long serialVersionUID = -8073709528775688532L;

    public CrawlerCollectionBusinessException(String message, String code) {
        super(message, code);
    }
    
    public CrawlerCollectionBusinessException(String message, String code, Throwable cause) {
        super(message, code, cause);
    } 
}
