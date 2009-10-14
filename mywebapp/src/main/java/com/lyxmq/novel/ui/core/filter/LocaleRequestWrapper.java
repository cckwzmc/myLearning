package com.lyxmq.novel.ui.core.filter;

import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LocaleRequestWrapper extends HttpServletRequestWrapper {
	private static transient final Log log=LogFactory.getLog(LocaleRequestWrapper.class);
	private final Locale preferredLocale;
	public LocaleRequestWrapper(final HttpServletRequest request,final Locale userLocale) {
		super(request);
		preferredLocale=userLocale;
		if(preferredLocale==null){
			log.error("preferred locale is null ,it is an exception value!");
		}
	}
	/* (non-Javadoc)
	 * @see javax.servlet.ServletRequestWrapper#getLocale()
	 */
	@Override
	public Locale getLocale() {
		if(preferredLocale!=null){
			return preferredLocale;
		}
		return super.getLocale();
	}
	/* (non-Javadoc)
	 * @see javax.servlet.ServletRequestWrapper#getLocales()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Enumeration<Locale> getLocales() {
		if(preferredLocale!=null){
			List<Locale> list=Collections.list(super.getLocales());
			if(list.contains(preferredLocale)){
				list.remove(preferredLocale);
			}
			list.add(0,preferredLocale);
			return Collections.enumeration(list);
		}
		return super.getLocales();
	}
}
