/* close the create new user page*/
 	$(document).ready(function(){
 		$("#buttonCancel").on("click", function(){
		/*
			NB this is a static url referece in javascript
			use this type of code when you write your js in the 
			same page
			
			window.location="[[@{/users}]]";
		*/
		
		// Dynamic url reference
		window.location = moduleURL; 
 			
 		});
 		});
 		

 	
 	/* function to show modal dialog with warning message*/
 	function showModalDialog(title,message){
 	$("#modalTitle").text(title);
 	$("#modalBody").text(message);
 	$("#modalDialog").modal();
 	}
 	
 	
 	/*functon for showing error message of the modal dialog*/
 	function showErrorModal(message){
 	showModalDialog("Error", message);
 	}
 	
 	/*functon for showing warning message of the modal dialog*/
 	function showWarningModal(message){
 	showModalDialog("Warning", message);
 	}