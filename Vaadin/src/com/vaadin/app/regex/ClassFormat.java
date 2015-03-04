package com.vaadin.app.regex;


public class ClassFormat {
	private String format;
	private String regex = "(?=(.*\\..*))";
	private String result;

	public ClassFormat(String format){
		this.format  = format;
		this.result = generateRegex();
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
		String format = "%.20C{2}";
		ClassFormat c = new ClassFormat(format);
		System.out.println("Format: "+format);
		System.out.println("Regex: "+c.getResult());
	}
	
	
	
}
