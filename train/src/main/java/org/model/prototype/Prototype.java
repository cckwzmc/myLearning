package org.model.prototype;

public abstract class Prototype {
	private String pId;
	protected static String sId="prototype";
	public Prototype() {
	}
	public String getPId() {
		return pId;
	}
	public void setPId(String id) {
		pId = id;
	}
	protected abstract Prototype clone();
	
}
