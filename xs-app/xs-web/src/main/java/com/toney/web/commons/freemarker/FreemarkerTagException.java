package com.toney.web.commons.freemarker;

import freemarker.template.TemplateModelException;

public class FreemarkerTagException extends TemplateModelException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5794310625847572130L;

	public FreemarkerTagException() {
		super();
	}

	public FreemarkerTagException(String message) {
		super(message);
	}

	public FreemarkerTagException(Exception t) {
		super(t);
	}

	public FreemarkerTagException(Exception arg1, String arg0) {
		super(arg0, arg1);
	}
}
