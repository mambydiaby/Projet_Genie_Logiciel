function send() {
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
				location.href = 'http://localhost:8081/login.html';
			} else {
				alert(data.result);
			}
		}

	})
}
