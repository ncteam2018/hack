function getDefaultHeaders() {
  var headers = new Headers();
  headers.append("Content-Type", "application/json");
  return headers;
}

function createUserProfile() {

	var userAuth  = new function () {
	    this.login = document.getElementById("username").value;
	    this.password = document.getElementById("password").value;
	}
	
	var userData = new function () {
    this.userAuth = userAuth;
    this.email = document.getElementById("email").value;

    this.about = document.getElementById("about").value;
    this.active = true;
    this.city = document.getElementById("city").value;
    this.dateOfBirth = document.getElementById("dateOfBirth").value;
    this.firstName = document.getElementById("fname").value;
    this.middleName = document.getElementById("mname").value;
    this.lastName = document.getElementById("lname").value;

    if ($("input[name='gender']:checked").val() != 'M') {
      this.gender = "WOMAN";
    } else {
      this.gender = "MAN";
    }
    this.level = ["PUPIL"];

    this.faculty = document.getElementById("faculty").value;
    this.institution = document.getElementById("university").value;
    this.course = document.getElementById("course").value;

    this.placeOfWork = document.getElementById("placeOfWork").value;
    this.position = document.getElementById("position").value;

    this.phone = document.getElementById("phone").value;
    this.skype = document.getElementById("skype").value;
    
    this.companyData = null;
    if(document.getElementById("orgCheck").checked){
    	    var companyData = new Object();
  
    	    companyData.companyName = document.getElementById("companyName").value;
    	    companyData.about = document.getElementById("aboutCompany").value;
    
    	    this.companyData = companyData;		
    }

  };

  fetch("api/profile", {
    method: 'POST',
    headers: getDefaultHeaders(),
    body: JSON.stringify(userData),
    credentials: "same-origin"
  }).catch(function (err) {
    console.log('Ошибка регистрации, исправьте данные! - ', err);
  })
  .then(function (response) {

    if (response.status != 201) {
      $(function () {

        $('body').prepend(
            ' <div id="myModalFail" class="modal fade" tabindex="-1"><div class="modal-dialog modal-lg"> <div class="modal-content"> <div class="modal-header"> <h4 class="modal-title">Регистрация</h4></div><div class="modal-body">При регистрации произошла ошибка!</div><div class="modal-footer"><button class="btn btn-danger" data-dismiss="modal">Ок</button></div></div></div></div>');
        $('#myModalFail').modal("show");

      });
    } else {
      $(function () {
        $('body').prepend(
            ' <div id="myModal" class="modal fade" tabindex="-1"><div class="modal-dialog modal-lg"> <div class="modal-content"> <div class="modal-header"> <h4 class="modal-title">Регистрация</h4></div><div class="modal-body">Пользователь успешно создан!</div><div class="modal-footer"><button class="btn btn-success" data-dismiss="modal">Войти</button></div></div></div></div>');
        $('#myModal').modal("show");
        $('#myModal').on('hidden.bs.modal', function (event) {

          window.location.replace("/profile");
        });
      });

    }
  });

  return false;
}	

$("#orgCheck").change(function() {
    if(this.checked) 
    	$('#orgRegForm').css('display', 'block');
    else
    	$('#orgRegForm').css('display', 'none');
    
});

























