package com.toney.publish.exception.resolver;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import com.toney.publish.utils.JsonPackageWrapper;

/**
 * 
 *************************************************************** 
 * <p>
 * 
 * @CLASS :com.xiu.portal.web.exception.resolver.HandlerPortalExceptionResolver
 * @DESCRIPTION :
 * @AUTHOR :xiu@xiu.com
 * @TODO: 1. 加入对BaseRuntimeException的异常处理 2. 让Spring异常 (NoSuchRequestHandlingMethodException等)在AJAX请求时按照自定义的标准JSON包格式做输出
 *        3. 对非BaseRuntimeException的异常做标准化编码
 **************************************************************** 
 */
public class HandlerPortalExceptionResolver extends AbstractHandlerExceptionResolver {
    /**
     * Log category to use when no mapped handler is found for a request.
     * 
     * @see #pageNotFoundLogger
     */
    public static final String PAGE_NOT_FOUND_LOG_CATEGORY = "org.springframework.web.servlet.PageNotFound";

    /**
     * Additional logger to use when no mapped handler is found for a request.
     * 
     * @see #PAGE_NOT_FOUND_LOG_CATEGORY
     */
    protected static final Log pageNotFoundLogger = LogFactory.getLog(PAGE_NOT_FOUND_LOG_CATEGORY); // NOPMD

    /**
     * Sets the {@linkplain #setOrder(int) order} to {@link #LOWEST_PRECEDENCE}.
     */
    public HandlerPortalExceptionResolver() {
        setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) {

        try {
            if (ex instanceof NoSuchRequestHandlingMethodException) {
                return handleNoSuchRequestHandlingMethod((NoSuchRequestHandlingMethodException) ex, request, response,
                        handler);
            } else if (ex instanceof HttpRequestMethodNotSupportedException) {
                return handleHttpRequestMethodNotSupported((HttpRequestMethodNotSupportedException) ex, request,
                        response, handler);
            } else if (ex instanceof HttpMediaTypeNotSupportedException) {
                return handleHttpMediaTypeNotSupported((HttpMediaTypeNotSupportedException) ex, request, response,
                        handler);
            } else if (ex instanceof HttpMediaTypeNotAcceptableException) {
                return handleHttpMediaTypeNotAcceptable((HttpMediaTypeNotAcceptableException) ex, request, response,
                        handler);
            } else if (ex instanceof MissingServletRequestParameterException) {
                return handleMissingServletRequestParameter((MissingServletRequestParameterException) ex, request,
                        response, handler);
            } else if (ex instanceof ServletRequestBindingException) {
                return handleServletRequestBindingException((ServletRequestBindingException) ex, request, response,
                        handler);
            } else if (ex instanceof ConversionNotSupportedException) {
                return handleConversionNotSupported((ConversionNotSupportedException) ex, request, response, handler);
            } else if (ex instanceof TypeMismatchException) {
                return handleTypeMismatch((TypeMismatchException) ex, request, response, handler);
            } else if (ex instanceof HttpMessageNotReadableException) {
                return handleHttpMessageNotReadable((HttpMessageNotReadableException) ex, request, response, handler);
            } else if (ex instanceof HttpMessageNotWritableException) {
                return handleHttpMessageNotWritable((HttpMessageNotWritableException) ex, request, response, handler);
            } else if (ex instanceof MethodArgumentNotValidException) {
                return handleMethodArgumentNotValidException((MethodArgumentNotValidException) ex, request, response,
                        handler);
            } else if (ex instanceof MissingServletRequestPartException) {
                return handleMissingServletRequestPartException((MissingServletRequestPartException) ex, request,
                        response, handler);
//            } else if (ex instanceof BaseRuntimeException) {
//                return handleBaseRuntimeException((BaseRuntimeException) ex, request, response, handler);
            } else {
                return handleDefaultException(ex, request, response, handler);
            }
        } catch (Exception handlerException) {
            logger.warn("Handling of [" + ex.getClass().getName() + "] resulted in Exception", handlerException);
        }
        return null;
    }

 /*   protected ModelAndView handleBaseRuntimeException(BaseRuntimeException ex, HttpServletRequest request,
            HttpServletResponse response, Object handler) throws IOException {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

        ModelAndView mav = new ModelAndView();

        JsonPackageWrapper jpw = new JsonPackageWrapper(JsonPackageWrapper.S_ERR);
        // TODO: 加入自定制错误消息
        jpw.setErrorCode(ex.getErrorCode());
        jpw.setSmsg(ex.getMessage());

        mav.addObject("exception", ex);
        mav.addObject("data", jpw);
        mav.setViewName("error/exception");

        return mav;
    }
*/
    protected ModelAndView handleDefaultException(Exception ex, HttpServletRequest request,
            HttpServletResponse response, Object handler) throws IOException {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

        ModelAndView mav = new ModelAndView();

        JsonPackageWrapper jpw = new JsonPackageWrapper();
        // TODO: 加入自定制错误消息
        jpw.setSmsg(ex.getMessage());
        jpw.setSuccess(false);
        mav.addObject("exception", ex);
        mav.addObject("data", jpw);
        mav.setViewName("error/exception");

        return mav;
    }

    /**
     * Handle the case where no request handler method was found.
     * <p>
     * The default implementation logs a warning, sends an HTTP 404 error, and returns an empty {@code ModelAndView}.
     * Alternatively, a fallback view could be chosen, or the NoSuchRequestHandlingMethodException could be rethrown
     * as-is.
     * 
     * @param ex
     *            the NoSuchRequestHandlingMethodException to be handled
     * @param request
     *            current HTTP request
     * @param response
     *            current HTTP response
     * @param handler
     *            the executed handler, or <code>null</code> if none chosen at the time of the exception (for example,
     *            if multipart resolution failed)
     * @return an empty ModelAndView indicating the exception was handled
     * @throws IOException
     *             potentially thrown from response.sendError()
     */
    protected ModelAndView handleNoSuchRequestHandlingMethod(NoSuchRequestHandlingMethodException ex,
            HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        pageNotFoundLogger.warn(ex.getMessage());
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
        return new ModelAndView();
    }

    /**
     * Handle the case where no request handler method was found for the particular HTTP request method.
     * <p>
     * The default implementation logs a warning, sends an HTTP 405 error, sets the "Allow" header, and returns an empty
     * {@code ModelAndView}. Alternatively, a fallback view could be chosen, or the
     * HttpRequestMethodNotSupportedException could be rethrown as-is.
     * 
     * @param ex
     *            the HttpRequestMethodNotSupportedException to be handled
     * @param request
     *            current HTTP request
     * @param response
     *            current HTTP response
     * @param handler
     *            the executed handler, or <code>null</code> if none chosen at the time of the exception (for example,
     *            if multipart resolution failed)
     * @return an empty ModelAndView indicating the exception was handled
     * @throws IOException
     *             potentially thrown from response.sendError()
     */
    protected ModelAndView handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
            HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        pageNotFoundLogger.warn(ex.getMessage());
        String[] supportedMethods = ex.getSupportedMethods();
        if (supportedMethods != null) {
            response.setHeader("Allow", StringUtils.arrayToDelimitedString(supportedMethods, ", "));
        }

        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);

        ModelAndView mav = new ModelAndView();

        JsonPackageWrapper jpw = new JsonPackageWrapper();
        // TODO: 加入自定制错误消息
        jpw.setSmsg(ex.getMessage());
        jpw.setSuccess(false);
        mav.addObject("exception", ex);
        mav.addObject("data", jpw);
        mav.setViewName("error/exception");

        return mav;
    }

    /**
     * Handle the case where no {@linkplain org.springframework.http.converter.HttpMessageConverter message converters}
     * were found for the PUT or POSTed content.
     * <p>
     * The default implementation sends an HTTP 415 error, sets the "Accept" header, and returns an empty
     * {@code ModelAndView}. Alternatively, a fallback view could be chosen, or the HttpMediaTypeNotSupportedException
     * could be rethrown as-is.
     * 
     * @param ex
     *            the HttpMediaTypeNotSupportedException to be handled
     * @param request
     *            current HTTP request
     * @param response
     *            current HTTP response
     * @param handler
     *            the executed handler
     * @return an empty ModelAndView indicating the exception was handled
     * @throws IOException
     *             potentially thrown from response.sendError()
     */
    protected ModelAndView handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
            HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        response.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
        List<MediaType> mediaTypes = ex.getSupportedMediaTypes();
        if (!CollectionUtils.isEmpty(mediaTypes)) {
            response.setHeader("Accept", MediaType.toString(mediaTypes));
        }
        return new ModelAndView();
    }

    /**
     * Handle the case where no {@linkplain org.springframework.http.converter.HttpMessageConverter message converters}
     * were found that were acceptable for the client (expressed via the {@code Accept} header.
     * <p>
     * The default implementation sends an HTTP 406 error and returns an empty {@code ModelAndView}. Alternatively, a
     * fallback view could be chosen, or the HttpMediaTypeNotAcceptableException could be rethrown as-is.
     * 
     * @param ex
     *            the HttpMediaTypeNotAcceptableException to be handled
     * @param request
     *            current HTTP request
     * @param response
     *            current HTTP response
     * @param handler
     *            the executed handler
     * @return an empty ModelAndView indicating the exception was handled
     * @throws IOException
     *             potentially thrown from response.sendError()
     */
    protected ModelAndView handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
            HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
        return new ModelAndView();
    }

    /**
     * Handle the case when a required parameter is missing.
     * <p>
     * The default implementation sends an HTTP 400 error, and returns an empty {@code ModelAndView}. Alternatively, a
     * fallback view could be chosen, or the MissingServletRequestParameterException could be rethrown as-is.
     * 
     * @param ex
     *            the MissingServletRequestParameterException to be handled
     * @param request
     *            current HTTP request
     * @param response
     *            current HTTP response
     * @param handler
     *            the executed handler
     * @return an empty ModelAndView indicating the exception was handled
     * @throws IOException
     *             potentially thrown from response.sendError()
     */
    protected ModelAndView handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
            HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        return new ModelAndView();
    }

    /**
     * Handle the case when an unrecoverable binding exception occurs - e.g. required header, required cookie.
     * <p>
     * The default implementation sends an HTTP 400 error, and returns an empty {@code ModelAndView}. Alternatively, a
     * fallback view could be chosen, or the exception could be rethrown as-is.
     * 
     * @param ex
     *            the exception to be handled
     * @param request
     *            current HTTP request
     * @param response
     *            current HTTP response
     * @param handler
     *            the executed handler
     * @return an empty ModelAndView indicating the exception was handled
     * @throws IOException
     *             potentially thrown from response.sendError()
     */
    protected ModelAndView handleServletRequestBindingException(ServletRequestBindingException ex,
            HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        return new ModelAndView();
    }

    /**
     * Handle the case when a {@link org.springframework.web.bind.WebDataBinder} conversion cannot occur.
     * <p>
     * The default implementation sends an HTTP 500 error, and returns an empty {@code ModelAndView}. Alternatively, a
     * fallback view could be chosen, or the TypeMismatchException could be rethrown as-is.
     * 
     * @param ex
     *            the ConversionNotSupportedException to be handled
     * @param request
     *            current HTTP request
     * @param response
     *            current HTTP response
     * @param handler
     *            the executed handler
     * @return an empty ModelAndView indicating the exception was handled
     * @throws IOException
     *             potentially thrown from response.sendError()
     */
    protected ModelAndView handleConversionNotSupported(ConversionNotSupportedException ex, HttpServletRequest request,
            HttpServletResponse response, Object handler) throws IOException {

        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return new ModelAndView();
    }

    /**
     * Handle the case when a {@link org.springframework.web.bind.WebDataBinder} conversion error occurs.
     * <p>
     * The default implementation sends an HTTP 400 error, and returns an empty {@code ModelAndView}. Alternatively, a
     * fallback view could be chosen, or the TypeMismatchException could be rethrown as-is.
     * 
     * @param ex
     *            the TypeMismatchException to be handled
     * @param request
     *            current HTTP request
     * @param response
     *            current HTTP response
     * @param handler
     *            the executed handler
     * @return an empty ModelAndView indicating the exception was handled
     * @throws IOException
     *             potentially thrown from response.sendError()
     */
    protected ModelAndView handleTypeMismatch(TypeMismatchException ex, HttpServletRequest request,
            HttpServletResponse response, Object handler) throws IOException {

        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        return new ModelAndView();
    }

    /**
     * Handle the case where a {@linkplain org.springframework.http.converter.HttpMessageConverter message converter}
     * cannot read from a HTTP request.
     * <p>
     * The default implementation sends an HTTP 400 error, and returns an empty {@code ModelAndView}. Alternatively, a
     * fallback view could be chosen, or the HttpMediaTypeNotSupportedException could be rethrown as-is.
     * 
     * @param ex
     *            the HttpMessageNotReadableException to be handled
     * @param request
     *            current HTTP request
     * @param response
     *            current HTTP response
     * @param handler
     *            the executed handler
     * @return an empty ModelAndView indicating the exception was handled
     * @throws IOException
     *             potentially thrown from response.sendError()
     */
    protected ModelAndView handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpServletRequest request,
            HttpServletResponse response, Object handler) throws IOException {

        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        return new ModelAndView();
    }

    /**
     * Handle the case where a {@linkplain org.springframework.http.converter.HttpMessageConverter message converter}
     * cannot write to a HTTP request.
     * <p>
     * The default implementation sends an HTTP 500 error, and returns an empty {@code ModelAndView}. Alternatively, a
     * fallback view could be chosen, or the HttpMediaTypeNotSupportedException could be rethrown as-is.
     * 
     * @param ex
     *            the HttpMessageNotWritableException to be handled
     * @param request
     *            current HTTP request
     * @param response
     *            current HTTP response
     * @param handler
     *            the executed handler
     * @return an empty ModelAndView indicating the exception was handled
     * @throws IOException
     *             potentially thrown from response.sendError()
     */
    protected ModelAndView handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpServletRequest request,
            HttpServletResponse response, Object handler) throws IOException {

        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return new ModelAndView();
    }

    /**
     * Handle the case where an argument annotated with {@code @Valid} such as an {@link RequestBody} or
     * {@link RequestPart} argument fails validation. An HTTP 400 error is sent back to the client.
     * 
     * @param request
     *            current HTTP request
     * @param response
     *            current HTTP response
     * @param handler
     *            the executed handler
     * @return an empty ModelAndView indicating the exception was handled
     * @throws IOException
     *             potentially thrown from response.sendError()
     */
    protected ModelAndView handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
            HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        return new ModelAndView();
    }

    /**
     * Handle the case where an @{@link RequestPart}, a {@link MultipartFile}, or a {@code javax.servlet.http.Part}
     * argument is required but missing. An HTTP 400 error is sent back to the client.
     * 
     * @param request
     *            current HTTP request
     * @param response
     *            current HTTP response
     * @param handler
     *            the executed handler
     * @return an empty ModelAndView indicating the exception was handled
     * @throws IOException
     *             potentially thrown from response.sendError()
     */
    protected ModelAndView handleMissingServletRequestPartException(MissingServletRequestPartException ex,
            HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        return new ModelAndView();
    }

}
