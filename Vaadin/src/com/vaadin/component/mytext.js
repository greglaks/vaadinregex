com_vaadin_component_MyText = function() {
	var e = this.getElement();
	var state = this.getState();
	e.innerHTML = state.selectText;
	var connector = this;
	this.onStateChange = function(){
		
	};
	
	
	e.onmouseup = function(){
			   var selectText = "";
				if (window.getSelection) {
					selectText = window.getSelection().toString();
			    } else if (document.selection && document.selection.type != "Control") {
			    	selectText = document.selection.createRange().text;
			    }
				connector.onTextSelected(selectText);
				
};
}