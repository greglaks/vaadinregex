package com.vaadin.app.regex;

public class TimeFormat {
	
	private String format;
	private String regex = "(?=([0-2][0-9]):([0-5][0-9]|60):([0-5][0-9]|60),([0-9]{3}))";
	private String timeFormatString = "yMdHsS";
	private String result;
	private int dateFormatLenght;

	public TimeFormat(String format){
		this.format = format;
		try {
			this.result = generateRegex();
			processPadding();
			processStartEndChar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void processStartEndChar() {
		String startChar = processStartChar();
//		String endChar = processEndChar();
		String startRegex = "";
		String endRegex = "";
		if(!startChar.equals("") && !startChar.equals(" "))
			startRegex = "(.*"+startChar+".*)";
//		if(!endChar.equals(""))
//			endRegex = "(.*"+endChar+".*)";
		
		result = startRegex+result+endRegex;
	}

	private String processEndChar() {
		int endRegexIndex = 0;
		String endChar = "";
		if(format.contains("}"))
			endRegexIndex = format.indexOf("}");
		
		else
			endRegexIndex = format.indexOf("d");			
		
		endChar = format.substring(endRegexIndex+1, format.length());
		return endChar;
	}

	private String processStartChar() {
		int startRegexIndex = format.indexOf("%");
		String startChar = format.substring(0, startRegexIndex);
		String startRx = "";
		if(startChar.length() == 1){
			if(Format.getSpecialChar().contains(startChar)){
				startRx = "\\"+startChar;
			}			
			else{
				startRx = startChar;
			}
		}else if(startChar.length() > 0){
			for(int i=0;i<startChar.length();i++){
				String c = String.valueOf(startChar.charAt(i));
				if(Format.getSpecialChar().contains(c)){
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

	private void processPadding() {
		int padding = getPadding();
		if(dateFormatLenght<Math.abs(padding)){
			String diffRegex = "";
			int diff = Math.abs(padding) - dateFormatLenght;
			diffRegex = "(\\s{"+diff+"})";
			//Only left padding will be validated on regex
			if(padding > 0){
				this.result = diffRegex+this.result;
			}
		}
		
	}

	private int getPadding() {
		int padding = 0;
		int start = format.indexOf("%")+1;
		int end = format.indexOf("d",start);
		String padd = format.substring(start, end).trim();
		if(!padd.equals("") && !padd.contains("."))
			padding = Integer.parseInt(padd);
		return padding;
	}

	private String generateRegex() throws Exception {
		String regex = "(?=";
		int indexOpenBracet = format.indexOf("{");
		int indexCloseBracet = format.indexOf("}");
		if(indexOpenBracet == 0 || indexCloseBracet == 0 ||indexOpenBracet == -1 || indexCloseBracet == -1)
			regex = this.regex;
		else{
			String dateFormat = format.substring(indexOpenBracet+1, indexCloseBracet);
			dateFormatLenght = dateFormat.length();
			validateDayOrMonth(dateFormat, "d");
			validateDayOrMonth(dateFormat, "M");
			validateYear(dateFormat);
			String rep = "";
			for(int i=0;i<dateFormat.length();i++){
				CharSequence  c = Character.toString(dateFormat.charAt(i));
				if(!rep.contains(c) && !rep.equals("")){
					String formatRegex = getRegex(rep);
					regex = regex + formatRegex;
					rep = "";
					if(!timeFormatString.contains(c)){
						String l = getRegex(c.toString());
						regex = regex  + l;
					}else{
						rep = rep + c;
					}
					
				}
				else
					rep = rep+c;
			}
			if(!rep.equals("")){
				regex = regex + getRegex(rep);
			}
			regex = regex + ")";
		}
		return regex;
	}
	
	
	private void validateYear(String dateFormat) throws Exception{
		int length = dateFormat.length() - dateFormat.replace("y", "").length(); 
		
		if(length == 1 || length == 3 || length > 5)
			throw new Exception("Validation error at year format");
		
	}

	private String getRegex(String rep) {
		String result = "";
		if(rep.equals("dd"))
			result = "([0-3][0-9])";
		else if(rep.equals("MM"))
			result = "([0-1][0-9])";
		else if(rep.equals("yy"))
			result = "([0-9][0-9])";
		else if(rep.equals("yyyy"))
			result = "((19|20)\\d\\d)";
		else if(rep.equals("HH"))
			result = "([0-2][0-9])";
		else if(rep.equals("mm"))
			result = "([0-5][0-9]|60)";
		else if(rep.equals("ss"))
			result = "([0-5][0-9]|60)";
		else if(rep.equals("SS"))
			result = "([0-9]{2})";
		else if(rep.equals("SSS"))
			result = "([0-9]{3})";
		else if(!rep.equals("")){
			result = rep;
		}
		return result;
	}

	private void validateDayOrMonth(String dateFormat, String validator) throws Exception{
		int length = dateFormat.length() - dateFormat.replace(validator, "").length();
		if(length > 2 || length == 1)
			throw new Exception("Validation error at "+validator+" format");
	}


	public String getResult() {
		return result;
	}

	public static void main(String[] args){
		String date = "test%-25d{dd-MM-yyyy HH:mm:ss}test";
		//String date = "%d{yyyy}";
		TimeFormat tf  = new TimeFormat(date);
		System.out.println(tf.getResult());
		
	}
}
