package com.toney.dal.base;

import java.io.Serializable;

/**
 * Base class for Model objects. Child objects should implement toString().
 * 
 * @author <a href="mailto:david.lin@xiu.com">David Lin</a>
 */

@SuppressWarnings("serial")
public abstract class BaseObject implements Serializable {

	/**
	 * Returns a multi-line String with key=value pairs.
	 * 
	 * @return a String representation of this class.
	 */
	@Override
	public abstract String toString();

	@Override
	public abstract int hashCode();

	@Override
	public abstract boolean equals(Object object);
}
