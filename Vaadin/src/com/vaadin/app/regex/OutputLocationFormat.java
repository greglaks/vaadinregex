package com.vaadin.app.regex;

public class OutputLocationFormat extends Format{

	public OutputLocationFormat(String format) {
		super(format, "(?=\\w)", "I");
	}
	
	public static void main(String[] args){
		String format = "%-20I";
		//String date = "%d{yyyy}";
		OutputLocationFormat tf  = new OutputLocationFormat(format);
		System.out.println(tf.getResult());
		
	}
}
