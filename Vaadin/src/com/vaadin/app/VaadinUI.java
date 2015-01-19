package com.vaadin.app;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.app.model.Alert;
import com.vaadin.app.model.NotificationType;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("vaadin")
public class VaadinUI extends UI {

	private List<Alert> alerts = new ArrayList<Alert>();
	
	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = VaadinUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		
		MainPage mainPage = new MainPage();
		mainPage.setSizeFull();
		setContent(mainPage);
		createContentList();
	}

	private void createContentList() {
		NotificationType n = new NotificationType();
		n.setId(1);
		n.setLabel("Email");
		
		NotificationType n1 = new NotificationType();
		n1.setId(2);
		n1.setLabel("SMS");
		
		Alert a = new Alert();
		a.setName("Check number at begining");
		a.setPattern("^[^\\d].*");
		a.setSeverity(1);
		a.setNotification(n);
		
		Alert b = new Alert();
		b.setName("Less than three hundert");
		b.setPattern("[^0-9]*[12]?[0-9]{1,2}[^0-9]*");
		b.setSeverity(2);
		b.setNotification(n1);
		
		alerts.add(a);
		alerts.add(b);
		
		
	}

	public List<Alert> getAlerts() {
		return alerts;
	}

	public void addAlert(Alert alert){
		alerts.add(alert);
	}
	
	

}