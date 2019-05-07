/**
 * get profile and changes html elements
 * 
 * unsolved img upload prob
 * 
 * @returns
 */

$(function() {
	var type = sessionStorage.getItem("type");
	if (type == "passenger") {
		$('#back1').attr("href", "passengerNav.html");
	} else if (type == "pilot") {
		$('#back1').attr("href", 'pilotNav.html');
	} else {
		location.replace('login.html');
	}

	$('#profile-image1').on('click', function() {
		$('#profile-image-upload').click();
	});

	document.getElementById('utype').innerText = sessionStorage.getItem("type");

	$.ajax({
		url : '/ws/user/profile/' + sessionStorage.getItem("user"),
		type : 'get',

		success : function(data) {
			document.getElementById('usr').innerText = data.id;
			document.getElementById('fname').innerText = data.firstName;
			document.getElementById('lname').innerText = data.lastName;
			document.getElementById('email').innerText = data.email;
			if (data.experience != null)
				document.getElementById('exp').innerText = data.experience;
			else {
				document.getElementById('exp').innerText = "---------";
				$('.exp').hide();

			}
			if (data.qualification != null)
				document.getElementById('qual').innerText = data.qualification;
			else {
				document.getElementById('qual').innerText = "---------";
				$('.qual').hide();

			}

			document.getElementById('name').innerText = data.firstName + ' '
			+ data.lastName;
		}
	})

});