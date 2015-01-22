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
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class ResultItem extends CssLayout {

	private static final long serialVersionUID = 3545202531484800153L;
	private Result item;
	private int index;
	private IBackendService backendService = new IBackendServiceImpl();
	private Alert alert = null;

	
	public ResultItem(Result item, int index){
		this.item = item;
		this.index = index;
		addStyleName("rsingleitem");
		createContents();
	}

	private void createContents() {
		VerticalLayout layout = new VerticalLayout();
		TextField textfield1 = new TextField();
		TextField textfield2 = new TextField();
		TextField textfield3 = new TextField();
		
		Date d = Calendar.getInstance().getTime();
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String d1 = format.format(d);
		String d2 = format.format(d);
		
		// On click/selecting the text:
		// 1. Search the text
		// 2. If not found window will appear to let the user fill the alert pattern
		// 3. If found, get the alert object and set the values into the form
		
		final MyText resultText = new MyText("Title "+String.valueOf(index));
		this.alert =  backendService.getAlertByText(resultText.getCaption());
		if(alert != null)
			resultText.setCaption(resultText.getCaption()+"<span class='hasalert'> !</span>");
//		resultText.addStyleName("link");
		resultText.addSelectListener(new OnSelectListener() {
			
			@Override
			public void onSelect(String text) {
				Window w = createPopupWindow(text);
				UI.getCurrent().addWindow(w);
				
			}
		});
				
		textfield1.setValue("Title "+String.valueOf(index));
		textfield2.setValue("This is item #"+String.valueOf(index));
		textfield3.setValue(d1 +" - "+ d2);
		
		textfield1.setReadOnly(true);
		textfield2.setReadOnly(true);
		textfield3.setReadOnly(true);
		
		textfield1.setImmediate(true);
		textfield2.setImmediate(true);
		textfield3.setImmediate(true);
		
		textfield1.addStyleName("rsitemstyle");
		textfield2.addStyleName("rsitemstyle");
		textfield3.addStyleName("rsitemstyle");
		
		layout.addComponent(resultText);
		layout.addComponent(textfield2);
		layout.addComponent(textfield3);
		layout.setMargin(true);
		layout.setSpacing(true);
		
		addComponent(layout);
		
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
		}
		else{
			nameText.setValue(text);
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
				bService.createAlert(text, alert);
//				.setCaption(b.getCaption()+"<span class='hasalert'> !</span>");
				UI.getCurrent().removeWindow(w);
				event.getButton().setData(alert);
			}
		});
		
		nameText.setEnabled(false);
		
		form.addComponent(nameText);
		form.addComponent(severityText);
		form.addComponent(patternText);
		form.addComponent(notificationCombo);
		form.addComponent(insertButton);
		
		layout.addComponent(form);
		
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
