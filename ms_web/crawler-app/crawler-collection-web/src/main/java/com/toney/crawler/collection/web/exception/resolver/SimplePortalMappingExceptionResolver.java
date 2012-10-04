package com.toney.crawler.collection.web.exception.resolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.toney.crawler.collection.web.contants.Constants;
import com.toney.crawler.collection.web.contants.ErrConstants;
import com.toney.crawler.collection.web.utils.JsonPackageWrapper;
import com.toney.crawler.collection.web.utils.RequestUtil;
import com.toney.crawler.common.exception.CrawlerCollectionBaseException;

/**
 *************************************************************** 
 * <p>
 * 
 * @DESCRIPTION :
 * @AUTHOR : xiu@xiu.com
 * @DATE :2012-5-10 下午12:01:01
 *       </p>
 **************************************************************** 
 */
public class SimplePortalMappingExceptionResolver extends SimpleMappingExceptionResolver {
	
	
	
    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) {
        ModelAndView model = super.doResolveException(request, response, handler, ex);

        if ((model != null) && (ex != null)) {
            if (RequestUtil.isJsonRequest(request) && CrawlerCollectionBaseException.class.isAssignableFrom(ex.getClass())) { 
                model.addObject(Constants.JSON_MODEL_DATA, new JsonPackageWrapper(false,
                        ((CrawlerCollectionBaseException) ex).getMessageWithSupportCode()));
            }
            model.addObject("errorMsg", generateErrorMessage(request, response, ex));
        }

        return model;
    }

    private String generateErrorMessage(HttpServletRequest request, HttpServletResponse response, Exception ex) {
    	if(CrawlerCollectionBaseException.class.isAssignableFrom(ex.getClass())){
    		return ((CrawlerCollectionBaseException) ex).getMessageWithSupportCode();
        } else {
            return ErrConstants.GENERAL_COMM_ERR_MSG + "["+ErrConstants.HttpErrorCode.HTTP_INTERNAL_SERVER_ERROR + "]";
        }
    }
}
