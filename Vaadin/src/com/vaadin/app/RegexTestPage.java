package com.vaadin.app;

/**
 * %C - class name
 * %d - time information
 * %p - priority OFF | FATAL | ERROR | WARN | INFO | DEBUG | TRACE | ALL
 * %t - thread
 * 
 */
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
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
			String format = textAreaFormat.getValue();
			String[] all = format.split(" ");
			String allRegex = "";
			for(int i=0; i<all.length; i++){
				String oneFormat = all[i];
				if(oneFormat.equals("%c")){
					
				}
				else if(oneFormat.equals("%C")){
					String t = "(?=[\\.{1,}][a-z]) ";
					allRegex = allRegex + t;
				}
				else if(oneFormat.equals("%d")){
					String t = "(?=([0-9]+):([0-5][0-9]|60):([0-5][0-9]|60),([0-9]{3})) ";
					allRegex = allRegex + t;
				}
				else if(oneFormat.equals("%F")){
					
				}
				else if(oneFormat.equals("%I")){
					
				}
				else if(oneFormat.equals("%M")){
					
				}
				else if(oneFormat.equals("%m")){
					
				}
				else if(oneFormat.equals("%n")){
					
				}
				else if(oneFormat.equals("%p")){
					String t = "(?=.*(INFO|ERROR|WARNING|FATAL|OFF|DEBUG|TRACE|ALL).*)";
					allRegex = allRegex + t;
				}
				else if(oneFormat.equals("%r")){
					
				}
				else if(oneFormat.equals("%t")){
					String t = "(?=.*([\\(]).*)(?=.*([\\)]).*)";
					allRegex = allRegex + t;
				}
				else{
					
				}
			}
			regexTextArea.setEnabled(true);
			regexTextArea.setValue(allRegex);
			regexTextArea.setEnabled(false);
			
		}
		
	};
	private ClickListener clearListener = new ClickListener() {
		
		@Override
		public void buttonClick(ClickEvent event) {
			regexTextArea.setEnabled(true);
			textAreaFormat.setEnabled(true);
			textAreaFormat.setValue("");
			regexTextArea.setValue("");
			regexTextArea.setEnabled(false);
			textAreaFormat.setEnabled(false);			
		}
	};
	private TextArea regexTextArea;

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
		textAreaFormat.setRows(2);
		textAreaFormat.setEnabled(false);
		addComponent(textAreaFormat);
		
		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.setMargin(true);
		buttonLayout.setSpacing(true);
		
		Button regexGeneratorButton = new Button("Generate Regex");
		regexGeneratorButton.addClickListener(generateRegexListener );
		regexGeneratorButton.setPrimaryStyleName("defaultbutton");
		buttonLayout.addComponent(regexGeneratorButton);
		
		Button clearButton = new Button("Clear");
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
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.setMargin(true);
		horizontalLayout.setSpacing(true);
		
		CheckBox cb1 = new CheckBox("%c");
		CheckBox cb2 = new CheckBox("%C");
		CheckBox cb3 = new CheckBox("%d");
		CheckBox cb4 = new CheckBox("%F");
		CheckBox cb5 = new CheckBox("%I");
		CheckBox cb7 = new CheckBox("%m");
		CheckBox cb8 = new CheckBox("%M");
		CheckBox cb9 = new CheckBox("%n");
		CheckBox cb10 = new CheckBox("%p");
		CheckBox cb11 = new CheckBox("%r");
		CheckBox cb12 = new CheckBox("%t");
		CheckBox cb13 = new CheckBox("%x");
		
		cb1.setData("%c");
		cb2.setData("%C");
		cb3.setData("%d");
		cb4.setData("%F");
		cb5.setData("%I");
		cb7.setData("%m");
		cb8.setData("%M");
		cb9.setData("%n");
		cb10.setData("%p");
		cb11.setData("%r");
		cb12.setData("%t");
		cb13.setData("%x");
		
		RegexPatternValueChangeListener listener = new RegexPatternValueChangeListener();
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
		
		
		horizontalLayout.addComponent(cb2);
		horizontalLayout.addComponent(cb3);
		horizontalLayout.addComponent(cb4);
		horizontalLayout.addComponent(cb5);
		horizontalLayout.addComponent(cb1);
		horizontalLayout.addComponent(cb7);
		horizontalLayout.addComponent(cb8);
		horizontalLayout.addComponent(cb9);
		horizontalLayout.addComponent(cb10);
		horizontalLayout.addComponent(cb11);
		horizontalLayout.addComponent(cb12);
		horizontalLayout.addComponent(cb13);
		addComponent(horizontalLayout);
	}

	@Override
	public void enter(ViewChangeEvent event) {


	}
	
	private class RegexPatternValueChangeListener implements ValueChangeListener{
		public RegexPatternValueChangeListener(){
			
		}

		@Override
		public void valueChange(ValueChangeEvent event) {
			CheckBox cb = (CheckBox) event.getProperty();
			boolean value = cb.getValue();
			String t = (String) cb.getData();
			String text = textAreaFormat.getValue();
			if(value){
				text = text + t+" ";
				textAreaFormat.setValue(text);				
			}
			else{
				String replace = text.replaceAll(" "+t, "");
				textAreaFormat.setValue(replace);
			}
			
		}
	}

}
