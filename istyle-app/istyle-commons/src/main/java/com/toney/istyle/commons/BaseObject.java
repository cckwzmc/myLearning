package com.toney.istyle.commons;

import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class BaseObject implements Serializable {

	@Override
	public abstract String toString();

	@Override
	public abstract int hashCode();

	@Override
	public abstract boolean equals(Object object);

}
