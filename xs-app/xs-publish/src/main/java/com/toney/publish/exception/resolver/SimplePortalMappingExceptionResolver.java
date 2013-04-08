package com.toney.publish.exception.resolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.toney.core.constants.ErrConstants;
import com.toney.core.exception.PortalBaseException;
import com.toney.publish.contants.Constants;
import com.toney.publish.utils.JsonPackageWrapper;
import com.toney.publish.utils.RequestUtil;

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
            if (RequestUtil.isJsonRequest(request) && PortalBaseException.class.isAssignableFrom(ex.getClass())) { 
                model.addObject(Constants.JSON_MODEL_DATA, new JsonPackageWrapper(false,
                        ((PortalBaseException) ex).getMessageWithSupportCode()));
            }
            model.addObject("errorMsg", generateErrorMessage(request, response, ex));
        }

        return model;
    }

    private String generateErrorMessage(HttpServletRequest request, HttpServletResponse response, Exception ex) {
    	if(PortalBaseException.class.isAssignableFrom(ex.getClass())){
    		return ((PortalBaseException) ex).getMessageWithSupportCode();
        } else {
            return ErrConstants.GENERAL_COMM_ERR_MSG + "["+ErrConstants.HttpErrorCode.HTTP_INTERNAL_SERVER_ERROR + "]";
        }
    }
}
