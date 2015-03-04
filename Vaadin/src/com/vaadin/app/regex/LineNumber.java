package com.vaadin.app.regex;

public class LineNumber extends Format{

	public LineNumber(String format) {
		super(format, "(?=\\d)", "L");
	}

	public static void main(String[] args){
		String format = "%-20f";
		//String date = "%d{yyyy}";
		LineNumber tf  = new LineNumber(format);
		System.out.println(tf.getResult());
		
	}
}
