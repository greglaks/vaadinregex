package com.vaadin.app;

/**
 * 		%C - class name. ex. %C{2}
 * 		%d - time information. ex. %d{yyyy,MM,dd,HH,mm,ss,SSS}
 * 		%p - priority OFF | FATAL | ERROR | WARN | INFO | DEBUG | TRACE | ALL
 * 		%t - thread
 * 		%F - File name
 * 		%L - Line number
 * 
 * 		%I - Output location
 * 		%m - supplied message
 * 		%M - method name
 * 		%n - line separator
 * 		%r - 
 * %x - NDC 
 * %X - conversion character  
 */
import java.util.ArrayList;
import java.util.List;

import com.vaadin.app.model.CBData;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class RegexTestPage extends VerticalLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 366072114362425681L;
	private TextArea textAreaFormat;
	private List<CBData> cbList = new ArrayList<CBData>();
	private ClickListener generateRegexListener = new ClickListener(){

		@Override
		public void buttonClick(ClickEvent event) {
			
			try{
				String allRegex = "";
				String allFormat = "";
				for(CBData data :cbList){
					String regex = data.getRegex();
					String format = data.getFormat();
					allRegex = allRegex + regex;
					allFormat = allFormat + format;
				}
				regexTextArea.setReadOnly(false);
				regexTextArea.setValue(allRegex);
				regexTextArea.setReadOnly(true);
				
				textAreaFormat.setReadOnly(false);
				textAreaFormat.setValue(allFormat);
				textAreaFormat.setReadOnly(true);
			}catch(Exception e){
				Notification.show("Format is not available", Type.ERROR_MESSAGE);
				clearButton.click();
			}
		

			
		}
		
	};
	private ClickListener clearListener = new ClickListener() {
		
		@Override
		public void buttonClick(ClickEvent event) {
			regexTextArea.setReadOnly(false);
			textAreaFormat.setReadOnly(false);
			textAreaFormat.setValue("");
			regexTextArea.setValue("");
			textAreaFormat.setReadOnly(true);
			regexTextArea.setReadOnly(true);		
			cb1.setValue(false);
			cb2.setValue(false);
			cb3.setValue(false);
			cb4.setValue(false);
			cb5.setValue(false);
			cb7.setValue(false);
			cb8.setValue(false);
			cb10.setValue(false);
			cb9.setValue(false);
			cb11.setValue(false);
			cb12.setValue(false);
			cb13.setValue(false);
			cb14.setValue(false);
		}
	};
	private TextArea regexTextArea;
	private CheckBox cb14;
	private CheckBox cb13;
	private CheckBox cb12;
	private CheckBox cb11;
	private CheckBox cb9;
	private CheckBox cb10;
	private CheckBox cb8;
	private CheckBox cb7;
	private CheckBox cb5;
	private CheckBox cb4;
	private CheckBox cb3;
	private CheckBox cb2;
	private CheckBox cb1;
	private Button clearButton;

	public RegexTestPage(){
		createRegexTextContent();
		setMargin(true);
		setSpacing(true);
	}
	
	private void createRegexTextContent() {
//		initLabelInfo();
		createCheckBoxGroup();
		createTextBoxRegexFormat();
	}

	private void initLabelInfo() {
		Label info = new Label();
		info.setContentMode(ContentMode.HTML);
		addComponent(info);
		info.setValue("<p>Acceptable format: </p>"
					+ "<ol>"
					+ "<li>All format modification should be written within the (Opening and closing) brackets ex. for %C format <b>(one%CTwo)</b></li>"
					+ "<li>Between the format should be separated by white space ex. for %C and %r formats <b>(one%Ctwo) (test%rblah)</b></li>"
					+ "<li>There should be no whitespace in bracet. See example above</li>"
					+ "<li>Violation will cause an exception</li>"
					+ "</ol>");
	}

	private void createTextBoxRegexFormat() {
		textAreaFormat = new TextArea();
		textAreaFormat.setCaption("Log4J Format:");
		textAreaFormat.setWidth("80%");
		textAreaFormat.setRows(4);
		textAreaFormat.setReadOnly(true);
		addComponent(textAreaFormat);

		regexTextArea = new TextArea();
		regexTextArea.setWidth("80%");
		regexTextArea.setRows(5);
		regexTextArea.setReadOnly(true);
		regexTextArea.setCaption("Regex");
		addComponent(regexTextArea);
	}

	private void createCheckBoxGroup() {
		VerticalLayout verticalLayout = new VerticalLayout();
		verticalLayout.setSpacing(true);
		
		HorizontalLayout comboBoxLayout = new HorizontalLayout();
//		comboBoxLayout.setMargin(true);
		comboBoxLayout.setSpacing(true);
		
		HorizontalLayout textFieldLayout = new HorizontalLayout();
//		textFieldLayout.setMargin(true);
		textFieldLayout.setSpacing(true);
		
		verticalLayout.addComponent(comboBoxLayout);
		verticalLayout.addComponent(textFieldLayout);
		
		 cb1 = new CheckBox("%c");
		 cb2 = new CheckBox("%C");
		 cb3 = new CheckBox("%d");
		 cb4 = new CheckBox("%F");
		 cb5 = new CheckBox("%I");
		 cb7 = new CheckBox("%m");
		 cb8 = new CheckBox("%M");
		 cb9 = new CheckBox("%n");
		 cb10 = new CheckBox("%p");
		 cb11 = new CheckBox("%r");
		 cb12 = new CheckBox("%t");
		 cb13 = new CheckBox("%x");
		 cb14 = new CheckBox("%L");
		
		cb1.setData(new CBData("%c", new TextField(), "c"));
		cb2.setData(new CBData("%C", new TextField(),"C"));
		cb3.setData(new CBData("%d", new TextField(),"d"));
		cb4.setData(new CBData("%F", new TextField(),"F"));
		cb5.setData(new CBData("%I",new TextField(),"I"));
		cb7.setData(new CBData("%m",new TextField(),"m"));
		cb8.setData(new CBData("%M", new TextField(),"M"));
		cb9.setData(new CBData("%n",new TextField(),"n"));
		cb10.setData(new CBData("%p",new TextField(),"p"));
		cb11.setData(new CBData("%r",new TextField(),"r"));
		cb12.setData(new CBData("%t",new TextField(),"t"));
		cb13.setData(new CBData("%x",new TextField(),"x"));
		cb14.setData(new CBData("%L",new TextField(),"L"));
		
		RegexPatternValueChangeListener listener = new RegexPatternValueChangeListener(textFieldLayout);
		cb1.addValueChangeListener(listener);
		cb2.addValueChangeListener(listener);
		cb3.addValueChangeListener(listener);
		cb4.addValueChangeListener(listener);
		cb5.addValueChangeListener(listener);
		cb7.addValueChangeListener(listener);
		cb8.addValueChangeListener(listener);
		cb9.addValueChangeListener(listener);
		cb10.addValueChangeListener(listener);
		cb11.addValueChangeListener(listener);
		cb12.addValueChangeListener(listener);
		cb13.addValueChangeListener(listener);
		cb14.addValueChangeListener(listener);
		
		
		cb1.setImmediate(true);
		cb2.setImmediate(true);
		cb3.setImmediate(true);
		cb4.setImmediate(true);
		cb5.setImmediate(true);
		cb7.setImmediate(true);
		cb8.setImmediate(true);
		cb9.setImmediate(true);
		cb10.setImmediate(true);
		cb11.setImmediate(true);
		cb12.setImmediate(true);
		cb13.setImmediate(true);
		cb14.setImmediate(true);
		
		comboBoxLayout.addComponent(cb2);
		comboBoxLayout.addComponent(cb3);
		comboBoxLayout.addComponent(cb4);
		comboBoxLayout.addComponent(cb5);
		comboBoxLayout.addComponent(cb1);
		comboBoxLayout.addComponent(cb7);
		comboBoxLayout.addComponent(cb8);
		comboBoxLayout.addComponent(cb9);
		comboBoxLayout.addComponent(cb10);
		comboBoxLayout.addComponent(cb11);
		comboBoxLayout.addComponent(cb12);
		comboBoxLayout.addComponent(cb13);
		comboBoxLayout.addComponent(cb14);
		
		
		HorizontalLayout buttonLayout = new HorizontalLayout();
//		buttonLayout.setMargin(true);
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
	
	private class RegexPatternValueChangeListener implements ValueChangeListener{
		/**
		 * 
		 */
		private static final long serialVersionUID = 5818193954011701817L;
		private HorizontalLayout parent;

		public RegexPatternValueChangeListener(HorizontalLayout parent){
			this.parent = parent;
		}

		@Override
		public void valueChange(ValueChangeEvent event) {
			CheckBox cb = (CheckBox) event.getProperty();
			boolean value = cb.getValue();
			CBData data = (CBData) cb.getData();

			TextField textField = data.getTextField();
			if(value){
				textField.setValue(data.getFormat());
				cbList.add(data);
				parent.addComponent(textField);
			}
			
			else{
				data.getTextField().setValue("");
				cbList.remove(data);
				parent.removeComponent(textField);
			}
//			String text = textAreaFormat.getValue();
//			if(value){
//				text = text + t+" ";
//				textAreaFormat.setValue(text);				
//			}
//			else{
//				String replace = text.replaceAll(" "+t, "");
//				textAreaFormat.setValue(replace);
//			}
			
		}
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
