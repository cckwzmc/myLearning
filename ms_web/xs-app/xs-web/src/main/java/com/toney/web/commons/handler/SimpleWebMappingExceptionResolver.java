package com.toney.web.commons.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.toney.core.constants.ErrConstants;
import com.toney.core.exception.XsAppBaseException;
import com.toney.web.commons.constants.Constants;
import com.toney.web.commons.utils.JsonPackageWrapper;
import com.toney.web.commons.utils.RequestUtil;

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
public class SimpleWebMappingExceptionResolver extends SimpleMappingExceptionResolver {
	
	
	
    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) {
        ModelAndView model = super.doResolveException(request, response, handler, ex);

        if ((model != null) && (ex != null)) {
            if (RequestUtil.isJsonRequest(request) && XsAppBaseException.class.isAssignableFrom(ex.getClass())) { 
                model.addObject(Constants.JSON_MODEL_DATA, new JsonPackageWrapper(false,
                        ((XsAppBaseException) ex).getMessageWithSupportCode()));
            }
            model.addObject("errorMsg", generateErrorMessage(request, response, ex));
        }

        return model;
    }

    private String generateErrorMessage(HttpServletRequest request, HttpServletResponse response, Exception ex) {
    	if(XsAppBaseException.class.isAssignableFrom(ex.getClass())){
    		return ((XsAppBaseException) ex).getMessageWithSupportCode();
        } else {
            return ErrConstants.GENERAL_COMM_ERR_MSG + "["+ErrConstants.HttpErrorCode.HTTP_INTERNAL_SERVER_ERROR + "]";
        }
    }
}
