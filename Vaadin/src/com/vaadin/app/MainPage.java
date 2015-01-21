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

	private Navigator n;
	public MainPage(){
		super("main");
		createContents();
	}
	private void createContents() {
//		Button searchPageButton = new Button("Search page");
//		Button alertPageButton = new Button("Alert page");	
		
//		searchPageButton.addClickListener(searchPageListener);
//		alertPageButton.addClickListener(alertPageListener);
		
//		addComponent(searchPageButton, "searchpage");
//		addComponent(alertPageButton, "alertpage");
		
		CssLayout content = new CssLayout();
		addComponent(content, "content");
		
		 n = new Navigator(UI.getCurrent(), content);
		((VaadinUI)UI.getCurrent()).setNavigator(n);
		
		n.addView("/searchpage", SearchPage.class);
//		n.addView("/alertpage", AlertPage.class);
		n.navigateTo("/searchpage");
	}
}
