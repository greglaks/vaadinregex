package com.vaadin.app.regex;

public class ThreadFormat extends Format{

	
	public ThreadFormat(String format) {
		super(format, "(?=.*([\\(]).*)(?=.*([\\)]).*)", "t");
	}


	public static void main(String[] args){
		String format = "%-20t";
		//String date = "%d{yyyy}";
		ThreadFormat tf  = new ThreadFormat(format);
		System.out.println(tf.getResult());
		
	}

}


