package com.vaadin.app;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.vaadin.app.model.Result;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;

public class ResultItem extends CssLayout {

	private static final long serialVersionUID = 3545202531484800153L;
	private Result item;
	private int index;
	private FocusListener textFocusListener = new FocusListener() {
		
		@Override
		public void focus(FocusEvent event) {
			TextField o  = (TextField) event.getComponent();
			String value = o.getValue();
			
			int pos = o.getCursorPosition();
			Notification.show(value+ " is selected", Type.TRAY_NOTIFICATION);
			
		}
	};
	
	
	public ResultItem(Result item, int index){
		this.item = item;
		this.index = index;
		addStyleName("rsingleitem");
		createContents();
		
	}

	private void createContents() {
		TextField textfield1 = new TextField();
		TextField textfield2 = new TextField();
		TextField textfield3 = new TextField();
		
		Date d = Calendar.getInstance().getTime();
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String d1 = format.format(d);
		String d2 = format.format(d);
		
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
		
		textfield1.addFocusListener(textFocusListener);
		textfield2.addFocusListener(textFocusListener);
		textfield3.addFocusListener(textFocusListener);
		
		addComponent(textfield1);
		addComponent(textfield2);
		addComponent(textfield3);
		
	}

}
