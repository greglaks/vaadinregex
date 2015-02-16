package com.vaadin.app;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.UI;

public class MainPage extends CustomLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3264797138636497258L;
	
	private ClickListener searchPageListener = new ClickListener() {
		
		@Override
		public void buttonClick(ClickEvent event) {
			
			n.navigateTo("/searchpage");
		}
	};
	
	private ClickListener alertPageListener = new ClickListener() {
		
		@Override
		public void buttonClick(ClickEvent event) {
			
			n.navigateTo("/alertpage");
		}
	};

	private ClickListener regexTestListener = new ClickListener(){

		@Override
		public void buttonClick(ClickEvent event) {
			n.navigateTo("/regextestpage");
			
		}
		
	};
	private Navigator n;

	public MainPage(){
		super("main");
		createContents();
	}
	private void createContents() {
		Button searchPageButton = new Button("Search page");
		Button alertPageButton = new Button("Alert page");
		Button regexTestPageButton = new Button("Regex test page");
		
		searchPageButton.setPrimaryStyleName("menuitem");
		alertPageButton.setPrimaryStyleName("menuitem");
		alertPageButton.addStyleName("distanceleft");
		
		regexTestPageButton.setPrimaryStyleName("menuitem");
		regexTestPageButton.addStyleName("distanceleft");
		
		searchPageButton.addClickListener(searchPageListener);
		alertPageButton.addClickListener(alertPageListener);
		regexTestPageButton.addClickListener(regexTestListener);
		
		addComponent(searchPageButton, "searchpage");
		addComponent(alertPageButton, "alertpage");
		addComponent(regexTestPageButton, "regextestpage");
		
		CssLayout content = new CssLayout();
		addComponent(content, "content");
		
		 n = new Navigator(UI.getCurrent(), content);
		((VaadinUI)UI.getCurrent()).setNavigator(n);
		
		n.addView("/searchpage", SearchPage.class);
		n.addView("/alertpage", AlertPage.class);
		n.addView("/regextestpage", RegexTestPage.class);
		n.navigateTo("/searchpage");
	}
}
