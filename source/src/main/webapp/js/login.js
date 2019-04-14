function login() {
	var usr = $('#usr').val();
	var pwd = $('#pwd').val();
	console.log("usr"+usr+"\n");
	$.ajax({
		type: "post",
		url: '/ws/user/login',
		data: {
			'id': usr,
			'pwd': pwd
		},
		dataType: "json",
		success: function(data) {
			var data1 = JSON.parse(data);
			alert("parse"+data1.result+"\n");
			console.log(data.result);
			if (data.result == 'ok') {
			//	sessionStorage.setItem("user", usr)
				//sessionStorage.setItem("type", 'Passenger');
				//location.replace("http://localhost:8081/test.html");
			} else if (data.result == 'okp') {
				//sessionStorage.setItem("user", usr)
				//sessionStorage.setItem("type", 'Pilot');
				//location.replace("http://localhost:8081/testp.html");
			} else {
				console.log(data.result);
				// $('#loginerror').show();
				// $('#loginerror').html(data.result);
				// $("#loginerror").css("display", "block");
				// $('#loginerror').fadeIn();
				// $('#loginerror').show();
			}
		},
		error:function(error){
			alert(error);
		}
	})
}
