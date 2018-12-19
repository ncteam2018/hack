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

var oldTeam;
function loadTeamData(){
	
	let query = "/api/team/" + $("#teamUuid").html();
		
	fetch(query, {
	    method: 'GET',
	    headers: getDefaultHeaders(),
	    credentials: "same-origin"
	  }).then(function (response) {
		   return response.json();
	  }).then(function (team) {
		  
		  $("#title").val(team.name );
		  $("#description").val(team.about );
		  
		  fetch("/api/hack/names", {
			    method: 'GET',
			    headers: getDefaultHeaders(),
			    credentials: "same-origin"
			  }).then(function (response) {
				   return response.json();
			  }).then(function (hackNames) {

				  hackNames.forEach(function(hackName, index, arr) {
					  if(hackName.second == team.hack.uuid)
						  $('#hack').append( $("<option></option>").attr("value",
								  hackName.second).attr("selected",
										  "selected").text(hackName.first)); 
					  else
						  $('#hack').append( $("<option></option>").attr("value",
								  hackName.second).text(hackName.first)); 
				  });
			  });

		  team.skillTags.forEach(function(tag, index, arr) {
			  $("#skillTagsInput").val(tag.tagName);
			  addTag("1");
			});
		  $("#skillTagsInput").val("");
		  
		  team.scopeTags.forEach(function(tag, index, arr) {
			  $("#scopeTagsInput").val(tag.tagName);
			  addTag("2");
			});
		  $("#scopeTagsInput").val("");
		  
		  oldTeam = team;
	 });
}



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



function saveTeam() {
	
	let query = "/api/team/" + oldTeam.uuid;
	fetch( query, {
	    method: 'PUT',
	    headers: getDefaultHeaders(),
	    body: JSON.stringify(oldTeam),
	    credentials: "same-origin"
	  }).catch(function (err) {
	    console.log('Ошибка создания команды, исправьте данные! - ', err);
	  })
	  .then(function (response) {

		  if (response.status != 201) {
		      $(function () {

		        $('body').prepend(
		            ' <div id="myModalFail" class="modal fade" tabindex="-1"><div class="modal-dialog modal-lg"> <div class="modal-content"> <div class="modal-header"> <h4 class="modal-title">Регистрация</h4></div><div class="modal-body">При обновлении описания команды произошла ошибка!</div><div class="modal-footer"><button class="btn btn-danger" data-dismiss="modal">Ок</button></div></div></div></div>');
		        $('#myModalFail').modal("show");

		      });
		    } else {
		      $(function () {

		        $('body').prepend(
		            ' <div id="myModal" class="modal fade" tabindex="-1"><div class="modal-dialog modal-lg"> <div class="modal-content"> <div class="modal-header"> <h4 class="modal-title">Регистрация</h4></div><div class="modal-body">Описание команды успешно изменено!</div><div class="modal-footer"><button class="btn btn-success" data-dismiss="modal">Ок</button></div></div></div></div>');
		        $('#myModal').modal("show");
		      });

		    }
		  return response.json();
		  }).then(function (team) {
			  if(team != null)
			        window.location.replace("/teamProfile/" + oldTeam.uuid);	  
		  }	);
}

var team;

function updateTeam() {

	
	oldTeam.name = $("#title").val();
	oldTeam.about = $("#description").val();
	
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
	
	oldTeam.skillTags = skills;
	oldTeam.scopeTags = scopes;
		
	let query = "/api/hack/" + $("#hack").val();
	fetch(query, {
	    method: 'GET',
	    headers: getDefaultHeaders(),
	    credentials: "same-origin"
	  }).then(function (response) {
		   return response.json();
	  }).then(function (hack) {
		  
		  oldTeam.hack = hack;
		  saveTeam();
	  });
	
	
	return false;
}

function goBack(){
	
	window.location.replace("/teamProfile/" + oldTeam.uuid);
	return false;
}
















