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
	let query = "/api/team/" + uuid;

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
						$("#creationDate").html(teamProfile.dateOfPublishing);
						$("#hackName").html(teamProfile.hack.title);
						$("#aboutTeam").html(teamProfile.about);
						$("#memberCounter").html(teamProfile.peopleCount);
						$("#hackLink").attr("href",
								"/hackPage/" + teamProfile.hack.uuid);

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
									
									if(Me.uuid == member.uuid)
										$("#chatPanel").css('display','block');
									
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

						if (captainProfile.uuid == UserID)
							$('#captainPanel').css("display", "block");
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

	let query = "/api/team/" + teamUUID + "/chat";
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
	$("#gender").html(teamMembers[index].gender);
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

	if (teamMembers[index].uuid == Me.uuid) {
		$("#sendMessageButton").css('display', 'none');
	} else {
		$("#sendMessageButton").attr("onclick",
				'return showMessageWindow(' + index + ')');
		$("#sendMessageButton").css('display', 'block');
	}

	if (teamMembers[index].companyData != null) {
		$("#companyData").html(teamMembers[index].companyData.companyName);
		$("#aboutCompany").html(teamMembers[index].companyData.about);
	}

	if (teamMembers[index].uuid != captainProfile.uuid)
		if (captainProfile.uuid == UserID)
			$('#deleteUserButton').attr(
					"onclick",
					'sendRemoveMemberRequest("' + teamUUID + '","'
							+ teamMembers[index].uuid + '","' + index + '")')
					.css("display", "block");
		else
			$('#deleteUserButton').css("display", "none");
	else
		$('#deleteUserButton').css("display", "none");

	$('#profilePlaceholder').css('display', 'none');
	$('#memberProfile').css('display', 'block');

}

function updatePage() {
	let newLocation = window.location.pathname + "/update";
	window.location.replace(newLocation);

}

function sendMessage() {

	let message = new function() {
		this.id = null;
		this.senderName = Me.lastName + " " + Me.firstName;
		this.date = null;
		this.message = $('#message').val();
	};

	chatClient.send("/app/team/" + teamUUID + "/newMessage", {}, JSON
			.stringify(message));

	$('#message').val("");
}

function sendRemoveMemberRequest(teamID, userID, id) {

	let query = '/api/team/' + teamID + '/removeUser';
	fetch(query, {
		method : 'POST',
		headers : getDefaultHeaders(),
		body : JSON.stringify(userID),
		credentials : "same-origin"
	}).then(
			function() {
				$("#member_" + id).remove();
				$("#memberCounter").html(
						Number.parseInt($("#memberCounter").html()) - 1);
			});

}

function showDeleteAlert() {
	$("#deleteAlert").modal("show");
	return false;
}

function deleteTeam() {

	$("#deleteAlert").modal('hide')
	let query = '/api/team/' + $("#teamUuid").html();
	fetch(query, {
		method : 'DELETE',
		headers : getDefaultHeaders(),
		credentials : "same-origin"
	})
			.then(
					function(response) {

						if (response.status != 200) {
							$(function() {

								$('body')
										.prepend(
												' <div id="myModalFail" class="modal fade" tabindex="-1"><div class="modal-dialog modal-lg"> <div class="modal-content"> <div class="modal-header"> <h4 class="modal-title">Регистрация</h4></div><div class="modal-body">При удалении команды произошла ошибка!</div><div class="modal-footer"><button class="btn btn-danger" data-dismiss="modal">Ок</button></div></div></div></div>');
								$('#myModalFail').modal("show");

							});
						} else {
							$(function() {
								window.location.replace("/profile");
							});
						}
					});
	return false;
}

function showSearchMembers() {
	$("#searchMembers").modal("show");
	return false;
}

function openUserProfileTab(profileLink) {

	window.open(profileLink);
	return false;
}

function findUsers() {

	$("#tableOfUsers").html("");

	let query = '/api/profile/searchUser?userLogin=' + $("#searchUser").val()
			+ "&teamID=" + $("#teamUuid").html();
	fetch(query, {
		method : 'GET',
		headers : getDefaultHeaders(),
		credentials : "same-origin"
	}).then(function(response) {
		return response.json();
	}).then(function(foundUsers) {

		if ((foundUsers == null) || (foundUsers.length == 0)) {
			;
			$("#notFoundMessage").css("display", "block");
			$("#memberSearchList").css("visibility", "hidden");
		} else {
			$("#notFoundMessage").css("display", "none");
			$("#memberSearchList").css("visibility", "visible");

			foundUsers.forEach(function(foundUser, index, arr) {

				var user = new function() {
					this.number = index + 1;
					this.FIO = foundUser.firstName + " " + foundUser.lastName;
					this.uuid = foundUser.uuid;
					this.profileLink = "/profileView/" + foundUser.uuid;
				}

				$("#foundUserRow").tmpl(user).appendTo("#tableOfUsers");
			});

		}
	});

	return false;
}

function clearInput() {

	$("#searchUser").val("");
	return false;
}

function sendInvite(userUUID) {

	let query = '/api/team/' + teamUUID + '/sendInvite';
	fetch(query, {
		method : 'POST',
		headers : getDefaultHeaders(),
		body : JSON.stringify(userUUID),
		credentials : "same-origin"
	});

	$("#inviteSendAlert").modal("show");

	return false;
}

function showMessageWindow(index) {

	$("#receiverName").html(
			teamMembers[index].lastName + " " + teamMembers[index].firstName);
	$("#sendToUser").attr("onclick", 'return sendMessageToUser(' + index + ')');

	$("#messageWindow").modal("show");
	return false;
}

function sendMessageToUser(index) {

	let Message = new function() {
		this.sender = Me.uuid;
		this.receiver = teamMembers[index].uuid;
		this.message = $("#messageTextarea").val();
	}

	let query = '/api/profile/sendMessage';
	fetch(query, {
		method : 'POST',
		headers : getDefaultHeaders(),
		body : JSON.stringify(Message),
		credentials : "same-origin"
	});

	$("#messageWindow").modal("hide");
	$("#messageSendAlert").modal("show");

	return false;
}
