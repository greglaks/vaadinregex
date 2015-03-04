package com.vaadin.app.regex;

public class LoggingFormat extends Format{

	public LoggingFormat(String format ) {
		super(format, "(?=\\w)", "r");
	}

}
