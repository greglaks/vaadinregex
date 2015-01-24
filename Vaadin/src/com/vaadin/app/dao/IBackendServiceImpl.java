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
		List<Result> results = new Vector<Result>();
		for(int i=start; i<end; i++){
			results.add(new Result());
		}
	
		
		return results;
	}

	@Override
	public int getSumAllProduct() {
		// TODO Auto-generated method stub
		return 40;
	}

	@Override
	public List<Alert> getAlerts() {
		return ((VaadinUI) UI.getCurrent()).getAlerts();
	}

	@Override
	public void createAlert(Alert alert) {
		((VaadinUI) UI.getCurrent()).addAlert(alert);
	}

	@Override
	public Alert getAlertByText(String text) {

		return null;
	}



	@Override
	public void deleteAlert(Alert a) {
		((VaadinUI)UI.getCurrent()).deleteAlert(a);
		
	}
	

}
