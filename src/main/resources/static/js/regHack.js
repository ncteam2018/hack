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

function createHackathon() {

	var hackathon = new function() {
		this.title = document.getElementById("title").value;
		this.description = document.getElementById("description").value;
		this.startDate = document.getElementById("startDate").value;
		this.duration = document.getElementById("duration").value;
		
		this.place = address;
		this.placeCoords = coords[1] + "," + coords[0];
		this.site = document.getElementById("site").value;
		this.auditory = document.getElementById("auditory").value;

		this.startDate = document.getElementById("startDate").value;
		this.startDate = document.getElementById("startDate").value;
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
		this.company = null;
		
	};
	
	fetch("api/hack", {
	    method: 'POST',
	    headers: getDefaultHeaders(),
	    body: JSON.stringify(hackathon),
	    credentials: "same-origin"
	  }).catch(function (err) {
	    console.log('Ошибка создания хакатона, исправьте данные! - ', err);
	  })
	  .then(function (response) {

	    if (response.status != 201) {
	      $(function () {

	        $('body').prepend(
	            ' <div id="myModalFail" class="modal fade" tabindex="-1"><div class="modal-dialog modal-lg"> <div class="modal-content"> <div class="modal-header"> <h4 class="modal-title">Регистрация</h4></div><div class="modal-body">При создании хакатона произошла ошибка!</div><div class="modal-footer"><button class="btn btn-danger" data-dismiss="modal">Ок</button></div></div></div></div>');
	        $('#myModalFail').modal("show");

	      });
	    } else {
	      $(function () {

	        $('body').prepend(
	            ' <div id="myModal" class="modal fade" tabindex="-1"><div class="modal-dialog modal-lg"> <div class="modal-content"> <div class="modal-header"> <h4 class="modal-title">Регистрация</h4></div><div class="modal-body">Хакатон успешно создан!</div><div class="modal-footer"><button class="btn btn-success" data-dismiss="modal">Ок</button></div></div></div></div>');
	        $('#myModal').modal("show");
	        $('#myModal').on('hidden.bs.modal', function (event) {
	          window.location.replace("/hacks");
	        });
	      });

	    }
	  });
	
	return false;
}

var coords;
var address;

ymaps.ready(function () {
    var myMap = new ymaps.Map('map', {
            center: [55.661574, 37.573856],
            controls: ['geolocationControl'],
            zoom: 16
        }, {
            searchControlProvider: 'yandex#search'
        }),

        myPlacemark = new ymaps.Placemark(myMap.getCenter(), {
          hintContent: 'Указанное место'
        }, {
            // iconLayout: 'default#image',
            preset: "islands#dotIcon",
            iconColor: '#ff0000',
           iconImageSize: [30, 42],
            iconImageOffset: [-5, -38]
        }),
        
        // Моя метка
        hackathonPlace = new ymaps.Placemark([55.75, 37.61], {
            hintContent: 'Место проведения хакатона'}
        , {
            preset: "islands#circleDotIcon",
            iconColor: '#ff0000'
        }   

        );
    
    var searchControl = new ymaps.control.SearchControl({
      options: {
        provider: 'yandex#search'
      }
    });
    
searchControl.events.add('resultselect', function(e) {
      var index = e.get('index');
      searchControl.getResult(index).then(function(res) {
        coords = res.geometry.getCoordinates();
        var query = "https://geocode-maps.yandex.ru/1.x/?format=json&apikey=4fa6aa42-d9bb-4b62-9e6b-77d1ce2ff058&geocode=" + coords[1] + ","+ coords[0];
        
		  fetch(query).then(function(response) { 
			  return response.json();
		  }).then(function(loadedTags) {
			  address = loadedTags.response.GeoObjectCollection.featureMember["0"].GeoObject.metaDataProperty.GeocoderMetaData.text;
			  $("#place").html(address);
		  });
		 
        

        
      });
});

    myMap.geoObjects
        .add(myPlacemark)
        .add(hackathonPlace);
    myMap.controls.add(searchControl);
});












