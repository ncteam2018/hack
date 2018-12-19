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
var UserID = null;

$("#events").addClass('text-warning');
eventList = new Array();
function loadEventInformation() {
    fetch("/api/profile/me", {
        method : 'GET',
        headers : getDefaultHeaders(),
        credentials : "same-origin"
    }).then(function(response) {
        return response.json();
    }).then(function(myProfile) {
        UserID = myProfile.uuid;
        let query = "api/event?eventType=1&ownerID=" + UserID;
        fetch(query, {
            method : 'GET',
            headers : getDefaultHeaders(),
            credentials : "same-origin"
        }).then(function(response) {
            return response.json();
        }).then(function (value) {
            for (var i = 0; i < value.length; i++) {
                for (var j = 0; j < value[i].length; j++) {
                    eventList.push(value[i][j]);
                }
            }
            $("#eventList").html("");
            eventList.forEach(function(eventItem, index, arr) {
                eventItem.index = index;
                $("#eventItem").tmpl(eventItem).appendTo("#eventList");
            });
            // $("#message").html(value[0][0].message);
            // $("#dateOfCreation").html(value[0][0].dateOfCreation);
            //
            // $("#id").html(value[0][0].id);

        })
        // $('#loadingIcon').remove();
        $('#userProfile').css('visibility', 'visible');
    });

}



function changeState(eventId, state){
    var hackId1;
    var q = "api/event/" + eventId;
    fetch(q, {
        method : 'PUT',
        headers : getDefaultHeaders(),
        body: JSON.stringify(state)
    });
    var sender;

    query = "api/event/" + eventId;

    fetch(query, {
        method: 'GET',
        headers: getDefaultHeaders(),
        credentials: "same-origin"
    }).then(function (response) {
        return response.json();
    }).then(function (value) {
        sender = value.sender.uuid;
        hackId1 = value.resourceHackReference.uuid;
        var newEvent1 = new function () {
            this.message = "Заявка одобрена";
            this.hackID = eventId;
            this.teamId = null;
            this.reciever = sender;
        }
        var newEvent2 = new function () {
            this.message = "Заявка отклонена";
            this.hackID = eventId;
            this.teamId = null;
            this.reciever = sender;
        }

        q = "api/hack/"+hackId1+"/status";
        if(state == 1){
            fetch("api/event/userNotification",{
                method : 'POST',
                headers : getDefaultHeaders(),
                body: JSON.stringify(newEvent1)
            });
            hackState = "Active";

            fetch(q, {
                method : 'PUT',
                headers : getDefaultHeaders(),
                body: JSON.stringify(hackState)
            });
        }else{
            fetch("api/event/userNotification",{
                method : 'POST',
                headers : getDefaultHeaders(),
                body: JSON.stringify(newEvent2)
            });
            hackState = "Canceled";
            fetch(q, {
                method : 'PUT',
                headers : getDefaultHeaders(),
                body: JSON.stringify(hackState)
            });
        }

    });


}

