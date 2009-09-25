package com.lyxmq.novel.web.filter;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.lyxmq.novel.configuration.Constants;

/**
 * Servlet Filter implementation class LocaleFilter
 */
public class LocaleFilter extends OncePerRequestFilter {
	private static Log log=LogFactory.getLog(LocaleFilter.class);
	private static final String DEFAULT_REQ_LOCAL_NAME = "locale";


	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		log.debug("locale filter destroy");
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String locale=request.getParameter("locale");
		HttpSession session=request.getSession(false);
		Locale preferredLocale = null;
		if(locale!=null){
			int indexOfUnderscore=locale.indexOf("_");
			if(indexOfUnderscore!=-1){
				String language=locale.substring(0, indexOfUnderscore);
				String country=locale.substring(indexOfUnderscore+1);
				preferredLocale=new Locale(language,country);
			}else{
				preferredLocale=new Locale(locale);
			}
		}
		if(session!=null){
			if(preferredLocale==null)
			{
				preferredLocale=(Locale) session.getAttribute(Constants.PREFERRED_LOCAL_KEY);
			}
			else{
				session.setAttribute(Constants.PREFERRED_LOCAL_KEY, preferredLocale);
				Config.set(session,Constants.PREFERRED_LOCAL_KEY, preferredLocale);
			}
			if (preferredLocale != null && !(request instanceof LocaleRequestWrapper)) {
                request = new LocaleRequestWrapper(request, preferredLocale);
                LocaleContextHolder.setLocale(preferredLocale);
            }
		}
		filterChain.doFilter(request, response);
		LocaleContextHolder.setLocale(null);
	}

}
