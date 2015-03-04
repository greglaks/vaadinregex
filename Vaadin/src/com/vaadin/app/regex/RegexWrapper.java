package com.vaadin.app.regex;

public class RegexWrapper {
	
	private String id;

	public RegexWrapper(String id){
		this.id = id;
	}

	public String getRegex(String format) {
		String regex = "" ;
		if(id.equals("C"))
			regex = new ClassFormat(format).getResult();
		else if(id.equals("d"))
			regex = new TimeFormat(format).getResult();
		else if(id.equals("p"))
			regex = new PriorityFormat(format).getResult();
		else if(id.equals("t"))
			regex = new ThreadFormat(format).getResult();
		else if(id.equals("F"))
			regex = new LogFileFormat(format).getResult();
		else if(id.equals("L"))
			regex = new LineNumberFormat(format).getResult();
		else if(id.equals("I"))
			regex = new OutputLocationFormat(format).getResult();
		else if(id.equals("m"))
			regex = new MessageFormat(format).getResult();
		else if(id.equals("M"))
			regex = new MethodFormat(format).getResult();
		else if(id.equals("n"))
			regex = new SeparatorFormat(format).getResult();
		else if(id.equals("r"))
			regex = new LogFileFormat(format).getResult();
		else
			regex = "";
		return regex;
	}
	
//	 * 		%C - class name. ex. %C{2}
//	 * 		%d - time information. ex. %d{yyyy,MM,dd,HH,mm,ss,SSS}
//	 * 		%p - priority OFF | FATAL | ERROR | WARN | INFO | DEBUG | TRACE | ALL
//	 * 		%t - thread
//	 * 		%F - File name
//	 * 		%L - Line number
//	 * 
//	 * 		%I - Output location
//	 * 		%m - supplied message
//	 * 		%M - method name
//	 * 		%n - line separator
//	 * 		%r - 
	
}
