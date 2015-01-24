package com.vaadin.app.dao;

import java.util.List;
import java.util.Map;

import com.vaadin.app.model.Alert;
import com.vaadin.app.model.Result;

public interface IBackendService {
	
	
	public List<Result> getItems(int start, int end);
	public int getSumAllProduct();
	public Result getByOne(long id);
	public List<Alert> getAlerts();
	public void createAlert(Alert alert);
	public Alert getAlertByText(String text);
	public void deleteAlert(Alert a);
}
