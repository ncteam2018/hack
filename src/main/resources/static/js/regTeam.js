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


fetch("api/hack/names", {
    method: 'GET',
    headers: getDefaultHeaders(),
    credentials: "same-origin"
  }).then(function (response) {
	   return response.json();
  }).then(function (hackNames) {

	  hackNames.forEach(function(hackName, index, arr) {
		  $('#hack').append( $("<option></option>").attr("value",
				  hackName.second).text(hackName.first)); 
	  });
  });


var activeTags = new Array()

function addTag(type) {

	if (type == "1") {
		var tagName = $("#skillTagsInput").val();
		var tagID = "skill_" + $("#skillTagsInput").val();
		var place = "#filterSkillTags";
	} else {
		var tagName = $("#scopeTagsInput").val();
		var tagID = "scope_" + $("#scopeTagsInput").val();
		var place = "#filterScopeTags";
	}

	if (tagName != "") {

		obj = activeTags.find(function(tag, index, array) {
			if ((tag.tagName == tagName) && (tag.type == type))
				return true;
			return false;

		});

		if (obj == null) {
			var newTag = {
				"id" : tagID,
				"tagName" : tagName,
				"type" : type
			}
			activeTags.push(newTag);
			$("#tagItem").tmpl(newTag).appendTo(place);
		}
	}
	return false;
}

function removeTag(name) {
	var objID = name;
	obj = activeTags.find(function(tag, index, array) {
		if (tag.id == objID)
			return true;
		return false;

	});

	if (obj != null) {
		activeTags.splice(activeTags.indexOf(obj), 1);
		$("#" + name).remove();
	}

	return false;
}


function loadHack(hackUUid,team) {
	
	let query = "api/hack/" + hackUUid;
	fetch(query, {
	    method: 'GET',
	    headers: getDefaultHeaders(),
	    credentials: "same-origin"
	  }).then(function (response) {
		   return response.json();
	  }).then(function (hack) {
		  team.hack = hack;
		  saveTeam(team);
	  });

	
}

function saveTeam(team) {
	
	fetch("api/team", {
	    method: 'POST',
	    headers: getDefaultHeaders(),
	    body: JSON.stringify(team),
	    credentials: "same-origin"
	  }).catch(function (err) {
	    console.log('Ошибка создания команды, исправьте данные! - ', err);
	  })
	  .then(function (response) {

		  if (response.status != 201) {
		      $(function () {

		        $('body').prepend(
		            ' <div id="myModalFail" class="modal fade" tabindex="-1"><div class="modal-dialog modal-lg"> <div class="modal-content"> <div class="modal-header"> <h4 class="modal-title">Регистрация</h4></div><div class="modal-body">При создании команды произошла ошибка!</div><div class="modal-footer"><button class="btn btn-danger" data-dismiss="modal">Ок</button></div></div></div></div>');
		        $('#myModalFail').modal("show");

		      });
		    } else {
		      $(function () {

		        $('body').prepend(
		            ' <div id="myModal" class="modal fade" tabindex="-1"><div class="modal-dialog modal-lg"> <div class="modal-content"> <div class="modal-header"> <h4 class="modal-title">Регистрация</h4></div><div class="modal-body">Команда успешно создана!</div><div class="modal-footer"><button class="btn btn-success" data-dismiss="modal">Ок</button></div></div></div></div>');
		        $('#myModal').modal("show");
		      });

		    }
		  return response.json();
		  }).then(function (team) {
			  if(team != null)
			        window.location.replace("/teamProfile?uuid=" + team.uuid);	  
		  }	);
}

var team;

function createTeam() {

	 team = new function() {	  
		this.name = document.getElementById("title").value;
		this.about = document.getElementById("description").value;
		this.hack = document.getElementById("hack").value;
		this.captain = null;
		
		var skills = new Array();
		var scopes = new Array();

		activeTags.forEach(function(tag, index, arr) {

			if (tag.type == "1")
				skills.push({
					"id" : null,
					"tagName" : tag.tagName
				});

			if (tag.type == "2")
				scopes.push({
					"id" : null,
					"tagName" : tag.tagName
				});
		});
		
		this.skillTags = skills;
		this.scopeTags = scopes;
		
	};
	
	fetch("api/profile/me", {
	    method: 'GET',
	    headers: getDefaultHeaders(),
	    credentials: "same-origin"
	  }).catch(function (err) {
	    console.log('Ошибка создания команды, исправьте данные! - ', err);
	  })
	  .then(function (response) {

		  return response.json();
	  }).then(function (userProfile) {
		  
		team.captain = userProfile;
		loadHack(team.hack,team);
	  });
	return false;
}














