function getDefaultHeaders() {
	var headers = new Headers();
	headers.append("Content-Type", "application/json");
	return headers;
}

$(document).ready(function() {
	$("#form1").keydown(function(event) {
		if (event.keyCode == 13) {
			event.preventDefault();
			return false;
		}
	});
});

$("#notifications").addClass('text-warning');

var reqSend = 0;
var reqRec = 0;
var invSend = 0;
var invRec = 0;
var messageSend = 0;
var messageRec = 0;

var myEvents;
function loadMyEvents() {

	fetch("/api/profile/me", {
		method : 'GET',
		headers : getDefaultHeaders(),
		credentials : "same-origin"
	})
			.then(function(response) {
				return response.json();
			})
			.then(
					function(myProfile) {

						let query = "/api/event/" + myProfile.uuid
								+ "/myEvents";

						fetch(query, {
							method : 'GET',
							headers : getDefaultHeaders(),
							credentials : "same-origin"
						})
								.then(function(response) {
									return response.json();
								})
								.then(
										function(allMyEvents) {
											myEvents = allMyEvents;

											$("#loadingIcon1").css("display",
													"none");
											$("#loadingIcon2").css("display",
													"none");

											myEvents[0]
													.forEach(function(event,
															index, arr) {
														$("#requestItemSend")
																.tmpl(event)
																.appendTo(
																		"#myRequestsListSended");
													});

											myEvents[1]
													.forEach(function(event,
															index, arr) {
														$("#requestItemRec")
																.tmpl(event)
																.appendTo(
																		"#myRequestsListReceived");
													});

											reqSend = myEvents[0].length;
											reqRec = myEvents[1].length;

											invSend = myEvents[2].length;
											invRec = myEvents[3].length;

											myEvents[2]
													.forEach(function(event,
															index, arr) {
														if (event.team.captain.uuid != event.sender.uuid) {
															$(
																	"#inviteItemSendReq")
																	.tmpl(event)
																	.appendTo(
																			"#myRequestsListSended");
															reqSend = reqSend + 1;
															invSend = invSend - 1;
														} else
															$("#inviteItemSend")
																	.tmpl(event)
																	.appendTo(
																			"#myInvitesListSended");
													});

											myEvents[3]
													.forEach(function(event,
															index, arr) {
														if (event.team.captain.uuid == event.receiver.uuid) {
															$(
																	"#inviteItemRecReq")
																	.tmpl(event)
																	.appendTo(
																			"#myRequestsListReceived");
															reqRec = reqRec + 1;
															invRec = invRec - 1;
														} else
															$("#inviteItemRec")
																	.tmpl(event)
																	.appendTo(
																			"#myInvitesListReceived");
													});

											messageSend = myEvents[4].length;
											messageRec = myEvents[5].length;

											myEvents[4]
													.forEach(function(event,
															index, arr) {
														$("#messageItemSend")
																.tmpl(event)
																.appendTo(
																		"#myMessagesListSended");
													});

											myEvents[5]
													.forEach(function(event,
															index, arr) {
														$("#messageItemRec")
																.tmpl(event)
																.appendTo(
																		"#myMessagesListReceived");
													});

											$("#inviteCounter").html(
													invSend + " / " + invRec);
											$("#requestCounter").html(
													reqSend + " / " + reqRec);
											$("#messageCounter").html(
													messageSend + " / "
															+ messageRec);

										});
					});

}

function showMyInvites() {
	$('#myInvitesListSended').css('display', 'block');
	$('#myInvitesListReceived').css('display', 'block');

	$('#myMessagesListSended').css('display', 'none');
	$('#myMessagesListReceived').css('display', 'none');

	$('#myRequestsListSended').css('display', 'none');
	$('#myRequestsListReceived').css('display', 'none');

	$('#myInvites').removeClass('text-light').addClass('text-warning');
	$('#myMessages').removeClass('text-warning').addClass('text-light');
	$('#myRequests').removeClass('text-warning').addClass('text-light');
}

function showMyMessages() {
	$('#myInvitesListSended').css('display', 'none');
	$('#myInvitesListReceived').css('display', 'none');

	$('#myMessagesListSended').css('display', 'block');
	$('#myMessagesListReceived').css('display', 'block');

	$('#myRequestsListSended').css('display', 'none');
	$('#myRequestsListReceived').css('display', 'none');

	$('#myInvites').removeClass('text-warning').addClass('text-light');
	$('#myMessages').removeClass('text-light').addClass('text-warning');
	$('#myRequests').removeClass('text-warning').addClass('text-light');
}

function showAdminRequests() {
	$('#myInvitesListSended').css('display', 'none');
	$('#myInvitesListReceived').css('display', 'none');

	$('#myMessagesListSended').css('display', 'none');
	$('#myMessagesListReceived').css('display', 'none');

	$('#myRequestsListSended').css('display', 'block');
	$('#myRequestsListReceived').css('display', 'block');

	$('#myInvites').removeClass('text-warning').addClass('text-light');
	$('#myMessages').removeClass('text-warning').addClass('text-light');
	$('#myRequests').removeClass('text-light').addClass('text-warning');
}

function deleteMessage(mailID, eventID) {

	let query = "/api/event/" + mailID;

	fetch(query, {
		method : 'DELETE',
		headers : getDefaultHeaders(),
		credentials : "same-origin"
	}).then(function() {
		$("#event_" + eventID).remove();
	});

	return false;
}

function deleteInvite(inviteID, eventID) {

	let query = "/api/event/" + inviteID;

	fetch(query, {
		method : 'DELETE',
		headers : getDefaultHeaders(),
		credentials : "same-origin"
	}).then(function() {
		$("#event_" + eventID).remove();
	});

	return false;
}

function changeInviteStatus(teamID, userID, inviteID, newStatus, eventID) {

	if (newStatus == 1) {
		let query = "/api/team/" + teamID + "/addUser";

		fetch(query, {
			method : 'POST',
			headers : getDefaultHeaders(),
			body : JSON.stringify(userID),
			credentials : "same-origin"
		}).then(function(response) {

			let query = "/api/event/" + inviteID;

			fetch(query, {
				method : 'PUT',
				headers : getDefaultHeaders(),
				body : JSON.stringify(newStatus),
				credentials : "same-origin"
			}).then(function() {
				$("#event_" + eventID).remove();
			});

		});
	}else{
		
		let query = "/api/event/sendNotification?message=Вас не приняли в команду!&teamID=" + teamID + "&receiver=" + userID;
		fetch(query, {
			method : 'POST',
			headers : getDefaultHeaders(),
			credentials : "same-origin"
		});
		
		query = "/api/event/" + inviteID;
		fetch(query, {
			method : 'PUT',
			headers : getDefaultHeaders(),
			body : JSON.stringify(newStatus),
			credentials : "same-origin"
		}).then(function() {
			$("#event_" + eventID).remove();
		});
		
	}

	return false;
}


function deleteReq(reqID, eventID) {

	let query = "/api/event/" + reqID;

	fetch(query, {
		method : 'DELETE',
		headers : getDefaultHeaders(),
		credentials : "same-origin"
	}).then(function() {
		$("#event_" + eventID).remove();
	});

	return false;
}

function changeRequestStatus(hackID, userID, requestID, newStatus, eventID) {

	if (newStatus == 1) {
		
		let query = "/api/hack/" + hackID + "/status";
		fetch(query, {
			method : 'PUT',
			headers : getDefaultHeaders(),
			body : JSON.stringify('Active'),
			credentials : "same-origin"
		}).then(function(response) {

			let query = "/api/event/" + requestID;
			fetch(query, {
				method : 'PUT',
				headers : getDefaultHeaders(),
				body : JSON.stringify(newStatus),
				credentials : "same-origin"
			}).then(function() {
				$("#event_" + eventID).remove();
			});

		});
		
		query = "/api/event/sendNotification?message=Создание хакатона подтверждено!&hackID=" + hackID + "&receiver=" + userID;
		fetch(query, {
			method : 'POST',
			headers : getDefaultHeaders(),
			credentials : "same-origin"
		});
		
	}else{
		
		let query = "/api/hack/" + hackID + "/status";
		fetch(query, {
			method : 'PUT',
			headers : getDefaultHeaders(),
			body : JSON.stringify('Canceled'),
			credentials : "same-origin"
		}).then(function(response) {

			let query = "/api/event/" + requestID;
			fetch(query, {
				method : 'PUT',
				headers : getDefaultHeaders(),
				body : JSON.stringify(newStatus),
				credentials : "same-origin"
			}).then(function() {
				$("#event_" + eventID).remove();
			});

		});
			
		query = "/api/event/sendNotification?message=Создание хакатона отклонено!&hackID=" + hackID + "&receiver=" + userID;
		fetch(query, {
			method : 'POST',
			headers : getDefaultHeaders(),
			credentials : "same-origin"
		});	
	}

	return false;
}




function showMessageWindow_(name, userID, eventID) {

	$("#messageReceiver").html(name);
	$("#sendTo")
			.attr("onclick", 'return sendMessageToUser__("' + userID + '")');

	$("#message_").modal("show");
	return false;
}

function sendMessageToUser__(userID) {

	let Message_ = new function() {
		this.sender = Me.uuid;
		this.receiver = userID;
		this.message = $("#messageTextarea_").val();
	}

	let query = '/api/profile/sendMessage';
	fetch(query, {
		method : 'POST',
		headers : getDefaultHeaders(),
		body : JSON.stringify(Message_),
		credentials : "same-origin"
	});

	$("#message_").modal("hide");
	$("#messageSendAlert_").modal("show");

	return false;
}

$("#inviteCounter").html(invSend + " / " + invRec);
$("#requestCounter").html(reqSend + " / " + reqRec);
$("#messageCounter").html(messageSend + " / " + messageRec);

function showNewEvent(event) {

	if (event.type.id == 1)
		if (event.sender.uuid == Me.uuid) {
			$("#requestItemSend").tmpl(event).appendTo("#myRequestsListSended");
			reqSend = reqSend + 1;
			$("#requestCounter").html(reqSend + " / " + reqRec);
		} else {
			$("#requestItemRec").tmpl(event)
					.appendTo("#myRequestsListReceived");
			reqRec = reqRec + 1;
			$("#requestCounter").html(reqSend + " / " + reqRec);

		}
	else if (event.type.id == 3)
		if (event.sender.uuid == Me.uuid) {
			if (event.team.captain.uuid != event.sender.uuid) {
				$("#inviteItemSendReq").tmpl(event).appendTo(
						"#myRequestsListSended");
				reqSend = reqSend + 1;
				$("#requestCounter").html(reqSend + " / " + reqRec);
			} else {
				$("#inviteItemSend").tmpl(event).appendTo(
						"#myInvitesListSended");
				invSend = invSend + 1;
				$("#inviteCounter").html(invSend + " / " + invRec);
			}
		} else {
			if (event.team.captain.uuid == event.receiver.uuid) {
				$("#inviteItemRecReq").tmpl(event).appendTo(
						"#myRequestsListReceived");
				reqRec = reqRec + 1;
				$("#requestCounter").html(reqSend + " / " + reqRec);
			} else {
				$("#inviteItemRec").tmpl(event).appendTo(
						"#myInvitesListReceived");
				invRec = invRec + 1;
				$("#inviteCounter").html(invSend + " / " + invRec)
			}
		}
	else if (event.type.id == 4)
		if (event.sender.uuid == Me.uuid) {
			$("#messageItemSend").tmpl(event).appendTo("#myMessagesListSended");
			messageSend = messageSend + 1;
			$("#messageCounter").html(messageSend + " / " + messageRec);
		} else {
			$("#messageItemRec").tmpl(event)
					.appendTo("#myMessagesListReceived");
			messageRec = messageRec + 1;
			$("#messageCounter").html(messageSend + " / " + messageRec);
		}
}

var eventSocketHandler = null;
var eventSocket = new SockJS('/eventStream');

fetch("/api/profile/me", {
	method : 'GET',
	headers : getDefaultHeaders(),
	credentials : "same-origin"
}).then(function(response) {
	return response.json();
}).then(
		function(myProfile) {

			eventSocketHandler = Stomp.over(eventSocket);
			eventSocketHandler.connect({}, function(frame) {
				stompClient.subscribe('/topic/events/' + myProfile.uuid,
						function(greeting) {
							let event = JSON.parse(greeting.body);
							showNewEvent(event);
						});
			});

		});
