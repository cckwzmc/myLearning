package com.lyxmq.newapp.model;

import java.io.Serializable;
import java.util.Comparator;

public class LabelValue implements Comparable,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6188569262064759772L;


    /**
     * Comparator that can be used for a case insensitive sort of
     * <code>LabelValue</code> objects.
     */
	public static final Comparator CAS_INSENSITIVE_ORDER=new Comparator(){
		public int compare(Object o1,Object o2){
			String label1=((LabelValue)o1).getLabel();
			String label2=((LabelValue)o2).getLabel();
			return label1.compareToIgnoreCase(label2);
		}
	};
	/**
	 * Default constructor
	 */
	public LabelValue(){
		super();
	}
	/**
     * Construct an instance with the supplied property values.
     *
     * @param label The label to be displayed to the user.
     * @param value The value to be returned to the server.
     */
	public LabelValue(final String label,final String value){
		this.label=label;
		this.value=value;
	}
	 // ------------------------------------------------------------- Properties


    /**
     * The property which supplies the option label visible to the end user.
     */
	
	private String label;
	
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	 /**
     * The property which supplies the value returned to the server.
     */
	private String value;
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	 // --------------------------------------------------------- Public Methods

    /**
     * Compare LabelValueBeans based on the label, because that's the human
     * viewable part of the object.
     *
     * @see Comparable
     * @param o LabelValue object to compare to
     * @return 0 if labels match for compared objects
     */
	public int compareTo(Object o) {
		String otherLabel=((LabelValue)o).getLabel();
		return this.getLabel().compareTo(otherLabel);
	}
	@Override
	public String toString() {
		return "LabelValue [label=" + label + ", value=" + value + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LabelValue other = (LabelValue) obj;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

}
