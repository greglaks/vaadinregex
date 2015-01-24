package com.vaadin.app.model;

import java.io.Serializable;

public class Alert implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7359595192820486866L;
	
	private String name;
	private String pattern;
	private NotificationType notification;
    private int severity; // 1-10
    
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public NotificationType getNotification() {
		return notification;
	}
	public void setNotification(NotificationType notification) {
		this.notification = notification;
	}
	public int getSeverity() {
		return severity;
	}
	public void setSeverity(int severity) {
		this.severity = severity;
	}
	
	@Override
	public int hashCode() {
		String s = String.valueOf(name.hashCode());
		int value = Integer.valueOf(s);
		return value;
	}
    
    

}
