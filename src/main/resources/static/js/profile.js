function getDefaultHeaders() {
	var headers = new Headers();
	headers.append("Content-Type", "application/json");
	return headers;
}

function loadUserProfile() {

	fetch("api/profile/me", {
		method : 'GET',
		headers : getDefaultHeaders(),
		credentials : "same-origin"
	}).then(function(response) {
		return response.json();

	}).then(
			function(profile) {

				$("#nickname").html(profile.login);
				$("#fio").html(
						profile.lastName + " " + profile.firstName + " "
								+ profile.middleName);
				$("#gender").html(profile.gender[0]);
				$("#birth").html(profile.dateOfBirth);
				$("#place").html(profile.city);
				$("#email").html(profile.email);
				$("#phone").html(profile.phone);
				$("#skype").html(profile.skype);
				$("#about").html(profile.about);
				$("#institution").html(profile.institution);
				$("#faculty").html(profile.faculty);
				$("#course").html(profile.course);
				$("#placeOfWork").html(profile.placeOfWork);
				$("#position").html(profile.position);

				if (profile.companyData != null) {
					$("#companyData").html(profile.companyData.companyName);
					$("#aboutCompany").html(profile.companyData.about);
				}

				$('#loadingIcon').remove();
				$('#userProfile').css('display', 'block');

				profile.teams.forEach(function(team, index, arr) {
					team.userID = profile.uuid;
					team.id = index;
					$("#teamItem").tmpl(team).appendTo("#userTeam");
				});

			});

}

$("#profile").addClass('text-warning');

function showProfile() {
	$('#userProfile').css('display', 'block');
	$('#userTeam').css('display', 'none');
}

function showTeams() {
	$('#userProfile').css('display', 'none');
	$('#userTeam').css('display', 'block');
}

function sendRemoveMemberRequest(teamID, userID, id) {

	let query = 'api/team/' + teamID + ' /' + userID;
	fetch(query, {
		method : 'POST',
		headers : getDefaultHeaders(),
		credentials : "same-origin"
	}).then(function() {
		$("#team_"+id).remove();
	});
}











