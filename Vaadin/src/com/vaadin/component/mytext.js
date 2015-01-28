com_vaadin_component_MyText = function() {
	var e = this.getElement();
	var state = this.getState();
	var text = "<span style='padding:10px;'>"+state.selectText+"</span>";
	e.innerHTML = text;
	var connector = this;
	this.onStateChange = function(){
		
	};
	
	
	e.onmouseup = function(s){
//			   var selectText = "";
//				if (window.getSelection) {
//					selectText = window.getSelection().toString();
//			    } else if (document.selection && document.selection.type != "Control") {
//			    	selectText = document.selection.createRange().text;
//			    }
//				connector.onTextSelected(selectText);
				e.innerHTML = text;
				
};

	e.onmousedown = function(s){
		   var posX = s.clientX;
		   var posY = s.clientY;
		   
		   var top = (posY - 15) + 'px';
		   var left = (posX - 15) + 'px';
		   
		   var bell = "<div id='bell' class='fa fa-bell' style='position:fixed;top:"+top+";left:"+left+";color:#e74c3c;" +
		   		"			-webkit-touch-callout: none;" +
		   		"			-webkit-user-select: none;" +
		   		"			-khtml-user-select: none;" +
		   		"			-moz-user-select: none;" +
		   		"			-ms-user-select: none;" +
		   		"			user-select: none;'></div>";
		   e.innerHTML = text + bell;
		  
			
	};
}