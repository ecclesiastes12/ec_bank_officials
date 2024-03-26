function clearFilter(){
	window.location = "[[@{/bankdetails}]";
}


function showDeleteConfirmModal(link){
	entityId = link.attr("entityId");
	fullName = link.attr("fullName");
	districtName = link.attr("districtName")
	$("#yesButton").attr("href", link.attr("href"));
	$("#confirmText").text("Are you sure you want to delete the district " + districtName + " with ID " + entityId + " of " + fullName + " ?");

	$("#confirmModal").modal("show");

}

function showDeleteConfirmModal(link, entityName){
	entityId = link.attr("entityId");
	fullName = link.attr("fullName");
	$("#yesButton").attr("href", link.attr("href"));
	$("#confirmText").text("Are you sure you want to delete the "+ entityName + " "+ fullName + " with ID " + entityId + " ?");

	$("#confirmModal").modal("show");

}

//function showDeleteConfirmModal(link, entityName){
//	entityId = link.attr("entityId");
//	$("#yesButton").attr("href", link.attr("href"));
//	$("#confirmText").text("Are you sure you want to delete " + entityName + " ID " + entityId + " ?");
//
//	$("#confirmModal").modal();
//}

/*
 function showDeleteConfirmModal2(link, entityName, customerFullName){
	entityId = link.attr("entityId");
	$("#yesButton").attr("href", link.attr("href"));
	$("#confirmText").text("Are you sure you want to delete  this" + entityName + " ID " + entityId + ": " + customerFullName + " ?");

	$("#confirmModal").modal();
}
*/


//logout function
function logout(logoutId){
	//$("#logoutLink").on("click",function(e){
		$( logoutId).on("click",function(e){
		e.preventDefault();
		document.logoutForm.submit();
	});
}