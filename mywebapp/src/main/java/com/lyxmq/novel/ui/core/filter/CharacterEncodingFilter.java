package com.lyxmq.novel.ui.core.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;

/**
 * Servlet Filter implementation class CharacterEncodingFilter
 */
public class CharacterEncodingFilter implements Filter {

	private static Log log = LogFactoryImpl.getLog(CharacterEncodingFilter.class);
	private String encoding = null;


	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	/**
	 * Default constructor.
	 */
	public CharacterEncodingFilter() {
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		log.debug("character encoding destroy");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		log.debug("Processing character endcoding");
		try{
			request.setCharacterEncoding(this.encoding);
			log.debug("Set request character encoding to "+this.encoding);
		}catch(UnsupportedEncodingException e){
			throw new ServletException("set character fail");
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		log.debug("character Encoding init");
		String m_encoding=fConfig.getInitParameter("encoding");
		if(StringUtils.isBlank(m_encoding)){
			m_encoding="UTF-8";
		}
		this.setEncoding(m_encoding);
	}

}
