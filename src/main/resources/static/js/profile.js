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
				$("#gender").html(profile.gender);
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
					
					profile.companyData.hacks.sort((a,b) => (a.name > b.name) ? 1 : ((b.name > a.name) ? -1 : 0));
					profile.companyData.hacks.forEach(function(hack, index, arr) {
						hack.userID = profile.uuid;
						hack.id = index;
						hack.hackLink = "/hackPage/" + hack.uuid;
						$("#hackItem").tmpl(hack).appendTo("#userHacks");
					});
					
					$("#companyInfo").css("display","block");
					$("#hackListButton").css("display","block");
				}

				$('#loadingIcon').remove();
				$('#userProfile').css('display', 'block');
				
				profile.teams.sort((a,b) => (a.name > b.name) ? 1 : ((b.name > a.name) ? -1 : 0));

				profile.teams.forEach(function(team, index, arr) {
					team.userID = profile.uuid;
					team.id = index;
					team.hackLink = "/hackPage/" + team.hack.uuid;
					$("#teamItem").tmpl(team).appendTo("#userTeam");
					
					if(team.captain.uuid != Me.uuid){
						$("#"+team.id).append('<button onclick="" class="btn bg-danger text-light text-right" ' + 'id="_' + team.id + '"> Покинуть команду</button>');
						$("#_"+team.id).attr("onclick", 'sendRemoveMemberRequest("' + team.uuid + '", "' + Me.uuid + '", "' + team.id +'" )');
					}else{
						$("#text_"+team.id).css("display","block");
					}
											
				});
				

			});

}

$("#profile").addClass('text-warning');

function showProfile() {
	$('#userProfile').css('display', 'block');
	$('#userTeam').css('display', 'none');
	$('#userHacks').css('display', 'none');
}

function showTeams() {
	$('#userProfile').css('display', 'none');
	$('#userTeam').css('display', 'block');
	$('#userHacks').css('display', 'none');
	
}

function showHacks() {
	$('#userProfile').css('display', 'none');
	$('#userTeam').css('display', 'none');
	$('#userHacks').css('display', 'block');
}

function sendRemoveMemberRequest(teamID, userID, id) {

	let query = 'api/team/' + teamID + ' /' + userID;
	fetch(query, {
		method : 'POST',
		headers : getDefaultHeaders(),
		credentials : "same-origin"
	}).then(function() {
		$("#team_"+id).remove();
		showDeleteAlert();
	});
}

function showDeleteAlert() {
	$("#successAlert").modal("show");
	return false;
}










