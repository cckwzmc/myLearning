package org.model.prototype;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrototypeClone implements Cloneable{
	private static Logger logger=LoggerFactory.getLogger(PrototypeClone.class);
	private String[] prototype;

	public String[] getPrototype() {
		return prototype;
	}

	public void setPrototype(String[] prototype) {
		this.prototype = prototype;
	}
	
	public PrototypeClone clone(){
		try {
			return (PrototypeClone) super.clone();
		} catch (CloneNotSupportedException e) {
			logger.error(e.getMessage());
		}
		return null;
	}
}
