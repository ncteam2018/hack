function getDefaultHeaders() {
	var headers = new Headers();
	headers.append("Content-Type", "application/json");
	return headers;
}

function loadHackProfile() {

	let query = "/api/hack/" + $("#hackUuid").html();
	fetch(query, {
		method : 'GET',
		headers : getDefaultHeaders(),
		credentials : "same-origin"
	}).then(function(response) {
		return response.json();
	}).then(function(hack) {

		$("#hackStatus").html(hack.status);
		$("#hackName").html(hack.title);
		$("#creationDate").html(hack.startDate);
		$("#duration").html(hack.duration);
		$("#compName").html(hack.company.companyName);
		$("#aboutHack").html(hack.description);
		$("#auditory").html(hack.auditory);
		$("#source").html(hack.site);
		$("#address").html(hack.place);

		hack.skillTags.forEach(function(tag, index, arr) {
			$("#tag-info").tmpl(tag).appendTo("#hack-tags");
		});

		hack.scopeTags.forEach(function(tag, index, arr) {
			$("#tag-info").tmpl(tag).appendTo("#hack-tags");
		});

		mas = hack.placeCoords.split(',').map(Number)
		mark_X = mas[1];
		mark_Y = mas[0];

		hackathonPlace = new ymaps.Placemark([ mark_X, mark_Y ], { // Ставим
			// маркер
			// хакатона и
			// центрируем
			// карту на него
			hintContent : 'Место проведения хакатона'
		}, {
			preset : "islands#circleDotIcon",
			iconColor : '#ff0000'
		});
		myMap.geoObjects.add(hackathonPlace)
		myMap.setCenter([ mark_X, mark_Y ]);

		if ((Me.companyData != null) && (hack.company.uuid == Me.companyData.uuid)&&(hack.status != "Closed")  )
			$("#deleteButton").css("display","block");

	});

}

var myMap; // Объект хранящий yandex карту
var mark_X = 55.76; // Начальные координаты центровки карты
var mark_Y = 37.64;
var hackathonPlace; // Хранит метку нужного хакатона карте

ymaps.ready(init); // Фунция вызывается когда страница уже вся загрузилась
function init() {
	// Создание карты.
	myMap = new ymaps.Map("map", {
		center : [ mark_X, mark_Y ],
		controls : [],
		zoom : 16
	});

	var focusOnHackButton = new ymaps.control.Button({ // Добавляем на карту
		// кастомную кнопку, она
		// центрирует карту на
		// метке, если вдруг вы
		// потерялись на карте
		data : {
			// Текст кнопки.
			content : "<b>Фокус</b>",
			title : "Центрирует карту на месте проведения"
		},
		options : {
			maxWidth : [ 28, 150, 178 ]
		}
	});

	focusOnHackButton.events.add('click', function(e) {
		myMap.setCenter([ mark_X, mark_Y ]);
		myMap.setZoom(16);
	});

	myMap.controls.add(focusOnHackButton);
}

function showDeleteAlert() {
	$("#deleteAlert").modal("show");
	return false;
}

function deleteHack() {

	$("#deleteAlert").modal('hide')
	let query = '/api/hack/' + $("#hackUuid").html();
	fetch(query, {
		method : 'DELETE',
		headers : getDefaultHeaders(),
		credentials : "same-origin"
	}).then(function(response) {
		window.location.replace("/profile");
	});
	return false;
}
