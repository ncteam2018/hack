function showNotif() {
	if ($("#notifList").is(":visible"))
		$("#notifList").hide();
	else
		$("#notifList").show();
}

function isEmpty(el) {
	return !$.trim(el.html())
}

function delNotif(notifID) {

	let id = "#" + notifID;
	$(id).remove();
	$(id + "-divider").remove();

	if (isEmpty($("#notificationWindow"))) {
		$("#bellIcon").addClass('text-white');
		$("#bellIcon").removeClass('text-warning');
		$("#bellSymbol").css("display", "none");
	}
}

function getDefaultHeaders() {
	var headers = new Headers();
	headers.append("Content-Type", "application/json");
	return headers;
}

function reloadEvents(userID) {

	let query = " /api/event/count?ownerID=" + userID;
	fetch(query, {
		method : 'GET',
		headers : getDefaultHeaders(),
		credentials : "same-origin"
	}).then(function(response) {
		return response.json();
	}).then(
			function(userEventCounter) {
				$("#newNotificationCounter").html("");
				$("#newNotificationCounter").html(
						"<i class='fas fa-envelope'></i> " + userEventCounter);
				if (userEventCounter > 0)
					$('#newNotificationCounter').addClass('bg-danger');
				$('#newNotificationCounter').css('visibility', 'visible');
			});

	query = " /api/event/notifications?ownerID=" + userID;
	fetch(query, {
		method : 'GET',
		headers : getDefaultHeaders(),
		credentials : "same-origin"
	}).then(function(response) {
		return response.json();
	}).then(
			function(userEventCounter) {
				notifications = userEventCounter;
				$("#notificationWindow").html("");
				if (notifications.length != 0) {
					$("#bellIcon").removeClass('text-white');
					$("#bellIcon").addClass('text-warning');
					$("#bellSymbol").css("display", "inline");
					notifications.forEach(function(notification, index, arr) {
						$("#notifItem").tmpl(notification).appendTo(
								"#notificationWindow");
						$("#" + notification.id).html(notification.message);
					})
				}
			});
}

var notifications;
var UserID;
var Me;
var stompClient = null;
var socket = new SockJS('/notificationStream');

fetch("/api/profile/me", {
	method : 'GET',
	headers : getDefaultHeaders(),
	credentials : "same-origin"
}).then(function(response) {
	return response.json();
}).then(
		function(myProfile) {
			Me = myProfile;
			UserID = myProfile.uuid;
			reloadEvents(myProfile.uuid);

			stompClient = Stomp.over(socket);
			stompClient.connect({}, function(frame) {
				stompClient.subscribe('/topic/notifications/' + Me.uuid,
						function(greeting) {
							$("#bellIcon").removeClass('text-white');
							$("#bellIcon").addClass('text-warning');
							$("#bellSymbol").css("display", "inline");
							let notif = JSON.parse(greeting.body);
							$("#notifItem").tmpl(notif).appendTo(
									"#notificationWindow");

							$("#" + notif.id).html(notif.message);
						});
			});

		});

function disconnect() {
	if (stompClient !== null) {
		stompClient.disconnect();
	}
	setConnected(false);
	console.log("Disconnected");
}


function updateNotification(notifID) {
	delNotif(notifID);

	let query = " /api/event/" + notifID;
	fetch(query, {
		method : 'PUT',
		headers : getDefaultHeaders(),
		body : 3,
		credentials : "same-origin"
	});

}
