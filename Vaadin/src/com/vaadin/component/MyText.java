package com.vaadin.component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;

import com.vaadin.shared.ui.JavaScriptComponentState;
import com.vaadin.ui.AbstractJavaScriptComponent;
import com.vaadin.ui.JavaScriptFunction;
@com.vaadin.annotations.JavaScript({"https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js", "mytext.js" })
public class MyText extends AbstractJavaScriptComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7244618069127354134L;
	public interface OnSelectListener{
		public void onSelect(String text);
	}
	private List<OnSelectListener> onSelectListenerList = new ArrayList<OnSelectListener>();
	public MyText(){
		addFunction();
	}
	public MyText(String text){
		setCaption(text);
		addFunction();
	}
	
	public void addSelectListener(OnSelectListener selectListener){
		onSelectListenerList.add(selectListener);
	}
	
	

	@Override
	public void setCaption(String caption) {
		getState().selectText = caption;
	}

	@Override
	protected MyTextState getState() {
		// TODO Auto-generated method stub
		return (MyTextState) super.getState();
	}
	
	private void addFunction(){
		addFunction("onTextSelected", new JavaScriptFunction() {
					
					/**
					 * 
					 */
					private static final long serialVersionUID = 2587930991130481395L;
		
					@Override
					public void call(JSONArray arguments) throws JSONException {
						String text = (String) arguments.get(0);
						for(OnSelectListener selectListener : onSelectListenerList){
							selectListener.onSelect(text);
						}
		
					}
			});
	}

	
	

}
