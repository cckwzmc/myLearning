package org.model.prototype;

public class PrototypeImplA extends Prototype {

	@Override
	protected PrototypeImplA clone() {
		return (PrototypeImplA)this.clone();
	}

}
