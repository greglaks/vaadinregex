package com.vaadin.app.regex;

public abstract class Format {
	protected String format;
	protected String regex ;
	protected String result;
	private String id;
	
	public Format(String format, String regex, String id){
		this.format = format;
		this.regex = regex;
		this.id = id;
		try {
			this.result = generateRegex();
			this.result = processStartEndChar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String processStartEndChar() {
		String startChar = processStartChar();
		String endChar = processEndChar();
		String startRegex = "";
		String endRegex = "";
		if(!startChar.equals(""))
			startRegex = "(.*"+startChar+".*)";
		if(!startChar.equals(""))
			endRegex = "(.*"+endChar+".*)";
		
		String result = startRegex+this.result+endRegex;
		return result;
	}

	private String processEndChar() {
		int inIndex = format.indexOf(id);
		String endChar = format.substring(inIndex+1, format.length());
		return endChar;
	}

	private String processStartChar() {
		int startRegexIndex = format.indexOf("%");
		String startChar = format.substring(0, startRegexIndex);
		return startChar;
	}

	private int getPadding() {
		int padding = 0;
		int start = format.indexOf("%")+1;
		int end = format.indexOf(id);
		String padd = format.substring(start, end).trim();
		if(!padd.equals(""))
			padding = Integer.parseInt(padd);
		return padding;
	}

	protected String generateRegex() throws Exception {
		String regex = null;
		int padding = getPadding();
		if(padding != -1 || padding != 0){
			
			regex = this.regex;
		}
		return regex;
	}


	public String getResult() {
		return result;
	}
}
