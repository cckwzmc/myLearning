package com.toney.publish.tpl.exception;

import freemarker.template.TemplateModelException;

public class MustStringException extends TemplateModelException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4279245023706194885L;

	public MustStringException(String paramName) {
		super("The \"" + paramName + "\" parameter must be a string.");
	}
}