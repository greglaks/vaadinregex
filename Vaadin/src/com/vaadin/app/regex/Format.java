package com.vaadin.app.regex;

import java.util.ArrayList;
import java.util.List;

public abstract class Format {
	protected String format;
	protected String regex ;
	protected String result;
	private static List<String> specialChar = new ArrayList<String>();
	private String id;
	
	public Format(String format, String regex, String id){
		this.format = format;
		this.regex = regex;
		this.id = id;
		try {
			initSpecialChar();
			this.result = generateRegex();
			this.result = processStartEndChar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void initSpecialChar() {
		if(specialChar.size() != 0)
			return;
		
		specialChar.add("]");
		specialChar.add("[");
		specialChar.add(")");
		specialChar.add("(");
		specialChar.add("*");
		specialChar.add(".");
		specialChar.add("?");
		specialChar.add("|");
		specialChar.add("<");
		specialChar.add(">");
		specialChar.add("\"");
		specialChar.add("'");
		specialChar.add("&");
		specialChar.add("}");
		specialChar.add("{");
		specialChar.add(":");
	}

	private String processStartEndChar() {
		String startChar = processStartChar();
//		String endChar = processEndChar();
		String startRegex = "";
		String endRegex = "";
		if(!startChar.equals("") && !startChar.equals(" "))
			startRegex = "(.*"+startChar+".*)";
//		if(!endChar.equals(""))
//			endRegex = "(.*"+endChar+".*)";
		
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
		String startRx = "";
		if(startChar.length() == 1){
			if(specialChar.contains(startChar)){
				startRx = "\\"+startChar;
			}else{
				startRx = startChar;
			}
		}else if(startChar.length() > 0){
			for(int i=0;i<startChar.length();i++){
				String c = String.valueOf(startChar.charAt(i));
				if(specialChar.contains(c)){
					startRx = startRx+"\\"+c;
				}
				else{
					startRx = startRx+c;
				}
			}
		}
		else{
			
		}
		return startRx;
	}

	private int getPadding() {
		int padding = 0;
		int start = format.indexOf("%")+1;
		int end = format.indexOf(id, start);
		String padd = format.substring(start, end).trim();
		if(!padd.equals("") && !padd.contains("."))
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

	public static List<String> getSpecialChar() {
		initSpecialChar();
		return specialChar;
	}
	
	
}
