$(document).ready(function() {
	var formInputs = $('input[type="text"],input[type="password"]');
	formInputs.focus(function() {
		$(this).parent().children('p.formLabel').addClass('formTop');
		$('div#formWrapper').addClass('darken-bg');
		$('div.logo').addClass('logo-active');
	});
	formInputs.focusout(function() {
		if ($.trim($(this).val()).length == 0) {
			$(this).parent().children('p.formLabel').removeClass('formTop');
		}
		$('div#formWrapper').removeClass('darken-bg');
		$('div.logo').removeClass('logo-active');
	});
	$('p.formLabel').click(function() {
		$(this).parent().children('.form-style').focus();
	});
});

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
				sessionStorage.setItem("type",'Passenger');
				location.href = 'http://localhost:8081/test.html';
			} else if (data.result == 'okp') {
				sessionStorage.setItem("user", usr)
				sessionStorage.setItem("type",'Pilot');
				location.href = 'http://localhost:8081/testp.html';
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
}
