package com.vaadin.app.regex;

public class PriorityFormat extends Format{


	public PriorityFormat(String format) {
		super(format, "(?=.*(INFO|ERROR|WARNING|FATAL|OFF|DEBUG|TRACE|ALL).*)", "p");
	}

	public static void main(String[] args){
		String date = "%-20p";
		//String date = "%d{yyyy}";
		PriorityFormat tf  = new PriorityFormat(date);
		System.out.println(tf.getResult());
		
	}

}
