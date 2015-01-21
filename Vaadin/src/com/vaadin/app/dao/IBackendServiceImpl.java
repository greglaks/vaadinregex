package com.vaadin.app.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.vaadin.app.VaadinUI;
import com.vaadin.app.model.Alert;
import com.vaadin.app.model.Result;
import com.vaadin.ui.UI;

public class IBackendServiceImpl implements IBackendService {
	
	
	
	@Override
	public Result getByOne(long id) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<Result> getItems(int start, int end) {
		List<Result> products = new Vector<Result>();
		for(int i=start; i<end; i++){
			products.add(new Result());
		}
	
		
		return products;
	}

	@Override
	public int getSumAllProduct() {
		// TODO Auto-generated method stub
		return 20;
	}

	@Override
	public Map<String, Alert> getAlerts() {
		return ((VaadinUI) UI.getCurrent()).getAlerts();
	}

	@Override
	public void createAlert(String key, Alert alert) {
		((VaadinUI) UI.getCurrent()).insertIntoAlert(key, alert);
	}

	@Override
	public Alert getAlertByText(String text) {
		Map<String, Alert> alerts = ((VaadinUI) UI.getCurrent()).getAlerts();
		Alert a = alerts.get(text);
		return a;
	}
	

}
