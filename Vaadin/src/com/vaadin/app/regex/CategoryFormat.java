package com.vaadin.app.regex;


public class CategoryFormat {
	private String format;
	private String regex = "(?=(.*\\..*))";
	private String result;


	public CategoryFormat(String format){
		this.format  = format;
		this.result = generateRegex();
		this.result = processStartEndChar();
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
		int endRegexIndex = 0;
		String endChar = "";
		if(format.contains("}"))
			endRegexIndex = format.indexOf("}");
		
		else
			endRegexIndex = format.indexOf("c");			
		
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


	private String generateRegex() {
		int separatorNum = extractSeparator();
		if(separatorNum < 2)
			return regex;
		else{
			String regex = "(?=";
			int occurance = separatorNum - 1;
			String subClassOccuranceRegex = "(.*\\..*){"+occurance+"}";
			return regex+subClassOccuranceRegex+")";
		}

	}

	private int extractSeparator() {
		int separatorNum = 0;
		int bracetOpen = format.indexOf("{");
		int bracetClose = format.indexOf("}");
		if(bracetOpen != -1 || bracetClose != -1){
			String s = format.substring(bracetOpen+1, bracetClose);
			separatorNum = Integer.parseInt(s);
		}
		return separatorNum;
	}


	public String getResult() {
		return result;
	}

	public static void main(String[] args){
		String format = "greg%cgreg";
		CategoryFormat c = new CategoryFormat(format);
		System.out.println("Format: "+format);
		System.out.println("Regex: "+c.getResult());
	}
	
	
	
}
