function login() {
	var usr = $('#usr').val();
	var pwd = $('#pwd').val();
	$.ajax({
		type: "post",
		url: '/ws/user/login',
		data: {
			'id': usr,
			'pwd': pwd
		},
		dataType: "json",
		success: function(data) {
			if (data.result == 'ok') {
				sessionStorage.setItem("user", usr)
				sessionStorage.setItem("type", 'Passenger');
				var prevLink = document.referrer;
				if($.trim(prevLink)==''){
					location.href = '../test.html';
				}else{
					if(prevLink.indexOf('detail')!=-1){		
						location.href = '../flight_detail.html';
					}
					else{
						location.href = '../test.html';
					}
				}
				location.replace("../test.html");
			} else if (data.result == 'okp') {
				sessionStorage.setItem("user", usr)
				sessionStorage.setItem("type", 'Pilot');
				var prevLink = document.referrer;
				if($.trim(prevLink)==''){
					location.href = '../testp.html';
				}else{
					if(prevLink.indexOf('detail')!=-1){		
						location.href = '../flight_detail.html';
					}
					else{
						location.href = '../testp.html';
					}

				}
				//location.replace("http://localhost:8081/testp.html");
			} else {
				console.log(data.result);
				 $('#loginerror').show();
				 $('#loginerror').html(data.result);
				 $("#loginerror").css("display", "block");
				 $('#loginerror').fadeIn();
				 $('#loginerror').show();
			}
		},
		error:function(error){
			alert("error");
		
		}
	})
}
