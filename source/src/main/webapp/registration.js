function mysubmit1() {
	var usr = $('#usr').val();
	var pwd = $('#pwd').val();
	$.ajax({
		type : "put",
		url : '/ws/user/reg',
		data : {
			'id' : usr,
			'pwd' : pwd
		},
		dataType : "json",
		success : function(data) {
			if (data.result == 'ok') {
				sessionStorage.setItem("user", usr)
				location.href = 'http://localhost:8081/login.html';
			} else {
				alert(data.result);
			}

		}
	})
}
