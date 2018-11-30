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

$("#events").addClass('text-warning');

function loadEventInformation() {
    fetch("api/event?eventType=1&ownerID=7eaf7c29-8914-4038-b6cf-7530a42bbead", {
                method : 'GET',
                headers : getDefaultHeaders(),
                credentials : "same-origin"
            }).then(function(response) {
                return response.json();
            }).then(function (value) {
                // console.log(value[0][0].message);
                $("#message").html(value[0][0].message);

        })
    $('#userProfile').css('visibility', 'visible');
}



// var eventList;
// var eventPage;
//
// function PageHandler() {
//
//     this.createPages = function() {
//
//         this.maxPage = totalPages;
//         this.pageSize = numberOfElements;
//         this.pages = [];
//
//         if (this.maxPage > 3)
//             this.pages = [ 1, 2, "..", this.maxPage ];
//         else if (this.maxPage == 3)
//             this.pages = [ 1, 2, 3 ]
//         else if (this.maxPage == 2)
//             this.pages = [ 1, 2 ];
//         else
//             this.pages = [ 1 ];
//
//         this.updatePagination(-1);
//
//     }
//     this.updatePagination = function(page) {
//
//         let pageNumber = {
//             number : 0
//         }
//
//         if (page != -1) {
//             let query = "api/event?eventType=1&ownerID=7eaf7c29-8914-4038-b6cf-7530a42bbead&page=" + (page - 1) + "&size="
//                 + this.pageSize;
//
//
//             fetch(query, {
//                 method : 'GET',
//                 headers : getDefaultHeaders(),
//                 credentials : "same-origin"
//             }).then(function(response) {
//                 return response.json();
//             }).then(
//                 function(eventPage) {
//                     for(var i = 0; i<eventPage.length; i++){
//                         eventList = eventPage[i];
//                         eventList.forEach(function (eventItem, index, arr) {
//                             $("eventItem").tmpl(eventItem).appendTo("#eventList")
//                         })}})
//         }
//         else
//         {
//             page = 1;
//         }
//         $("#eventPagination").html("");
//
//         this.pages.forEach(function(number, index, arr) {
//             pageNumber.number = number;
//
//             $("#paginationItem").tmpl(pageNumber).appendTo("#eventPagination");
//             if (number == page) {
//                 page_id = "#page_" + page;
//                 $(page_id).removeClass("bg-dark").removeClass("text-light")
//                     .addClass("bg-warning").addClass("text-dark");
//             }
//         });
//
//     }
//
//     this.createPages();
// }
// function changePage(page) {
//
//     let max = window.pageHandler.maxPage;
//
//     if (page == 1) {
//         if (max > 3)
//             window.pageHandler.pages = [ 1, 2, "..", max ]
//         else if (max == 1)
//             window.pageHandler.pages = [ 1 ]
//         else if (max == 2)
//             window.pageHandler.pages = [ 1, 2 ]
//         else if (max == 3)
//             window.pageHandler.pages = [ 1, 2, 3 ]
//     } else if (page == 2) {
//         if (max > 4)
//             window.pageHandler.pages = [ 1, 2, 3, "..", max ]
//         else if (max == 2)
//             window.pageHandler.pages = [ 1, 2 ]
//         else if (max == 3)
//             window.pageHandler.pages = [ 1, 2, 3 ]
//         else if (max == 4)
//             window.pageHandler.pages = [ 1, 2, 3, 4 ]
//     }
//
//     else if (page == 3) {
//         if (max > 5)
//             window.pageHandler.pages = [ 1, 2, 3, 4, "..", max ]
//         else if (max == 3)
//             window.pageHandler.pages = [ 1, 2, 3 ]
//         else if (max == 4)
//             window.pageHandler.pages = [ 1, 2, 3, 4 ]
//         else if (max == 5)
//             window.pageHandler.pages = [ 1, 2, 3, 4, 5 ]
//
//     } else if (page == max)
//         window.pageHandler.pages = [ 1, "..", max - 1, max ]
//     else if (page == max - 1)
//         window.pageHandler.pages = [ 1, "..", max - 2, max - 1, max ]
//     else if (page == max - 2)
//         window.pageHandler.pages = [ 1, "..", max - 3, max - 2, max - 1, max ]
//     else
//         window.pageHandler.pages = [ 1, "..", page - 1, page, page + 1, "..",
//             max ]
//
//     window.pageHandler.updatePagination(page);
//     return false;
// }
//
// // function findNewEventList() {
// //
// //     // --- Добавляем имя события
// //
// //     if ($("#eventType").val() != "")
// //         filters.push({
// //             "property" : "type",
// //             "value" : $("#eventType").val()
// //         });
// //     // --- Добавляем имя компании
// //     if ($("#messageInput").val() != "")
// //         filters.push({
// //             "property" : "message",
// //             "value" : $("#messageInput").val()
// //         });
// //
// //
// //     // // --- Добавляем имя города
// //     // if ($("#cityNameInput").val() != "")
// //     //     filters.push({
// //     //         "property" : "cityName",
// //     //         "value" : $("#cityNameInput").val()
// //     //     });
// //
// //
// //     filterQueryString += "&filter=" + JSON.stringify(filters);
// //
// //     // ---
// //
// //     // -- Загружаем новый список хакатонов
// //
// //     alert(filterQueryString)
// //
// //     filterQueryString = encodeURI(filterQueryString);
// //     loadData(false);
// //
// //     // --
// //
// //     return false;
// //
// // }
