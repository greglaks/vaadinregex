package com.vaadin.app.regex;

public class MessageFormat extends Format{

	public MessageFormat(String format) {
		super(format, "(?=\\w)", "m");
	}
	
	public static void main(String[] args){
		String date = "%-25m";
		//String date = "%d{yyyy}";
		MessageFormat tf  = new MessageFormat(date);
		System.out.println(tf.getResult());
		
	}
}
