package com.vaadin.app.model;

import java.io.Serializable;

public class NotificationType implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4666057631904790960L;
	private long id;
	private String label;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	
}
