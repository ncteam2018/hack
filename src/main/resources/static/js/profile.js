function loadUserProfile() {
   
	fetch('/api/profile/login/test')
	  .then(function(response) {
			
	   return response.json();
})
.then(function(profile) {
	
	$("#nickname").html(profile.login);
	
	
	
	var profile = JSON.stringify(profile, null, 2);
	alert(profile);
	
})
}
