com_vaadin_component_MyText = function() {
	var e = this.getElement();
	var parent = e.parentElement;
	var state = this.getState();
	var text = "<xmp>"+state.selectText+"</xmp>";
	e.innerHTML = text;
	var connector = this;
	this.onStateChange = function(){
		
	};
	
	
	e.onmouseup = function(s){
		 var selectText = "";
		 var as = e;
		 var clientRect = as.getBoundingClientRect();
			if (window.getSelection) {
				selectText = window.getSelection().toString();
				//window.getSelection().removeAllRanges();					
		    } else if (document.selection && document.selection.type != "Control") {
		    	selectText = document.selection.createRange().text;
		    }
			connector.sendSelectedText(selectText);
		
		   //e.innerHTML = text;
//		   var posX = s.layerX;
//		   var posY = s.layerY;
		   var posY = clientRect.height ;
		   var posX = s.layerX;
		   
		   var top = (posY)-60 + 'px';
		   var left = (posX) + 'px';
		   
		   //var b = document.createTextNode("<div onclick=saveAlert(); class='fa fa-bell' style='position:fixed;top:"+top+";left:"+left+";color:#e74c3c;z-index:10;'></div>");
		   var b = document.createElement("DIV");
//		   var currentRange = window.getSelection().getRangeAt(0);
//		   var startNode = currentRange.startContainer;
//		   var endNode = currentRange.endContainer;
		   button = b;
		   b.style.cssText = "position:absolute;top:"+top+";left:"+left+";color:#e74c3c;z-index:10;";
		   b.classList.add("fa");
		   b.classList.add("fa-bell");
		  //var text = window.getSelection();
		   b.addEventListener("click", function(event){
			   
				   var selectText = "";
					if (window.getSelection) {
						selectText = state.sss;
				    } else if (document.selection && document.selection.type != "Control") {
				    	selectText = document.selection.createRange().text;
				    }
					connector.onTextSelected(selectText);
					e.innerHTML = text;
					
			   });
		   //var bell = ;
		   if(selectText != "")
			   e.appendChild(b);
			   
		   $(b).delay(4000).fadeOut('slow', function(){
			   e.removeChild(b);
		   });
				
};

//	e.onmousedown = function(s){
//		   var posX = s.clientX;
//		   var posY = s.clientY;
//		   
//		   var top = (posY - 25) + 'px';
//		   var left = (posX - 15) + 'px';
//		   
//		   var bell = "<div class='fa fa-bell' style='position:fixed;top:"+top+";left:"+left+";color:#e74c3c></div>";
//		   
//		  // e.innerHTML = text + bell;
//		
//		  
//			
//	};
	
	
}