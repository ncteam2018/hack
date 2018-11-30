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
                $("#dateOfCreation").html(value[0][0].dateOfCreation);

        })
    $('#userProfile').css('visibility', 'visible');
}

function changeState(eventId, state){
    var q = "api/event/" + "eventId";
    fetch(q, {
            method : 'PUT',
            headers : getDefaultHeaders(),
            body: JSON.stringify(state)
        })
}

