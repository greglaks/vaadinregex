package com.vaadin.app;

import java.util.List;

import com.vaadin.ui.Component;

public interface IPageLayoutOperation<E> {
	
	public List<E> getResults(int start, int end);
	
	public Component getItem(int index, E e);
}
