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
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		if(padding != -1 || padding != 0)
			regex = this.regex;
		return regex;
	}


	public String getResult() {
		return result;
	}
}
