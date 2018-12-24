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


$("#teams").addClass('text-warning');

var teamList; // Массив загруженных хакатонов
var hackPageParams; //
function PageHandler() { 

	this.createPages = function() {

		this.maxPage = totalPages;
		this.pageSize = numberOfElements;
		this.pages = [];

		if (this.maxPage > 3)
			this.pages = [ 1, 2, "..", this.maxPage ];
		else if (this.maxPage == 3)
			this.pages = [ 1, 2, 3 ]
		else if (this.maxPage == 2)
			this.pages = [ 1, 2 ];
		else
			this.pages = [ 1 ];

		this.updatePagination(-1);

	}
	this.updatePagination = function(page) {

		let pageNumber = {
			number : 0
		}

		if (page != -1) {
			let query = "api/team?page=" + (page - 1) + "&size="
					+ this.pageSize;

			if (filterQueryString != "")
				query += "&" + filterQueryString;

			fetch(query, {
				method : 'GET',
				headers : getDefaultHeaders(),
				credentials : "same-origin"
			}).then(function(response) {
				return response.json();

			}).then(function(hackPage) {
				$("#teamList").html("");
				teamList = hackPage.content;
				teamList.forEach(function(teamItem, index, arr) {
					teamItem.index = index;
					$("#teamItem").tmpl(teamItem).appendTo("#teamList");
				});
			});
		} else
			page = 1;

		$("#teamPagination").html("");

		this.pages.forEach(function(number, index, arr) {
			pageNumber.number = number;

			$("#paginationItem").tmpl(pageNumber).appendTo("#teamPagination");
			if (number == page) {
				page_id = "#page_" + page;
				$(page_id).removeClass("bg-dark").removeClass("text-light")
						.addClass("bg-warning").addClass("text-dark");
			}
		});

	}

	this.createPages();
}

function changePage(page) {

	let max = window.pageHandler.maxPage;

	if (page == 1) {
		if (max > 3)
			window.pageHandler.pages = [ 1, 2, "..", max ]
		else if (max == 1)
			window.pageHandler.pages = [ 1 ]
		else if (max == 2)
			window.pageHandler.pages = [ 1, 2 ]
		else if (max == 3)
			window.pageHandler.pages = [ 1, 2, 3 ]
	} else if (page == 2) {
		if (max > 4)
			window.pageHandler.pages = [ 1, 2, 3, "..", max ]
		else if (max == 2)
			window.pageHandler.pages = [ 1, 2 ]
		else if (max == 3)
			window.pageHandler.pages = [ 1, 2, 3 ]
		else if (max == 4)
			window.pageHandler.pages = [ 1, 2, 3, 4 ]
	}

	else if (page == 3) {
		if (max > 5)
			window.pageHandler.pages = [ 1, 2, 3, 4, "..", max ]
		else if (max == 3)
			window.pageHandler.pages = [ 1, 2, 3 ]
		else if (max == 4)
			window.pageHandler.pages = [ 1, 2, 3, 4 ]
		else if (max == 5)
			window.pageHandler.pages = [ 1, 2, 3, 4, 5 ]

	} else if (page == max)
		window.pageHandler.pages = [ 1, "..", max - 1, max ]
	else if (page == max - 1)
		window.pageHandler.pages = [ 1, "..", max - 2, max - 1, max ]
	else if (page == max - 2)
		window.pageHandler.pages = [ 1, "..", max - 3, max - 2, max - 1, max ]
	else
		window.pageHandler.pages = [ 1, "..", page - 1, page, page + 1, "..",
				max ]

	window.pageHandler.updatePagination(page);
	return false;
}

var pageHandler // объект обработчика пагинации
var totalPages // переменная с кол-вом страниц по данному запросу ( меняет
// только loadData() )
var numberOfElements // переменная с размером запрашиваемых страниц ( меняет
// только loadData() )

function loadData(isFirst) {

	let query = "api/team?size=5"; // Формирует query string
	let hackPageParams;

	if (filterQueryString != "")
		query += "&" + filterQueryString;
	else if (isFirst) {
		$("#hackName").val($("#setHackName").html());
		findNewHackList();
		query += "&" + filterQueryString;
	}

	fetch(query, {
		method : 'GET',
		headers : getDefaultHeaders(),
		credentials : "same-origin"
	}).then(function(response) {
		if (response.status != 200) {
			$('#loadingIcon').remove();
			$("#teamList").html("<h2>Ошибка при загрузке списка команд</h2>");
		}
		return response.json();
	}).then(function(teamPage) {
		$("#teamList").html(""); 
		
		teamList = teamPage.content;

		let cityNames = new Array();
		let teamCounter = 0;
		teamList.forEach(function(teamItem, index, arr) {
			teamCounter = teamCounter + 1;
			teamItem.index = index;
			$("#teamItem").tmpl(teamItem).appendTo("#teamList");
		});

		$('#loadingIcon').remove();
		if (teamCounter == 0)
			$("#teamList").html("<h2>Ничего не найдено</h2>");

		if (isFirst)
			loadFilters();

		totalPages = teamPage.totalPages; // Запоминаем кол-во страниц (для
		// пагинации)
		numberOfElements = teamPage.numberOfElements; // Запоминаем размер
		// страницы (для
		// пагинации)

		window.pageHandler = new PageHandler(); // Создаём новый обработчик
		// пагинации

	});

}

function loadFilters() { // Загружает все зарегистрированные теги для
	// фильтрации

	fetch("/api/tags", {
		method : 'GET',
		headers : getDefaultHeaders(),
		credentials : "same-origin"
	}).then(function(response) {
		return response.json();

	}).then(function(loadedTags) {
		let hackTags;
		hackTags = loadedTags[0]; // Обрабатываем теги скиллов
		hackTags.forEach(function(tag, index, arr) {

			tags.push({
				"id" : tag.id,
				"tagName" : tag.tagName,
				"category" : "skill"
			});
			$("#tagItem").tmpl(tag).appendTo("#tag_skills"); // Добавляем
			// шаблон тега в
			// контейнер с
			// id=tag_skills

		})

		hackTags = loadedTags[1]; // Обрабатываем теги направлений
		hackTags.forEach(function(tag, index, arr) {

			tags.push({
				"id" : tag.id,
				"tagName" : tag.tagName,
				"category" : "scope"
			});

			$("#tagItem").tmpl(tag).appendTo("#tag_scope"); // Добавляем шаблон
			// тега в контейнер
			// с id=tag_scope

		})
	});

	fetch("/api/hack/places", {
		method : 'GET',
		headers : getDefaultHeaders(),
		credentials : "same-origin"
	}).then(function(response) {
		return response.json();

	}).then(
			function(loadedCities) {

				loadedCities.forEach(function(cityName, index, arr) {
					$('#cityNameInput').append(
							$("<option></option>").attr("value", cityName)
									.text(cityName));
				});

			});
}

function showTeamInformation(index) {
	$("#full_teamName").html(teamList[index].name);
	$("#full_teamDescription").html(teamList[index].about);
	$("#full_teamDate").html(teamList[index].dateOfPublishing);
	$("#userCounter").html(teamList[index].peopleCount);
	$("#hacknameRef").html(teamList[index].hack.title)

	$("#captain_avatar").attr("src","/img/avatars/"+teamList[index].captain.uuid);
	$("#fio").html(
			teamList[index].captain.lastName + " "
					+ teamList[index].captain.firstName + " "
					+ teamList[index].captain.middleName);
	$("#gender").html(teamList[index].captain.gender);
	$("#birth").html(teamList[index].captain.dateOfBirth);
	$("#place").html(teamList[index].captain.city);
	$("#email").html(teamList[index].captain.email);
	$("#phone").html(teamList[index].captain.phone);
	$("#skype").html(teamList[index].captain.skype);
	$("#about").html(teamList[index].captain.about);
	$("#institution").html(teamList[index].captain.institution);
	$("#faculty").html(teamList[index].captain.faculty);
	$("#course").html(teamList[index].captain.course);
	$("#placeOfWork").html(teamList[index].captain.placeOfWork);
	$("#position").html(teamList[index].captain.position);

	if (teamList[index].captain.companyData != null) {
		$("#companyData").html(teamList[index].captain.companyData.companyName);
		$("#aboutCompany").html(teamList[index].captain.companyData.about);
	}

	$("#captainProf").click(function() {
		return showCaptainProfile(index);
	});

	$("#hackref").click(function() {
		return showHackInformation(index);
	});
	captain

	$("#team-tags").html("");
	teamList[index].hack.skillTags.forEach(function(tag, index, arr) {
		$("#team-info").tmpl(tag).appendTo("#team-tags");
	});

	teamList[index].hack.scopeTags.forEach(function(tag, index, arr) {
		$("#team-info").tmpl(tag).appendTo("#team-tags");
	});

	$('#addMemberRef').attr("onclick",
			'sendAddMemberRequest("' + teamList[index].uuid + '")').html("Подать заявку в команду");

	teamList[index].teamMembers.forEach(function(member, i, arr) {
		if (member.uuid == Me.uuid)
			$('#addMemberRef').attr("onclick",
					'window.location.replace("/teamProfile/' + teamList[index].uuid + '")').html("Страница команды");
	});

	$('#fullTeamInfo').modal("show");

	return false;
}

function showCaptainProfile(index) {
	$('#fullTeamInfo').modal("hide");
	$('#captain').modal("show");
	return false;

}

function showTeamInfo() {
	$('#fullTeamInfo').modal("show");
	return false;
}

var mas; // ПЛОХОЕ НАЗВАНИЕ, временное хранилище координат хакатона на карте

function showHackInformation(index) {

	// -- Обновляем отображение карты
	if ((teamList[index].hack.placeCoords != "")
			&& (teamList[index].hack.placeCoords != null)) {
		$('#mapShowButton').prop('disabled', false);
		mas = teamList[index].hack.placeCoords.split(',').map(Number)
		mark_X = mas[1];
		mark_Y = mas[0];
		hackathonPlace.geometry.setCoordinates([ mark_X, mark_Y ]);
		myMap.setCenter([ mark_X, mark_Y ]);
	} else
		$('#mapShowButton').prop('disabled', true);

	// ------------
	// Заполнение модального окна данными нужного хакатона при нажатии на кнопку
	// подробнее
	$("#full_hackName").html(teamList[index].hack.title);
	$("#full_description").html(teamList[index].hack.description);
	$("#full_place").html(teamList[index].hack.place);
	$("#full_date").html(teamList[index].hack.startDate);
	$("#full_dutarion").html(teamList[index].hack.duration);
	$("#full_site").html(teamList[index].hack.site);
	$("#full_auditory").html(teamList[index].hack.auditory);
	$("#companyStatus").html(teamList[index].hack.company.status[0]);
	if (teamList[index].hack.company.status[0] == "LEGAL")
		$("#companyStatus").removeClass("text-warning")
				.addClass("text-success");

	$("#companyName").html(teamList[index].hack.company.companyName);
	$("#companyAbout").html(teamList[index].hack.company.about);
	$("#showCompany").click(function() {
		return showCompanyProfile();
	});

	$("#hack-tags").html("");
	teamList[index].hack.skillTags.forEach(function(tag, index, arr) {
		$("#tag-info").tmpl(tag).appendTo("#hack-tags");
	});

	teamList[index].hack.scopeTags.forEach(function(tag, index, arr) {
		$("#tag-info").tmpl(tag).appendTo("#hack-tags");
	});

	$('#fullTeamInfo').modal("hide");
	$('#fullHackInfo').modal("show"); // Закончив вставлять нужные данные в
	// модальное окно с id=fullHackInfo,
	// отобразим его

	return false;
}

function showInfo() {
	$('#fullHackInfo').modal("show");
}

function showMap() { // При нажатии на кнопку 'Карта', Показать модальное
	// окно с id=hackMap
	$('#hackMap').modal("show");
}

function showCompanyProfile() { // При нажатии на кнопку 'Организатор'
	$('#fullHackInfo').modal("hide"); // Прячем модальное окно с подробностями
	// хакатона
	$('#companyProfile').modal("show"); // Отображаем модальное окно с
	// информацией о компании-организаторе
	return false;
}

var tags = [] // Хранит все загруженные теги в функции loadTags()
var activeTags = [] // Хранит все теги которые выбраны в данный момент, на их
// основе строится фильтер поиска по тегам

function addTag(id) { // При нажатии на блок с тегом в фильтрах, Добавить тег
	// в выбранные и отрисовать его под строкой поиска

	if (!activeTags.includes(id)) { // Если выбран первый раз рисуем и добавляем
		tagID = "#tag_" + id;

		var objID = id;
		obj = tags.find(function(tag, index, array) {
			if (tag.id == objID)
				return true;
			return false;

		});

		$("#tagItem-active").tmpl(obj).appendTo("#filterTags");
		activeTags.push(id);
		$(tagID + "_button").removeClass("bg-dark").addClass("bg-success");
	} else { // Если выбран второй раз, то считаем это отменой выделения и
		// удаляем его из выделенных
		removeTag(id);
	}

	return false;
}

function removeTag(id) { // Удаляет отображение тега и из массива с
	// выделенными тегами
	tag_act_ID = "#tag_act_" + id;
	tagID = "#tag_" + id;

	$(tag_act_ID).remove();
	activeTags.splice(activeTags.indexOf(id), 1);
	$(tagID + "_button").removeClass("bg-success").addClass("bg-dark");

	return false;
}

function clearAllTags() { // При нажатии на кнопку 'Очистить', Очищает массив
	// выделенных тегов (очищает фильтр по тегам)

	let copy = Array.from(activeTags);

	for (i = 0; i < copy.length; ++i) {
		id = copy[i];
		removeTag(id)
	}

	return false;
}

var filterQueryString = ""; // Хранит сформированную строку фильтра
function findNewHackList() { // При нажатии на кнопку 'Поиск'

	filterQueryString = ""; // Строим фильт заного, очищаем старое значение

	// --- Добавляем порядок сортировки
	var sort = new Object();
	let sortDirection = $('input[name=options]:checked', '#direction').val(); // Получаем
	// выбранный
	// radio
	// button

	switch (sortDirection) { // Добавляем направление сортировки
	case "1":
		sort.direction = "ASC";
		break;
	case "2":
		sort.direction = "DESC";
	}

	let sortParam = "";
	$("#sortDirection option:selected").each(function() {
		sortParam = $(this).val();
	});

	switch (sortParam) { // Добавляем вид фильтра (по времени/по имени)
	case "1":
		sort.property = "name";
		break;
	case "2":
		sort.property = "dateOfPublishing";
	}

	filterQueryString += "sort=" + JSON.stringify(sort); // Превращаем
	// полученный объект
	// в json строку и
	// добавляем в
	// строку фильтра

	// --- Добавляем теги поиска
	var filters = new Array();

	activeTags.forEach(function(tag, index, arr) {

		var objID = tag;
		obj = tags.find(function(tag, index, array) {
			if (tag.id == objID)
				return true;
			return false;

		});

		if (obj.category == "skill")
			filters.push({
				"property" : "skill",
				"value" : tag
			});

		if (obj.category == "scope")
			filters.push({
				"property" : "scope",
				"value" : tag
			});
	});

	// --- Добавляем имя хакатона

	if ($("#hackName").val() != "")
		filters.push({
			"property" : "name",
			"value" : $("#hackName").val()
		});
	// --- Добавляем имя компании
	if ($("#compNameInput").val() != "")
		filters.push({
			"property" : "companyName",
			"value" : $("#compNameInput").val()
		});

	// --- Добавляем имя города
	if ($("#cityNameInput").val() != "")
		filters.push({
			"property" : "cityName",
			"value" : $("#cityNameInput").val()
		});

	filterQueryString += "&filter=" + JSON.stringify(filters);

	// ---

	// -- Загружаем новый список хакатонов

	filterQueryString = encodeURI(filterQueryString);
	loadData(false); // Вызываем новую загрузку хакатонов с новым фильтром,
	// false - это не первая загрузка, не надо снова грузить
	// теги

	// --

	return false;

}
// Перекращивает элемент по переданном id в жёлтый, а другой в белый (вроде
// используется в стрелочках сортировки)
function changeColor(newAct, oldAct) {
	$("#" + newAct).css("color", "yellow");
	$("#" + oldAct).css("color", "white");
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

	hackathonPlace = new ymaps.Placemark([ mark_X, mark_Y ], { // Ставим маркер
		// хакатона и
		// центрируем
		// карту на него
		hintContent : 'Место проведения хакатона'
	}, {
		preset : "islands#circleDotIcon",
		iconColor : '#ff0000'
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
	myMap.geoObjects.add(hackathonPlace)
}

function sendAddMemberRequest(teamID) {

	let query = '/api/team/' + teamID + '/sendRequest';
	fetch(query, {
		method : 'POST',
		headers : getDefaultHeaders(),
		credentials : "same-origin"
	});
	
	$("#requestSendAlert").modal("show");
	
	return false;
}
