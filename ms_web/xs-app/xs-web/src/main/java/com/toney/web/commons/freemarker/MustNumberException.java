package com.toney.web.commons.freemarker;

import freemarker.template.TemplateModelException;

public class MustNumberException extends TemplateModelException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7923035693261406605L;

	public MustNumberException(String paramName) {
		super("The \"" + paramName + "\" parameter must be a number.");
	}
}
