package com.vaadin.app.regex;

public class LogFileFormat extends Format{

	
	public LogFileFormat(String format) {
		super(format,  "(?=.*(.log|.out).*)", "F");
	}

	public static void main(String[] args){
		String date = "%-20F";
		//String date = "%d{yyyy}";
		LogFileFormat tf  = new LogFileFormat(date);
		System.out.println(tf.getResult());
		
	}

}
