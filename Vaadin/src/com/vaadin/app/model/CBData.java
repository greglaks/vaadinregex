package com.vaadin.app.model;

import com.vaadin.ui.TextField;

public class CBData {

	private String format;
	private TextField textField;
	
	public CBData(String string, TextField textField2) {
		this.format = string;
		this.textField = textField2;
		if(textField != null){
			textField.setColumns(4);
			textField.setValue(format);
		}
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
