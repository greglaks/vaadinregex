package com.vaadin.app;

public class RegexFormatter {

	private String logFormat;

	public RegexFormatter(String logFormat){
		this.logFormat = logFormat;
	}
	
	public String generateRegex(){
		String regex = "";
		int length = logFormat.length();
		for(int i=0;i<length; i++){
			char c = logFormat.charAt(i);
			if(c == '%'){
				i++;
				int formatIndex = i;
				try {
					String format = getFormat(formatIndex);
					String regexFormat = getRegex(format);
					regex = regex +regexFormat;
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				String cs = Character.toString(c);
				String regexC = constructRegexChar(cs);
				regex = regex + regexC;
			}
			
		}
		return regex;
	}

	

	private String getAfterFormat(int formatIndex) {
		String afterText = logFormat.substring(formatIndex+1, formatIndex+2 );

		return afterText;
	}

	private String getBeforeFormat(int formatIndex) {
		if(formatIndex == 0)
			return "";
		String beforeText = logFormat.substring(formatIndex-1, formatIndex);

		return beforeText;
	}

	private String getFormat(int formatIndex) throws Exception{
		int formatIndexLength = formatIndex;
		if(formatIndex > logFormat.length())
			throw new Exception("Log4J format is not valid");
		String format = logFormat.substring(formatIndexLength, formatIndexLength+1);
		if(format.equals(" "))
			throw new Exception("There is whitespace after char %");
		return format;
	}
	
	public static void main(String[] args){
		String format = "-%d-%c{2}-%F-";
		RegexFormatter formatter = new RegexFormatter(format);
		String regex = formatter.generateRegex();
		System.out.println("Regex :"+regex);
	}
	private String constructRegexChar(String anyChar){
		String regexChar = "";
		if(!anyChar.equals(""))
			regexChar =  "(.*"+anyChar+".*)";
		return regexChar;
		
	}
	private String getRegex(String format) {
		String t = "";
		if(format.equals("%c")){
			 t = "(?=\\w)";
		}
		else if(format.equals("C")){
			t = "(?=[\\.{1,}][a-z])";
		}
		else if(format.equals("d")){
			 t = "(?=([0-9]+):([0-5][0-9]|60):([0-5][0-9]|60),([0-9]{3}))";
		}
		else if(format.equals("F")){
			t = "(?=.*(log).*)";
		}
		else if(format.equals("I")){
			t = "(?=\\w)";
		}
		else if(format.equals("M")){
			t = "(?=\\w)";
		}
		else if(format.equals("m")){
			t = "(?=\\w)";
		}
		else if(format.equals("n")){
			t = "(?=\\w)";
		}
		else if(format.equals("p")){
			 t = "(?=.*(INFO|ERROR|WARNING|FATAL|OFF|DEBUG|TRACE|ALL).*)";
		}
		else if(format.equals("r")){
			t = "(?=\\w)";
		}
		else if(format.equals("t")){
			t = "(?=.*([\\(]).*)(?=.*([\\)]).*)";
		}
		else if (format.equals("(L")){
			t = "(?=\\d)";
		}
		else{
			
		}
		return t;
	}
}
