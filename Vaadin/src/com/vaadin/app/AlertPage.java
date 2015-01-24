package com.vaadin.app;

import java.util.List;
import java.util.Map;

import com.vaadin.app.dao.IBackendService;
import com.vaadin.app.dao.IBackendServiceImpl;
import com.vaadin.app.model.Alert;
import com.vaadin.app.model.NotificationType;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class AlertPage extends CustomLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6917206672516925576L;
	private ComboBox notificationField;
	private TextField alertSeverityField;
	private TextField alertPatternField;
	private TextField alertNameField;
	private ValueChangeListener selectListValueChangeListener = new ValueChangeListener() {
		
		@Override
		public void valueChange(ValueChangeEvent event) {
			Alert a = (Alert) event.getProperty().getValue();
			if(a == null)
				return;
			String alertName = a.getName();
			String pattern = a.getPattern();
			String severity = String.valueOf(a.getSeverity());
			NotificationType notification = a.getNotification();
			
			notificationField.select(notification);
			alertSeverityField.setValue(severity);
			alertPatternField.setValue(pattern);
			alertNameField.setValue(alertName);
		}
	};
	private ClickListener createAlertListener = new ClickListener() {
		
		@Override
		public void buttonClick(ClickEvent event) {
			Window w = new Window();
			CssLayout createNewAlertLayout = createNewAlertPopUp(w);
			w.setContent(createNewAlertLayout);
			w.setClosable(true);
			w.setModal(true);
			w.setResizable(false);
			w.setCaption("Create alert");
			w.center();
			UI.getCurrent().addWindow(w);
		}
	};
	private ClickListener deleteAlertListener = new ClickListener() {
		
		@Override
		public void buttonClick(ClickEvent event) {
			Object o = alertList.getValue();
			if(o == null)
				return;
			Alert a = (Alert) o;
			backendService.deleteAlert(a);
			refreshAlertListContent();
			resetFields();
		}
	};
	private ListSelect alertList;
	private IBackendService backendService = new IBackendServiceImpl();
	
	public AlertPage(){
		super("alertpage");
		createContents();
	}

	protected void resetFields() {
		notificationField.setValue("");;
		alertSeverityField.setValue("");;
		alertPatternField.setValue("");;
		alertNameField.setValue("");;
		
	}

	protected CssLayout createNewAlertPopUp(final Window w) {
		CssLayout layout = new CssLayout();
		layout.addStyleName("paddingnormal");
		FormLayout form = new FormLayout();
		
		final TextField alertNameField = new TextField("Name");
		final TextField alertPatternField = new TextField("Pattern");
		final TextField alertSeverityField = new TextField("Severity");
		final TextField notificationField = new TextField("Notification text");
		
		alertNameField.setRequired(true);
		alertPatternField.setRequired(true);
		alertSeverityField.setRequired(true);
		notificationField.setRequired(true);
		
		Button alertSave = new Button("Save");
		alertSave.setPrimaryStyleName("defaultbutton");
		alertSave.addClickListener(new ClickListener() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 5161423700004250226L;

			@Override
			public void buttonClick(ClickEvent event) {
				String name = alertNameField.getValue().trim();
				String pattern = alertPatternField.getValue().trim();
				String severity = alertSeverityField.getValue().trim();
				String notification = notificationField.getValue().trim();
				
				if(name.equals("") || pattern.equals("") || severity.equals("") || notification.equals("")){
					Notification.show("All fields are required", Type.ERROR_MESSAGE);
					return;
				}
				
				Alert a = new Alert();
				a.setName(name);
				NotificationType type = new NotificationType();
				type.setLabel(notification);
				a.setNotification(type);
				a.setPattern(pattern);
				a.setSeverity(Integer.parseInt(severity));
				((VaadinUI)UI.getCurrent()).addAlert(a);
				((VaadinUI)UI.getCurrent()).removeWindow(w);
				refreshAlertListContent();
			}
		});
		
		
		form.addComponent(alertNameField);
		form.addComponent(alertPatternField);
		form.addComponent(alertSeverityField);
		form.addComponent(notificationField);
		form.addComponent(alertSave);
		
		layout.addComponent(form);
		
		return layout;
	}

	private void createContents() {
		createSelectList();
		createAlertDetail();
	}

	private void createAlertDetail() {
		 alertNameField = new TextField("Name");
		 alertPatternField = new TextField("Pattern");
		 alertSeverityField = new TextField("Severity");
		 notificationField = new ComboBox("Notification");
		 insertNotificationItem(notificationField);
		Button alertSave = new Button("Save");
		alertSave.setPrimaryStyleName("defaultbutton");
		
		addComponent(alertNameField, "alertname");
		addComponent(alertPatternField, "alertpattern");
		addComponent(alertSeverityField, "alertseverity");
		addComponent(notificationField, "alertnotification");
		addComponent(alertSave, "save");
	}

	private void insertNotificationItem(ComboBox notificationCombo) {
		List<NotificationType> notificationTypes = ((VaadinUI) UI.getCurrent()).getNotificationTypes();
		for(NotificationType n : notificationTypes){
			notificationCombo.addItem(n);
			notificationCombo.setItemCaption(n, n.getLabel());
		}
		
	}

	private void createSelectList() {
		VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		layout.setSpacing(true);
		alertList = new ListSelect("Alert list");
		alertList.addValueChangeListener(selectListValueChangeListener);
		alertList.setNullSelectionAllowed(false);
		alertList.setMultiSelect(false);
		alertList.setImmediate(true);
		refreshAlertListContent();
		alertList.addStyleName("alertlist");
		Button createAlertButton = new Button("Create new");
		createAlertButton.setPrimaryStyleName("defaultbutton");
		
		Button deleteAlertButton = new Button("Delete");
		deleteAlertButton.setPrimaryStyleName("defaultbutton");
		deleteAlertButton.addClickListener(deleteAlertListener );
		createAlertButton.addClickListener(createAlertListener );
		
		layout.addComponent(alertList);
		layout.addComponent(createAlertButton);
		layout.addComponent(deleteAlertButton);
		addComponent(layout, "listlayout");
	}

	private void refreshAlertListContent() {
		alertList.removeAllItems();
		List<Alert> alerts = ((VaadinUI)UI.getCurrent()).getAlerts();
		for(Alert a : alerts){
			alertList.addItem(a);
			alertList.setItemCaption(a, a.getName());
		}
		
	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

}
