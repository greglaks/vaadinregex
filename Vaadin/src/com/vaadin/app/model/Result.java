package com.vaadin.app.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Result implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String field1;
	private String field2;
	private Date date1;
	private Date date2;

	
	
	public Date getDate1() {
		return date1;
	}



	public void setDate1(Date date1) {
		this.date1 = date1;
	}



	public Date getDate2() {
		return date2;
	}



	public void setDate2(Date date2) {
		this.date2 = date2;
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getField1() {
		return field1;
	}
	public void setField1(String field1) {
		this.field1 = field1;
	}
	public String getField2() {
		return field2;
	}
	public void setField2(String field2) {
		this.field2 = field2;
	}

	
	
	
	
}
