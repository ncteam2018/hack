function getDefaultHeaders() {
	var headers = new Headers();
	headers.append("Content-Type", "application/json");
	return headers;
}

function loadUserProfile() {

	let query = "/api/profile/" + $("#userUUID").html();
	fetch(query, {
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
				$("#main_avatar").attr("src","/img/avatars/" + profile.uuid);

				if (profile.companyData != null) {
					$("#companyData").html(profile.companyData.companyName);
					$("#aboutCompany").html(profile.companyData.about);
				}

				$('#loadingIcon').remove();
				$('#userProfile').css('display', 'block');
			});

}
