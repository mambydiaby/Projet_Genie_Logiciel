$('#register').click(function() {
	var passenger = {
		id: $('#usr').val(),
		pwd: $('#pwd').val(),
		firstName: $('#fname').val(),
		lastName: $('#lname').val(),
		email: $('#email').val()
	}
	$.ajax({
		url: '/ws/user/reg',
		type: 'put',
		dataType: 'json',
		contentType: 'application/json',
		data: JSON.stringify(passenger),

		success: function(data) {
			if (data.result == 'ok') {
				
				//location.replace("search.html");
			} else {
				//alert(data.result);
				$('#loginerror').show();
				$('#loginerror').html(data.result);
				$("#loginerror").css("display", "block");
				$('#loginerror').fadeIn();
				$('#loginerror').show();
			}
		}

	})
})

$('#registerp').click(function () {
	var pilote = {
		id: $('#usr1').val(),
		pwd: $('#pwd1').val(),
		firstName: $('#fname1').val(),
		lastName: $('#lname1').val(),
		email: $('#email1').val(),
		experience: $('#exp').val(),
		qualification: $('#qual').val() 
	}
	$.ajax({
		url: '/ws/user/regpilote',
		type: 'put',
		dataType: 'json',
		contentType: 'application/json',
		data: JSON.stringify(pilote),

		success: function(data) {
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
})
