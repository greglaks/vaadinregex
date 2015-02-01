com_vaadin_component_MyText = function() {
	var e = this.getElement();
	var parent = e.parentElement;
	var state = this.getState();
	var button = null;
	var text = "<xmp>"+state.selectText+"</xmp>";
	e.innerHTML = text;
	var connector = this;
	this.onStateChange = function(){
		
	};
	
	
	e.onmouseup = function(s){
			e.innerHTML = text;
		   var posX = s.clientX;
		   var posY = s.clientY;
		   
		   var top = (posY - 15) + 'px';
		   var left = (posX + 15) + 'px';
		   
		   //var b = document.createTextNode("<div onclick=saveAlert(); class='fa fa-bell' style='position:fixed;top:"+top+";left:"+left+";color:#e74c3c;z-index:10;'></div>");
		   var b = document.createElement("DIV");
		   b.id = "bellbutton";
		   button = b;
		   b.style.cssText = "position:fixed;top:"+top+";left:"+left+";color:#e74c3c;z-index:10;";
		   b.classList.add("fa");
		   b.classList.add("fa-bell");
		   b.addEventListener("click", function(){ 
				   var selectText = "";
					if (window.getSelection) {
						selectText = window.getSelection().toString();
				    } else if (document.selection && document.selection.type != "Control") {
				    	selectText = document.selection.createRange().text;
				    }
					connector.onTextSelected(selectText);
					parent.removeChild(b);	
			   });
		   //var bell = ;
		   parent.appendChild(b);
		   $(b).delay(1000).fadeOut('slow');
				
};

	e.onmousedown = function(s){
		   var posX = s.clientX;
		   var posY = s.clientY;
		   
		   var top = (posY - 15) + 'px';
		   var left = (posX - 15) + 'px';
		   
		   var bell = "<div id='bell' class='fa fa-bell' style='position:fixed;top:"+top+";left:"+left+";color:#e74c3c'></div>";
		   e.innerHTML = text + bell;
		  
			
	};
	function saveAlert(){
		var selectText = "";
		if (window.getSelection) {
			selectText = window.getSelection().toString();
	    } else if (document.selection && document.selection.type != "Control") {
	    	selectText = document.selection.createRange().text;
	    }
		connector.onTextSelected(selectText);
		e.innerHTML = text;		
	}
	
}