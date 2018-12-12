
function showNotif() {
	if ($("#notifList").is(":visible"))
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
				if (notifications != null) {
					$("#bellIcon").removeClass('text-white');
					$("#bellIcon").addClass('text-warning');
					notifications.forEach(function(notification, index, arr) {
						$("#notifItem").tmpl(notification).appendTo(
								"#notificationWindow"); // Добавляем
						// шаблон
						// тега
						// в
						// контейнер
						// с
						// id=tag_scope

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
			me = myProfile;
			UserID = myProfile.uuid;
			reloadEvents(myProfile.uuid);

			stompClient = Stomp.over(socket);
			stompClient.connect({}, function(frame) {
				stompClient.subscribe('/topic/notifications/' + me.uuid,
						function(greeting) {
							alert(JSON.parse(greeting.body).message);
							//reloadEvents(UserID);
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

function showRef(ref) {
	alert(ref);
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
