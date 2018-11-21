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

var hackList;
var hackPageParams;
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
			let query = "api/hack?page=" + (page - 1) + "&size="
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
				$("#hackList").html("");
				hackList = hackPage.content;
				hackList.forEach(function(hackItem, index, arr) {
					hackItem.index = index;
					$("#hackItem").tmpl(hackItem).appendTo("#hackList");
				});
			});
		} else
			page = 1;

		$("#hackPagination").html("");

		this.pages.forEach(function(number, index, arr) {
			pageNumber.number = number;

			$("#paginationItem").tmpl(pageNumber).appendTo("#hackPagination");
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

var pageHandler
var totalPages
var numberOfElements

function loadData(isFirst) {

	let query = "api/hack?size=2";
	let hackPageParams;

	if (filterQueryString != "")
		query += "&" + filterQueryString;

	fetch(query, {
		method : 'GET',
		headers : getDefaultHeaders(),
		credentials : "same-origin"
	}).then(function(response) {
		return response.json();

	}).then(
			function(hackPage) {
				$("#hackList").html("");
				hackList = hackPage.content;

				let companyNames = new Array();
				hackList.forEach(function(hackItem, index, arr) {
					hackItem.index = index;
					$("#hackItem").tmpl(hackItem).appendTo("#hackList");

					if (isFirst) {
						var objName = hackItem.company.companyName;
						obj = companyNames.find(function(name, index, array) {
							if (name == objName)
								return true;
							return false;

						});

						if (obj == null) {
							companyNames.push(hackItem.company.companyName);

							$('#compNameInput').append(
									$("<option></option>").attr("value",
											hackItem.company.companyName).text(
											hackItem.company.companyName));

						}

					}

				});

				if (isFirst)
					loadTags();

				totalPages = hackPage.totalPages;
				numberOfElements = hackPage.numberOfElements;

				window.pageHandler = new PageHandler();

			});

}

function loadTags() {

	fetch("/api/tags", {
		method : 'GET',
		headers : getDefaultHeaders(),
		credentials : "same-origin"
	}).then(function(response) {
		return response.json();

	}).then(function(loadedTags) {
		let hackTags;
		hackTags = loadedTags[0];
		hackTags.forEach(function(tag, index, arr) {

			tags.push({
				"id" : tag.id,
				"tagName" : tag.tagName,
				"category" : "skill"
			});
			$("#tagItem").tmpl(tag).appendTo("#tag_skills");

		})

		hackTags = loadedTags[1];
		hackTags.forEach(function(tag, index, arr) {

			tags.push({
				"id" : tag.id,
				"tagName" : tag.tagName,
				"category" : "scope"
			});

			$("#tagItem").tmpl(tag).appendTo("#tag_scope");

		})
	});

}

function showHackInformation(index) {

	var mas = hackList[index].placeCoords.split(',')
	//var coords= mas[1] + "," + mas[0]
	myMap.setCenter(mas[0], mas[1]);
	$("#full_hackName").html(hackList[index].title);
	$("#full_description").html(hackList[index].description);
	$("#full_place").html(hackList[index].place);
	$("#full_date").html(hackList[index].startDate);
	$("#full_dutarion").html(hackList[index].duration);
	$("#full_site").html(hackList[index].site);
	$("#full_auditory").html(hackList[index].auditory);
	$("#companyStatus").html(hackList[index].company.status[0]);
	if (hackList[index].company.status[0] == "LEGAL")
		$("#companyStatus").removeClass("text-warning")
				.addClass("text-success");

	$("#companyName").html(hackList[index].company.companyName);
	$("#companyAbout").html(hackList[index].company.about);
	$("#showCompany").click(function() {
		return showCompanyProfile();
	});

	$("#hack-tags").html("");
	hackList[index].skillTags.forEach(function(tag, index, arr) {
		$("#tag-info").tmpl(tag).appendTo("#hack-tags");
	});

	hackList[index].scopeTags.forEach(function(tag, index, arr) {
		$("#tag-info").tmpl(tag).appendTo("#hack-tags");
	});

	$('#fullHackInfo').modal("show");

	return false;
}

function shotInfo() {
	$('#fullHackInfo').modal("show");
}

function showCompanyProfile() {
	$('#fullHackInfo').modal("hide");
	$('#companyProfile').modal("show");
	return false;
}

var tags = []
var activeTags = []

function addTag(id) {

	if (!activeTags.includes(id)) {
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
	} else {
		removeTag(id);
	}

	return false;
}

function removeTag(id) {
	tag_act_ID = "#tag_act_" + id;
	tagID = "#tag_" + id;

	$(tag_act_ID).remove();
	activeTags.splice(activeTags.indexOf(id), 1);
	$(tagID + "_button").removeClass("bg-success").addClass("bg-dark");

	return false;
}

function clearAllTags() {

	let copy = Array.from(activeTags);

	for (i = 0; i < copy.length; ++i) {
		id = copy[i];
		removeTag(id)
	}

	return false;
}

var filterQueryString = "";
function findNewHackList() {

	filterQueryString = "";

	// --- Добавляем порядок сортировки
	var sort = new Object();
	let sortDirection = $('input[name=options]:checked', '#direction').val();

	switch (sortDirection) {
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

	switch (sortParam) {
	case "1":
		sort.property = "name";
		break;
	case "2":
		sort.property = "startDate";
	}

	filterQueryString += "sort=" + JSON.stringify(sort);
	// ---

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

	// --- Добавляем имя

	if ($("#hackName").val() != "")
		filters.push({
			"property" : "name",
			"value" : $("#hackName").val()
		});

	if ($("#compNameInput").val() != "")
		filters.push({
			"property" : "companyName",
			"value" : $("#compNameInput").val()
		});

	filterQueryString += "&filter=" + JSON.stringify(filters);

	// ---

	// -- Загружаем новый список хакатонов

	alert(filterQueryString)

	filterQueryString = encodeURI(filterQueryString);
	loadData(false);

	// --

	return false;

}

function changeColor(newAct, oldAct) {
	$("#" + newAct).css("color", "yellow");
	$("#" + oldAct).css("color", "white");
}

/*ymaps.ready(function() {
	var myMap = new ymaps.Map('map', {
		center : [ 55.661574, 37.573856 ],
		controls : [ 'geolocationControl' ],
		zoom : 16
	}, {
		searchControlProvider : 'yandex#search'
	});

	// Моя метка
	hackathonPlace = new ymaps.Placemark(myMap.getCenter(), {
		hintContent : 'Место проведения хакатона'
	}, {
		preset : "islands#circleDotIcon",
		iconColor : '#ff0000'
	}
	);

	myMap.geoObjects.add(hackathonPlace);
});*/


var myMap;
ymaps.ready(init);
function init(){ 
    // Создание карты.    
    myMap = new ymaps.Map("map", {
        center: [55.76, 37.64],
        controls : [ ],
        zoom: 16
    });
    
   var hackathonPlace = new ymaps.Placemark(myMap.getCenter(), {
		hintContent : 'Место проведения хакатона'
	}, {
		preset : "islands#circleDotIcon",
		iconColor : '#ff0000'
	}
	);

	myMap.geoObjects.add(hackathonPlace)
}
