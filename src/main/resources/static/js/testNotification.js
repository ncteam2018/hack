function getDefaultHeaders() {
	var headers = new Headers();
	headers.append("Content-Type", "application/json");
	return headers;
}

var counter = 1;
var hack;
var hack2;
function loadNotifications() {

	fetch("/api/profile/me", {
		method : 'GET',
		headers : getDefaultHeaders(),
		credentials : "same-origin"
	}).then(function(response) {
		return response.json();
	}).then(function(myProfile) {

		let query = " /api/event?eventType=1&ownerID=" + myProfile.uuid;
		fetch(query, {
			method : 'GET',
			headers : getDefaultHeaders(),
			credentials : "same-origin"
		}).then(function(response) {
			return response.json();
		}).then(function(userEvents) {

			userEvents.forEach(function(eventList, index, arr) {
				eventList.forEach(function(event, index, arr) {
					hack2 = event.resourceHackReference;
					
					if(hack == null)
						hack = event.resourceHackReference;
					
					let eventObj = {
						"number" : counter,
						"opt" : counter++,
						"type" : event.type,
						"status" : event.status,
						"content" : event.resourceHackReference.uuid,
						"sender" : event.sender.login,
						"receiver" : event.receiver.login,
						"message" : event.message,
						"creationDate" : event.dateOfCreation,
						"updateDate" : event.dateOfUpdate,
						"resourceName" : event.resourceHackReference.title,
						"eventID" : event.id,
						"eventType" : event.type
					};

					$("#event").tmpl(eventObj).appendTo("#events");
				})
			})
		});

	});

}

function processEvent(eventStatus, opt) {

	/*
	 * let query = " /api/event?eventID=" + eventID + "&eventStatus=" +
	 * newStatus; fetch(query, { method : 'PUT', headers : getDefaultHeaders(),
	 * credentials : "same-origin" }).then(function(response) { return
	 * response.json(); }).then(function(userEvents) {
	 * 
	 * });
	 */
	
	
	var hackRef;
	
	if(opt==1)
		hackRef = hack;
	else
		hackRef = hack2;
	
	if (eventStatus == 1) {
		hackRef.status = "Active";
		let query = " /api/hack/" + hackRef.uuid;

		fetch(query, {
			method : 'PUT',
			headers : getDefaultHeaders(),
			body : JSON.stringify(hackRef),
			credentials : "same-origin"
		});

	}
	if (eventStatus == 3) {
		hackRef.status = "Canceled";
		let query = " /api/hack/" + hackRef.uuid;

		fetch(query, {
			method : 'PUT',
			headers : getDefaultHeaders(),
			body : JSON.stringify(hackRef),
			credentials : "same-origin"
		});
	}

}
