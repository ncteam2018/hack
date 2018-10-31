function loadUserProfile() {
   
	fetch('api/profile/28a82e3c-b777-4a77-b997-46b6f541c95e')
	  .then(function(response) {
			
	   return response.json();
})
.then(function(profile) {
	
	$("#nickname").html(profile.login);
	
	
	
	var profile = JSON.stringify(profile, null, 2);
	alert(profile);
	
})
}
