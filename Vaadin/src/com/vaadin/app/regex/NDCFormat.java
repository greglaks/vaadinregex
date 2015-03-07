package com.vaadin.app.regex;

public class NDCFormat extends Format{

	public NDCFormat(String format) {
		super(format, "(?=\\w)", "x");
	}

}
