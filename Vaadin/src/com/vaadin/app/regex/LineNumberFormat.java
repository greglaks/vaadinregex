package com.vaadin.app.regex;

public class LineNumberFormat extends Format{

	public LineNumberFormat(String format) {
		super(format, "(?=\\d)", "L");
	}

	public static void main(String[] args){
		String format = "%-20f";
		//String date = "%d{yyyy}";
		LineNumberFormat tf  = new LineNumberFormat(format);
		System.out.println(tf.getResult());
		
	}
}
