/**
 * 
 * @returns


 */

function validateEmail(email) {
    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    
    return re.test(String(email).toLowerCase());
}

function CheckPassword(inputtxt) 
{ 
	var passw= new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})");
	//var passw= new RegExp("^.{7,}")
	return passw.test(String(inputtxt));
}

$('#register').click(function() {
	if(!CheckPassword($('#pwd').val())){
		alert('must contain at least 1 lowercase alphabetical, at least 1 uppercase alphabetical character,at least 1 numeric characterat ,at least one special character')
				

		
	}
	else if(validateEmail($('#email').val())){
	var passenger = {
			id : $('#usr').val(),
			pwd : $('#pwd').val(),
			firstName : $('#fname').val(),
			lastName : $('#lname').val(),
			email : $('#email').val()
	}
	$.ajax({
		url : '/ws/user/reg',
		type : 'put',
		dataType : 'json',
		contentType : 'application/json',
		data : JSON.stringify(passenger),

		success : function(data) {
			if (data.result == 'ok') {

				location.replace("login.html");
			} else {
				alert(data.result);
				$('#loginerror').show();
				$('#loginerror').html(data.result);
				$("#loginerror").css("display", "block");
				$('#loginerror').fadeIn();
				$('#loginerror').show();
			}
		}

	})
	}else{
		alert("email format error")
	}
})

$('#registerp').click(function() {
	if(!CheckPassword($('#pwd1').val())){
		console.log('wtf')
		alert('must contain at least 1 lowercase alphabetical, at least 1 uppercase alphabetical character,at least 1 numeric characterat ,at least one special character')

		
	}
else if(validateEmail($('#email1').val())){
	var pilote = {
			id : $('#usr1').val(),
			pwd : $('#pwd1').val(),
			firstName : $('#fname1').val(),
			lastName : $('#lname1').val(),
			email : $('#email1').val(),
			experience : $('#exp').val(),
			qualification : $('#qual').val()
	}
	$.ajax({
		url : '/ws/user/regpilote',
		type : 'put',
		dataType : 'json',
		contentType : 'application/json',
		data : JSON.stringify(pilote),

		success : function(data) {
			if (data.result == 'ok') {
				location.href = 'http://localhost:8081/login.html';
			} else {
				alert(data.result);
				$('#loginerror').show();
				$('#loginerror').html(data.result);
				$("#loginerror").css("display", "block");
				$('#loginerror').fadeIn();
				$('#loginerror').show();
			}
		}

	})
	}else{
		alert("email format error")
	}
})
