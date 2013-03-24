package com.toney.istyle.mvc.resolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.toney.istyle.constants.ErrConstants;
import com.toney.istyle.exception.IstyleBaseException;
import com.toney.istyle.mvc.constants.Constants;
import com.toney.istyle.mvc.util.RequestUtil;
import com.toney.istyle.util.JsonPackageWrapper;

/**
 *************************************************************** 
 * <p>
 * @CLASS :SimpleWebMappingExceptionResolver.java
 * @DESCRIPTION : 全局异常控制
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 21, 2013
 *       </p>
 **************************************************************** 
 */
public class SimpleWebMappingExceptionResolver extends SimpleMappingExceptionResolver {
	
	
	
    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) {
        ModelAndView model = super.doResolveException(request, response, handler, ex);

        if ((model != null) && (ex != null)) {
            if (RequestUtil.isJsonRequest(request) && IstyleBaseException.class.isAssignableFrom(ex.getClass())) { 
                model.addObject(Constants.JSON_MODEL_DATA, new JsonPackageWrapper(JsonPackageWrapper.S_ERR,
                        ((IstyleBaseException) ex).getMessageWithSupportCode()));
            }
            model.addObject("errorMsg", generateErrorMessage(request, response, ex));
        }

        return model;
    }

    private String generateErrorMessage(HttpServletRequest request, HttpServletResponse response, Exception ex) {
    	if(IstyleBaseException.class.isAssignableFrom(ex.getClass())){
			return ((IstyleBaseException) ex).getMessageWithSupportCode();
        } else {
            return ErrConstants.GENERAL_COMM_ERR_MSG + "["+ErrConstants.HttpErrorCode.HTTP_INTERNAL_SERVER_ERROR + "]";
        }
    }
}
