$(document).on('click', 'div .dropdown-menu', function(e) {
	e.stopPropagation();
});

function showNotif() {	
	if( $("#notifList").is(":visible") )
		$("#notifList").hide();
	else
		$("#notifList").show();
}

function delNotif(notifID) {
	
	let id = "#" + notifID;
	$(id).remove();
	$(id + "-divider").remove();
}

function getDefaultHeaders() {
	var headers = new Headers();
	headers.append("Content-Type", "application/json");
	return headers;
}

fetch("/api/profile/me", {
	method : 'GET',
	headers : getDefaultHeaders(),
	credentials : "same-origin"
}).then(function(response) {
	return response.json();
}).then(function(myProfile) {

	let query = " /api/event/count?ownerID=" + myProfile.uuid;
	fetch(query, {
		method : 'GET',
		headers : getDefaultHeaders(),
		credentials : "same-origin"
	}).then(function(response) {
		return response.json();
	}).then(function(userEventCounter) {
		$("#newNotificationCounter").html("<i class='fas fa-envelope'></i> " + userEventCounter);
		if (userEventCounter > 0)
			$('#newNotificationCounter').addClass('bg-danger');
		$('#newNotificationCounter').css('visibility', 'visible');
	});

});