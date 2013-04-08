package com.toney.publish.tpl.exception;

import freemarker.template.TemplateModelException;

public class MustBooleanException extends TemplateModelException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 868787610830517931L;

	public MustBooleanException(String paramName) {
		super("The \"" + paramName + "\" parameter must be a boolean.");
	}
}
