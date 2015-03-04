package com.vaadin.app.regex;

public class SeparatorFormat extends Format{

	public SeparatorFormat(String format) {
		super(format, "(?=\\w)", "n");
	}

}
