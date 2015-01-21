package com.vaadin.app.dao;

import java.util.List;
import java.util.Map;

import com.vaadin.app.model.Alert;
import com.vaadin.app.model.Result;

public interface IBackendService {
	
	
	public List<Result> getItems(int start, int end);
	public int getSumAllProduct();
	public Result getByOne(long id);
	public Map<String, Alert> getAlerts();
	public void createAlert(String key, Alert alert);
	public Alert getAlertByText(String text);
}
