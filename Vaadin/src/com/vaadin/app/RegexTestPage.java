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
	private ClickListener generateRegexListener = new ClickListener(){

		@Override
		public void buttonClick(ClickEvent event) {
			String format = textAreaFormat.getValue();
			String[] all = format.split(" ");
			String allRegex = "";
			try{
				for(int i=0;i<all.length;i++){
					String singleFormat = all[i];
					int indexOfFirstBracket = singleFormat.indexOf("(")+1;
					int indexFormat = singleFormat.indexOf("%");
					int indexOfLastBracket = singleFormat.indexOf(")") ;
					int indexOfLastFormat = indexFormat+2;
					String firstPlus = singleFormat.substring(indexOfFirstBracket, indexFormat).trim();
					String lastPlust = singleFormat.substring(indexOfLastFormat, indexOfLastBracket).trim();
					String formatRegex = singleFormat.substring(indexFormat, indexOfLastFormat).trim();
					
					
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
					else if(formatRegex.equals("%F")){
						String t = "("+regexFirst+"(?=.*(log).*)"+regexSecond+")";
						allRegex = allRegex + t;
					}
					else if(formatRegex.equals("%I")){
						String t = "("+regexFirst+"(?=\\w)"+regexSecond+")";
						allRegex = allRegex + t;
					}
					else if(formatRegex.equals("%M")){
						String t = "("+regexFirst+"(?=\\w)"+regexSecond+")";
						allRegex = allRegex + t;
					}
					else if(formatRegex.equals("%m")){
						String t = "("+regexFirst+"(?=\\w)"+regexSecond+")";
						allRegex = allRegex + t;
					}
					else if(formatRegex.equals("%n")){
						String t = "("+regexFirst+"(?=\\w)"+regexSecond+")";
						allRegex = allRegex + t;
					}
					else if(formatRegex.equals("%p")){
						String t = "("+regexFirst+"(?=.*(INFO|ERROR|WARNING|FATAL|OFF|DEBUG|TRACE|ALL).*)"+regexSecond+")";
						allRegex = allRegex + t;
					}
					else if(formatRegex.equals("%r")){
						String t = "("+regexFirst+"(?=\\w)"+regexSecond+")";
						allRegex = allRegex + t;
					}
					else if(formatRegex.equals("%t")){
						String t = "("+regexFirst+"(?=.*([\\(]).*)(?=.*([\\)]).*)"+regexSecond+")";
						allRegex = allRegex + t;
					}
					else if (formatRegex.equals("(%L")){
						String t = "("+regexFirst+"(?=\\d)"+regexSecond+")";
						allRegex = allRegex + t;
					}
					else{
						
					}
//					System.out.println(allRegex);
//					System.out.println(firstPlus);
//					System.out.println(lastPlust);
//					System.out.println(formatRegex);
				}

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
			regexTextArea.setEnabled(true);
			textAreaFormat.setValue("");
			regexTextArea.setValue("");
			regexTextArea.setEnabled(false);		
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
		initLabelInfo();
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
		textAreaFormat.setRows(2);
		addComponent(textAreaFormat);
		
		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.setMargin(true);
		buttonLayout.setSpacing(true);
		
		Button regexGeneratorButton = new Button("Generate Regex");
		regexGeneratorButton.addClickListener(generateRegexListener );
		regexGeneratorButton.setPrimaryStyleName("defaultbutton");
		buttonLayout.addComponent(regexGeneratorButton);
		
		clearButton = new Button("Clear");
		clearButton.setPrimaryStyleName("defaultbutton");
		clearButton.addClickListener(clearListener);
		buttonLayout.addComponent(clearButton);
		
		addComponent(buttonLayout);
		
		regexTextArea = new TextArea();
		regexTextArea.setWidth("80%");
		regexTextArea.setRows(5);
		regexTextArea.setEnabled(false);
		addComponent(regexTextArea);
	}

	private void createCheckBoxGroup() {
		VerticalLayout verticalLayout = new VerticalLayout();
		verticalLayout.setSpacing(true);
		
		HorizontalLayout comboBoxLayout = new HorizontalLayout();
		comboBoxLayout.setMargin(true);
		comboBoxLayout.setSpacing(true);
		
		HorizontalLayout textFieldLayout = new HorizontalLayout();
		textFieldLayout.setMargin(true);
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
		
		cb1.setData(new CBData("%c", new TextField()));
		cb2.setData(new CBData("%C", new TextField()));
		cb3.setData(new CBData("%d", new TextField()));
		cb4.setData(new CBData("%F", new TextField()));
		cb5.setData(new CBData("%I",new TextField()));
		cb7.setData(new CBData("%m",new TextField()));
		cb8.setData(new CBData("%M", new TextField()));
		cb9.setData(new CBData("%n",new TextField()));
		cb10.setData(new CBData("%p",new TextField()));
		cb11.setData(new CBData("%r",new TextField()));
		cb12.setData(new CBData("%t",new TextField()));
		cb13.setData(new CBData("%x",new TextField()));
		cb14.setData(new CBData("%L",new TextField()));
		
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
			String format = data.getFormat();
			TextField textField = data.getTextField();
			if(value)
				parent.addComponent(textField);
			
			else
				parent.removeComponent(textField);
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
