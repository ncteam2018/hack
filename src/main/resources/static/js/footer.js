
function showDevInfo()  {
	$("#aboutDevelopersInfo").modal("show");
	return false;
}

function showBugSend()  {
	$("#sendToUser2").attr("onclick",'return sendMessageToAdmin()');
	$("#sendEmailInfo").modal("show");
	return false;
}

function sendMessageToAdmin() {
	
	let Message = new function() {
		this.sender = Me.uuid;
		this.receiver = null;
		this.message = $("#messageTextarea2").val();
	}
	
	let query = '/api/profile/sendErrorMessage';
	fetch(query, {
		method : 'POST',
		headers : getDefaultHeaders(),
		body : JSON.stringify(Message),
		credentials : "same-origin"
	});
	
	$("#sendEmailInfo").modal("hide");
	$("#messageSendAlert").modal("show");
	
	return false;
}




