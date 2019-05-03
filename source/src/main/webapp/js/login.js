/**
 * 
 * verify login account and pwd
 * 
 * then Set sessionStorage
 * 
 * @returns use referrer to jump to several different pages after login
 */
function login() {
	var usr = $('#usr').val();
	var pwd = $('#pwd').val();
	$.ajax({
		type : "post",
		url : '/ws/user/login',
		data : {
			'id' : usr,
			'pwd' : pwd
		},
		dataType : "json",
		success : function(data) {
			if (data.result == 'ok') {
				sessionStorage.setItem("user", usr);
				sessionStorage.setItem("userId", usr);
				sessionStorage.setItem("type", 'passenger');
				var prevLink = document.referrer;
				if ($.trim(prevLink) == '') {
					location.href = '../test.html';
				} else {
					if (prevLink.indexOf('detail') != -1) {
						location.href = '../flight_detail.html';
					} else {
						location.href = '../test.html';
					}
				}
				location.replace("../test.html");
			} else if (data.result == 'okp') {
				sessionStorage.setItem("user", usr);
				sessionStorage.setItem("userId", usr);
				sessionStorage.setItem("type", 'pilot');
				var prevLink = document.referrer;
				if ($.trim(prevLink) == '') {
					location.href = '../testp.html';
				} else {
					if (prevLink.indexOf('detail') != -1) {
						location.href = '../flight_detail.html';
					} else {
						location.href = '../testp.html';
					}

				}
				// location.replace("http://localhost:8081/testp.html");
			} else {
				alert(data.result);
				$('#loginerror').show();
				$('#loginerror').html(data.result);
				$("#loginerror").css("display", "block");
				$('#loginerror').fadeIn();
				$('#loginerror').show();
			}
		},
		error : function(error) {
			alert("error");

		}
	})
}
