function getDefaultHeaders() {
  var headers = new Headers();
  headers.append("Content-Type", "application/json");
  return headers;
}

function loadUserProfile() {

  fetch("api/profile/me", {
    method: 'GET',
    headers: getDefaultHeaders(),
    credentials: "same-origin"
  }).then(function (response) {
    return response.json();

  }).then(function (profile) {

    $("#nickname").html(profile.login);
    $("#fio").html(profile.lName + " " + profile.mName + " " + profile.fName);
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

    //var profile = JSON.stringify(profile, null, 2);
    //alert(profile);

  });

}	

