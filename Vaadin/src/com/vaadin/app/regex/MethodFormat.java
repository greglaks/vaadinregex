package com.vaadin.app.regex;

public class MethodFormat extends Format{

	public MethodFormat(String format) {
		super(format, "(?=\\w)", "M");
	}

}
