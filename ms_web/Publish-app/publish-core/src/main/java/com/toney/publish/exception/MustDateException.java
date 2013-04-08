package com.toney.publish.exception;

import freemarker.template.TemplateModelException;

public class MustDateException extends TemplateModelException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1380376845657415133L;

	public MustDateException(String paramName) {
		super("The \"" + paramName + "\" parameter must be a date.");
	}
}
