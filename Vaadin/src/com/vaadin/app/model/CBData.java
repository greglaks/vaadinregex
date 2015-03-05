package com.vaadin.app.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.vaadin.app.regex.RegexWrapper;
import com.vaadin.ui.TextField;

public class CBData {

	private String format;
	private TextField textField;
	private String id;
	
	public CBData(String string, TextField textField2, String id) {
		this.format = string;
		this.textField = textField2;
		this.id = id;
		if(textField != null){
			textField.setColumns(4);
			textField.setValue(format);
		}

	}
	
	public String getRegex(){
		RegexWrapper r = new RegexWrapper(id);
		String format = textField.getValue();
		this.format = format;
		String regex = r.getRegex(format);
		return regex;
	}
	
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public TextField getTextField() {
		return textField;
	}
	public void setTextField(TextField textField) {
		this.textField = textField;
	}

	
	
	
	
}
