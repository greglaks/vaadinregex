package com.vaadin.app.regex;

import java.util.List;
import java.util.Vector;

public class RegexWrapper {
	
	private    List<String> formatList = new Vector<String>();
	private boolean newLineExist;

	public RegexWrapper(){
		initList();
	}
	
	private   void initList() {
		formatList.add("C");
		formatList.add("L");
		formatList.add("F");
		formatList.add("r");
		formatList.add("m");
		formatList.add("M");
		formatList.add("x");
		formatList.add("I");
		formatList.add("p");
		formatList.add("n");
		formatList.add("t");
		formatList.add("d");
		formatList.add("c");
		
	}
	
//	public static void main(String[] args){
//		initList();
//		String format = "%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n";
//		String result = test(format);
//		System.out.println(result);
//	}

	public    String test(String format) {
		String allRegex = "";
		int startScanIndex = 0;
		for(int i=0; i<format.length(); i++){
			String item = String.valueOf(format.charAt(i));
			String itemFormat = "";
			
			if(item.equals("%")){
				//Search for the formats
				int index = i;
				while(!formatList.contains(itemFormat)){
					index++;
					itemFormat = String.valueOf(format.charAt(index));
				}
				
				//Search for bracet
				int bracetIndex = index+1;
				int endOfFormatItem = index+1;
				if(bracetIndex < format.length() && String.valueOf(format.charAt(bracetIndex)).equals("{")){
					int endBracet = format.indexOf("}", bracetIndex);
					endOfFormatItem = endBracet+1;
					index = endOfFormatItem;
				}
				
				if(itemFormat.equals("n")){
					newLineExist = true;
				}
				
				String completeFormatItem  = format.substring(startScanIndex, endOfFormatItem);
				System.out.println("Format "+i+": "+completeFormatItem);
				i=index-1;
				startScanIndex = endOfFormatItem;
				allRegex = allRegex + getRegex(completeFormatItem, itemFormat);
			}
			
			// Capture the last char and format it to regex
			if(i == (format.length()-1)){
				String endChar = format.substring(startScanIndex, format.length());
				if(endChar.length() > 0){
					for(int j=0;j<endChar.length(); j++){
						String s = String.valueOf(endChar.charAt(j));
							if(Format.getSpecialChar().contains(s))
								allRegex = allRegex + "(.*\\"+s+".*)";
							else
								allRegex = allRegex + "(.*"+s+".*)";							
					
					}
				}
//				else if(endChar.length() == 0){
//					if(Format.getSpecialChar().contains(endChar))
//						allRegex = allRegex + "(.*\\"+endChar+".*)";
//					else
//						allRegex = allRegex + "(.*"+endChar+".*)";
//				}
			}
				
		}
		if(newLineExist){
			return allRegex;			
		}
		else{
			return allRegex+".";
		}

	}

	public   String getRegex(String format, String id) {
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
			regex = new RFormat(format).getResult();
		else if(id.equals("x"))
			regex = new NDCFormat(format).getResult();	
		else if(id.equals("c"))
			regex = new CategoryFormat(format).getResult();			
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
