package com.toney.istyle.module;

import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class BaseModule implements Serializable {

	@Override
	public abstract String toString();

	@Override
	public abstract int hashCode();

	@Override
	public abstract boolean equals(Object object);

}
