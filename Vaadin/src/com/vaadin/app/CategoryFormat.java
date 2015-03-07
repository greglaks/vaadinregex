package com.vaadin.app;

import com.vaadin.app.regex.Format;

public class CategoryFormat extends Format{

	public CategoryFormat(String format) {
		super(format, "(?=\\w)", "c");
		// TODO Auto-generated constructor stub
	}

}
