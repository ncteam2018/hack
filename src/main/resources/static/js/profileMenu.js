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

function updateNotifications() {
	
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

var stompClient = null;


var socket = new SockJS('/notificationStream');
stompClient = Stomp.over(socket);
stompClient.connect({}, function (frame) {
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/notifications', function (greeting) {
        alert('new message!');
        updateNotifications();
    });
});


function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}
