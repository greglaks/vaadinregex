package com.vaadin.app;

import com.vaadin.app.regex.RegexWrapper;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;

public class RegexTestPage extends VerticalLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 366072114362425681L;
	private TextArea textAreaFormat;
	private ClickListener generateRegexListener = new ClickListener(){

		@Override
		public void buttonClick(ClickEvent event) {
			
			try{
				String formats = textAreaFormat.getValue();
				String allRegex = new RegexWrapper().test(formats);

				regexTextArea.setValue(allRegex);
				
			}catch(Exception e){
				Notification.show("Format is not available", Type.ERROR_MESSAGE);
				clearButton.click();
			}
		

			
		}
		
	};
	private ClickListener clearListener = new ClickListener() {
		
		@Override
		public void buttonClick(ClickEvent event) {
			textAreaFormat.setValue("");
			regexTextArea.setValue("");	

		}
	};
	private TextArea regexTextArea;
	private Button clearButton;

	public RegexTestPage(){
		createRegexTextContent();
		setMargin(true);
		setSpacing(true);
	}
	
	private void createRegexTextContent() {
		createCheckBoxGroup();
		createTextBoxRegexFormat();
	}



	private void createTextBoxRegexFormat() {
		textAreaFormat = new TextArea();
		textAreaFormat.setCaption("Log4J Format:");
		textAreaFormat.setWidth("80%");
		textAreaFormat.setRows(4);
		addComponent(textAreaFormat);

		regexTextArea = new TextArea();
		regexTextArea.setWidth("80%");
		regexTextArea.setRows(5);
		regexTextArea.setCaption("Regex");
		addComponent(regexTextArea);
	}

	private void createCheckBoxGroup() {
		VerticalLayout verticalLayout = new VerticalLayout();
		verticalLayout.setSpacing(true);
		
		HorizontalLayout comboBoxLayout = new HorizontalLayout();
		comboBoxLayout.setSpacing(true);
		
		HorizontalLayout textFieldLayout = new HorizontalLayout();
		textFieldLayout.setSpacing(true);
		
		verticalLayout.addComponent(comboBoxLayout);
		verticalLayout.addComponent(textFieldLayout);
		
		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.setSpacing(true);
		
		Button regexGeneratorButton = new Button("Generate Regex");
		regexGeneratorButton.addClickListener(generateRegexListener );
		regexGeneratorButton.setPrimaryStyleName("defaultbutton");
		buttonLayout.addComponent(regexGeneratorButton);
		
		clearButton = new Button("Clear");
		clearButton.setPrimaryStyleName("defaultbutton");
		clearButton.addClickListener(clearListener);
		buttonLayout.addComponent(clearButton);
		
		verticalLayout.addComponent(buttonLayout);
		addComponent(verticalLayout);
	}

	@Override
	public void enter(ViewChangeEvent event) {


	}
	

	
	public static void main(String[] args){
		String input = "(black%Cwhite) (white%dtest)";
		String[] all = input.split(" ");
		String allRegex = "";
		for(int i=0;i<all.length;i++){
			String format = all[i];
			int indexOfFirstBracket = format.indexOf("(")+1;
			int indexFormat = format.indexOf("%");
			int indexOfLastBracket = format.indexOf(")") ;
			int indexOfLastFormat = indexFormat+2;
			String firstPlus = format.substring(indexOfFirstBracket, indexFormat).trim();
			String lastPlust = format.substring(indexOfLastFormat, indexOfLastBracket).trim();
			String formatRegex = format.substring(indexFormat, indexOfLastFormat).trim();
			
			
			String regexFirst = "" ;
			String regexSecond = "";
			
			if(!firstPlus.equals(""))
				regexFirst =  "(?=.*("+firstPlus+").*)";
			if(!lastPlust.equals(""))
				regexSecond = "(?=.*("+lastPlust+").*)";
			
			
			if(formatRegex.equals("%c")){
				String t = "("+regexFirst+"(?=\\w)"+regexSecond+")";
				allRegex = allRegex + t;
			}
			else if(formatRegex.equals("%C")){
				String t = "("+regexFirst+"(?=[\\.{1,}][a-z])"+regexSecond+")";
				allRegex = allRegex + t;
			}
			else if(formatRegex.equals("%d")){
				String t = "("+regexFirst+"(?=([0-9]+):([0-5][0-9]|60):([0-5][0-9]|60),([0-9]{3}))"+regexSecond+")";
				allRegex = allRegex + t;
			}

			System.out.println(allRegex);
//			System.out.println(firstPlus);
//			System.out.println(lastPlust);
//			System.out.println(formatRegex);
		}
		
	}

}
