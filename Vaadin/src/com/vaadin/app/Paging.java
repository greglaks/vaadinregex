package com.vaadin.app;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class Paging extends HorizontalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5729148447452003588L;
	private int pageIndex=1;
	private int maxItemsPerPage;
	private int start;
	private int end;
	private int buttonIndex = 2;
	private CssLayout pageLayout;
	private IPageLayoutOperation pageLayoutOperation;
	private List<Button> buttonList = new LinkedList<Button>(); 
	private Button firstButton = new Button("<First>");
	private Button prevButton = new Button("<Prev>");
	private Button moreButton = new Button("<More>");
	
	private ClickListener prevButtonListener = new ClickListener() {
		
		@Override
		public void buttonClick(ClickEvent event) {
			pageIndex--;
			if(pageIndex <= 0)
				pageIndex = 1;
			start = ((pageIndex * maxItemsPerPage) - maxItemsPerPage) + 1;
			end = (pageIndex * maxItemsPerPage);
			refresh();
		}
	};
	
	private ClickListener firstButtonListener = new ClickListener() {
		
		@Override
		public void buttonClick(ClickEvent event) {
			pageIndex = 1;
			start = 1;
			end = maxItemsPerPage;
			buttonList.clear();
			refresh();
			
		}
	};
	
	
	private ClickListener moreButtonListener = new ClickListener() {
		

		@Override
		public void buttonClick(ClickEvent event) {
			int index = ++pageIndex;
			start =( (pageIndex * maxItemsPerPage) - maxItemsPerPage ) + 1;
			end = (pageIndex * maxItemsPerPage);
			List l = pageLayoutOperation.getResults(start, end);
			int size = l.size();
			if(size > 0){
				Button button = new Button(String.valueOf(pageIndex));
				IndexButtonClickListener indexButtonListener = new IndexButtonClickListener(index);
				button.addClickListener(indexButtonListener);
				button.addStyleName("link");
				buttonList.add(button);
				initButtons();
			}
			
		}
	};

	public Paging(int pageItems,  CssLayout pageLayout, IPageLayoutOperation pageLayoutOperation){
		this.maxItemsPerPage = pageItems;
		this.pageLayout = pageLayout;
		this.pageLayoutOperation = pageLayoutOperation;
		
		firstButton.addStyleName("link");
		prevButton.addStyleName("link");
		moreButton.addStyleName("link");
		
		firstButton.addClickListener(firstButtonListener);
		prevButton.addClickListener(prevButtonListener);
		moreButton.addClickListener(moreButtonListener);
		setMargin(true);
		setSpacing(true);
		initIndexButton();
		initButtons();
	}

	private void initButtons() {
		removeAllComponents();
		addComponent(firstButton);
		addComponent(prevButton);
		for(Button b: buttonList){
			addComponent(b);
		}
		
		addComponent(moreButton);
	}

	private void initIndexButton() {
		if(pageIndex == 1){
			start = 1;
			end = maxItemsPerPage;
		}
		insertItem();
		
	}
	
	private void refresh(){
		pageLayout.removeAllComponents();
		insertItem();
	}

	private void insertItem() {
		List list = pageLayoutOperation.getResults(start, end);
		int index = 1;
		Iterator iter = list.iterator();
//		for(int i = 1; i<maxItemsPerPage; i++){
//			int indexpos = i - 1;
//			Object o = list.get(indexpos);
//			Component component = pageLayoutOperation.getItem(i, o);
//			pageLayout.addComponent(component);
//		}
		int cIndex = start;
		while(iter.hasNext()){
			Object o = iter.next();
			Component c = pageLayoutOperation.getItem(cIndex, o);
			cIndex++;
			pageLayout.addComponent(c);
			
		}
		
		
		
	}
	
	private class IndexButtonClickListener implements ClickListener{
		
		private int index;

		public IndexButtonClickListener(int index){
			this.index = index;
			
			
		}

		@Override
		public void buttonClick(ClickEvent event) {
			pageIndex = index;
			start =( (index * maxItemsPerPage) - maxItemsPerPage ) + 1;
			end = (index * maxItemsPerPage);
			refresh();
		}
		
	}
}
