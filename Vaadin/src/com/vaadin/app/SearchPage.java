package com.vaadin.app;

import java.util.Collection;
import java.util.List;

import org.vaadin.pagingcomponent.PagingComponent;
import org.vaadin.pagingcomponent.listener.impl.LazyPagingComponentListener;
import org.vaadin.pagingcomponent.utilities.FakeList;

import com.vaadin.app.dao.IBackendService;
import com.vaadin.app.dao.IBackendServiceImpl;
import com.vaadin.app.model.Result;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;

public class SearchPage extends CustomLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CssLayout content = new CssLayout();
	
	private static final int ITEM_NUMBER_PER_PAGE = 5;
	private static final int BUTTON_NUMBER_PAGE = 3;
	
	
	public SearchPage(){
		super("searchpage");
		addStyleName("main");
		createContents();
	}

	private void createContents() {
		DateField field1 = new DateField("Date 1:");
		DateField field2 = new DateField("Field 2:");
		TextField field3 = new TextField("Field 3:");
		int resultSum = new IBackendServiceImpl().getSumAllProduct();
		List<Result> fakeList = new FakeList<>(resultSum);
		PagingComponent<Result> pagingComponent = new PagingComponent<>(ITEM_NUMBER_PER_PAGE, BUTTON_NUMBER_PAGE, fakeList, new LazyPagingComponentListener<Result>(content) {

				/**
				 * 
				 */
				private static final long serialVersionUID = 9165964548422519578L;

				@Override
				protected Collection<Result> getItemsList(int startIndex, int endIndex) {
					IBackendService productDao = new IBackendServiceImpl();
					
					return productDao.getItems(startIndex, endIndex);
				}

				@Override
				protected Component displayItem(int index, Result item) {
					ResultItem resultItem = new ResultItem(item, index);
					return resultItem;
				}
			});
		
		addComponent(field1, "field1");
		addComponent(field2, "field2");
		addComponent(field3, "field3");
		
		Button searchButton = new Button("Search");
		searchButton.addStyleName("distancetop");
		addComponent(searchButton, "search");
		
		Button clearButton= new Button("Clear");
		clearButton.addStyleName("distancetop");
		addComponent(clearButton, "clear");
		
		searchButton.setPrimaryStyleName("defaultbutton");
		clearButton.setPrimaryStyleName("defaultbutton");
		
		addComponent(content, "result");
		addComponent(pagingComponent, "paging");
		
	}

	@Override
	public void enter(ViewChangeEvent event) {
		
	}
}
