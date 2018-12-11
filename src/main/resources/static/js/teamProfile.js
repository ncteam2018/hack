function getDefaultHeaders() {
	var headers = new Headers();
	headers.append("Content-Type", "application/json");
	return headers;
}

var teamMembers;
var captainProfile;
var teamUUID;
var chatClient = null;
var chatSocket = new SockJS('/teamChat');

function loadTeamProfile() {

	let uuid = $("#teamUuid").html();
	let query = "api/team/" + uuid;

	fetch(query, {
		method : 'GET',
		headers : getDefaultHeaders(),
		credentials : "same-origin"
	})
			.then(function(response) {
				return response.json();

			})
			.then(
					function(teamProfile) {
						captainProfile = teamProfile.captain;
						teamMembers = teamProfile.teamMembers;
						teamUUID = teamProfile.uuid;

						$("#teamName").html(teamProfile.name);
						$("#creationDate").html(teamProfile.dateOPublishing);
						$("#hackName").html(teamProfile.hack.title);
						$("#aboutTeam").html(teamProfile.about);
						$("#memberCounter").html(teamProfile.peopleCount);

						teamProfile.skillTags
								.forEach(function(tag, index, arr) {
									$("#tag-info").tmpl(tag).appendTo(
											"#team-tags");
								});

						teamProfile.scopeTags
								.forEach(function(tag, index, arr) {
									$("#tag-info").tmpl(tag).appendTo(
											"#team-tags");
								});

						teamMembers
								.forEach(function(member, index, arr) {
									member.index = index;
									$("#memberAvatar").tmpl(member).appendTo(
											"#memberList");
									if (member.uuid == captainProfile.uuid) {
										$("#member_" + index).addClass(
												"border-warning");
										$("#member_" + index)
												.append(
														'<div><i class="fas fa-star text-warning"></i> Капитан</div>');
									}
								});

						loadTeamChat(teamUUID);
						connectTeamChatSocket(teamUUID);

					});

}

function connectTeamChatSocket(teamID) {

	chatClient = Stomp.over(chatSocket);
	chatClient.connect({}, function(frame) {
		chatClient.subscribe('/topic/team/' + teamID + '/chat', function(
				message) {
			message = JSON.parse(message.body);
			$("#chatMessage").tmpl(message).appendTo("#teamChat");
		});
	});

}

function loadTeamChat(teamID) {

	let query = "api/team/" + teamUUID + "/chat";
	fetch(query, {
		method : 'GET',
		headers : getDefaultHeaders(),
		credentials : "same-origin"
	}).then(function(response) {
		return response.json();

	}).then(function(teamChat) {

		teamChat.forEach(function(message, index, arr) {

			$("#chatMessage").tmpl(message).appendTo("#teamChat");
		});

	});

}

function showMemberProfile(index) {

	$("#fio").html(
			teamMembers[index].lastName + " " + teamMembers[index].firstName
					+ " " + teamMembers[index].middleName);
	$("#gender").html(teamMembers[index].gender[0]);
	$("#birth").html(teamMembers[index].dateOfBirth);
	$("#place").html(teamMembers[index].city);
	$("#email").html(teamMembers[index].email);
	$("#phone").html(teamMembers[index].phone);
	$("#skype").html(teamMembers[index].skype);
	$("#about").html(teamMembers[index].about);
	$("#institution").html(teamMembers[index].institution);
	$("#faculty").html(teamMembers[index].faculty);
	$("#course").html(teamMembers[index].course);
	$("#placeOfWork").html(teamMembers[index].placeOfWork);
	$("#position").html(teamMembers[index].position);

	if (teamMembers[index].companyData != null) {
		$("#companyData").html(teamMembers[index].companyData.companyName);
		$("#aboutCompany").html(teamMembers[index].companyData.about);
	}

	$('#deleteUserButton').attr(
			"onclick",
			'sendRemoveMemberRequest("' + teamUUID + '","'
					+ teamMembers[index].uuid + '","' + index + '")');
	$('#profilePlaceholder').css('display', 'none');
	$('#memberProfile').css('display', 'block');

}

function sendMessage() {

	let message = new function() {
		this.id = null;
		this.senderName = me.lastName +" " + me.firstName;
		this.date = null;
		this.message = $('#message').val();
	};

	chatClient.send("/app/team/" + teamUUID + "/newMessage", {}, JSON
			.stringify(message));
}

function sendRemoveMemberRequest(teamID, userID, id) {

	let query = 'api/team/' + teamID + ' /' + userID;
	fetch(query, {
		method : 'POST',
		headers : getDefaultHeaders(),
		credentials : "same-origin"
	}).then(
			function() {
				$("#member_" + id).remove();
				$("#memberCounter").html(
						Number.parseInt($("#memberCounter").html()) - 1);
			});
}
