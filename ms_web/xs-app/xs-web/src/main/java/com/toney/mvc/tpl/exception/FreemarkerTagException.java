package com.toney.mvc.tpl.exception;

import freemarker.template.TemplateModelException;

public class FreemarkerTagException extends TemplateModelException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5794310625847572130L;

	public FreemarkerTagException(String message){super(message);}
}
