package com.vaadin.app;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.vaadin.app.dao.IBackendService;
import com.vaadin.app.dao.IBackendServiceImpl;
import com.vaadin.app.model.Alert;
import com.vaadin.app.model.NotificationType;
import com.vaadin.app.model.Result;
import com.vaadin.component.MyText;
import com.vaadin.component.MyText.OnSelectListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class ResultItem extends MyText {

	private static final long serialVersionUID = 3545202531484800153L;
	private Result item;
	private int index;
	private IBackendService backendService = new IBackendServiceImpl();
	private Alert alert = null;
	private String textCaption = "";
	
	public ResultItem(Result item, int index){
		this.item = item;
		this.index = index;
		addStyleName("rsingleitem");
		createContents();
	}

	private void createContents() {
		Date d = Calendar.getInstance().getTime();
		DateFormat format = new SimpleDateFormat("HH:mm:ss,SSS");

		//TODO: Replace textCaption with the actual text from Result object
		if(index == 2){
			textCaption = "08:50:45,770 WARN <xml>"+
						  "<test>"+
						  "</xml> ";
		}else
			textCaption =" WARN  [org.hibernate.engine.jdbc.spi.SqlExceptionHelper] (EJB default - 10) SQL Error: 0, SQLState: 23502 "+String.valueOf(index);
		setCaption(textCaption);
		
		//TODO: Write your implementation to get the alert based on criteria
		this.alert =  backendService.getAlertByText(textCaption);
		if(alert != null){
			textCaption = textCaption+"<span class='hasalert'> !</span>";			
		}
		
		addSelectListener(new OnSelectListener() {
			
			@Override
			public void onSelect(String text) {
				Window w = createPopupWindow(text);
				UI.getCurrent().addWindow(w);
				
			}
		});
		
		
	}

	protected Window createPopupWindow(String text) {
		Window w = new Window();
		CssLayout content = createWindowForm(text, w);
		w.setContent(content);
		w.setModal(true);
		w.setResizable(false);
		w.setDraggable(true);
		w.center();
		return w;
	}

	private CssLayout createWindowForm(final String text, final Window w) {
		CssLayout layout = new CssLayout();
		FormLayout form = new FormLayout();
		
		final TextField nameText = new TextField("Name:");
		final TextField severityText = new TextField("Severity:");
		final TextField patternText = new TextField("Pattern: ");
		final ComboBox notificationCombo = new ComboBox("Notification:");
		if(alert != null){
			nameText.setValue(alert.getName());
			severityText.setValue(String.valueOf(alert.getSeverity()));
			patternText.setValue(alert.getPattern());
		}else{
			nameText.setValue(text);
			patternText.setValue(text);
		}

		insertNotificationItems(notificationCombo, alert);
		
		Button insertButton = new Button("Create");
		insertButton.setPrimaryStyleName("defaultbutton");
		insertButton.addStyleName("distancetop");
		insertButton.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				String name = nameText.getValue();
				String pattern = patternText.getValue();
				String notification  = (String) notificationCombo.getValue();
				Alert alert = new Alert();
				alert.setName(name);
				alert.setPattern(pattern);
				
				long id = Calendar.getInstance().getTimeInMillis();
				NotificationType notificationType = new NotificationType();
				notificationType.setId(id);
				notificationType.setLabel(notification);
				alert.setNotification(notificationType);
				IBackendService bService = new IBackendServiceImpl();
				bService.createAlert(alert);
				setCaption(textCaption+"<span class='hasalert'> !</span>");
				UI.getCurrent().removeWindow(w);
				event.getButton().setData(alert);
			}
		});
		
		form.addComponent(nameText);
		form.addComponent(severityText);
		form.addComponent(patternText);
		form.addComponent(notificationCombo);
		form.addComponent(insertButton);
		
		layout.addComponent(form);
		layout.addStyleName("paddingnormal");
		
		return layout;
	}

	private void insertNotificationItems(ComboBox notificationCombo, Alert alert) {
		notificationCombo.addItem("SMS");
		notificationCombo.addItem("Telp.");
		notificationCombo.addItem("Email");
		if(alert != null){
			NotificationType n = alert.getNotification();
			if(n != null){
				String label = n.getLabel();
				if(notificationCombo.containsId(label)){
					notificationCombo.select(label);
				}
			}
		}
		
	}

}
