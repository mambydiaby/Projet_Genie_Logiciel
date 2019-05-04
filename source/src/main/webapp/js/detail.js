/**
 * this function updates this page according to flightId and userId 1. hide
 * login button when already login 2. update background img and all info 3. get
 * pilot info according to his Id(get from flightId) 4. hide/show private info
 * 
 * @returns
 */
$(document).ready(function() {
	var id = sessionStorage.getItem('flight_info');
	var user = sessionStorage.getItem("user");
	$(".lds-hourglass").css("display", "none");
	if (user != null) {
		$('#login_ref').hide();
	}
	$.ajax({
		type : "get",
		url : '/ws/flight/getbyid/' + id,
		success : function(data) {
			if (user == data.pilotId) {
				$('#myBtn').hide();
				$('#myBtn2').show();
			} else {
				$('#myBtn').show();
				$('#myBtn2').hide();

			}
			$('.arrival').html(data.arrival);
			$('#trajet').html("Trajet: " + data.trajet);
			$('#infoPrivate').html("Private info: " + data.privateInfo);
			$('#info').html("More info: " + data.info);
			$('.departure').html("Departure: " + data.departure);
			$('.date').html("Departure Date: " + data.date);
            $('.time').html(" Departure Time: " + data.time);
            $('.seat').html("Seat available: " + data.seat);
			$('.duration').html("Duration: " + data.duration);
			$('#price').html(data.price);
			var des = data.arrival + "";
			var url1 = "../img/" + des.toLowerCase() + ".jpg";
			$('body').css('background-image', "url"+"\'(\'" + url1 + "\')\'");
			//document.body.style.backgroundImage = "url(\'+url1+\')";
			updatePilot(data.pilotId);
			privateInfo(id, user);
		},
		error : function() {
			alert("can't find coresponding flights");
		}
	})
});

/**
 * 
 * @param data
 *            flightId
 * @returns change the pilot name after finished
 */
function updatePilot(data) {
	$.get("/ws/user/profile/" + data, function(id) {
		$(".pilot").html("Pilot : " + id.firstName + " " + id.lastName);
		$("#p_email").html("Email address : " + id.email);
		$("#p_experience").html("Experience : " + id.experience);
		$("#p_qualification").html("Qulification : " + id.qualification);
	});
}





/**
 * 
 * @param id
 *            flightId
 * @param user
 *            userId
 * @returns hide/show private Info
 */
function privateInfo(id, user) {
	$('#infoPrivate').hide();
	$.get("/ws/flight/getbyid/" + id, function(data) {
		var isPilot = (data.pilotId == user);

		var isPassenger = (data.passengerId.indexOf(user) != -1);
		if (isPilot || isPassenger) {
			$('#infoPrivate').show();
		}
	});
}

/**
 * 
 * @param id_flight
 * @returns check if there are enough seats
 */
function checkSeat(id_flight) {
	var user = sessionStorage.getItem("user");
	var regiId = id_flight + user + $('#seats').val()
			+ Math.floor(Math.random() * 55);
	$
			.get(
					'/ws/flight/getbyid/' + id_flight,
					function(flight) {
						var num = $('#seats').val();
						var only = flight.seat;
						if (num > only) {
							$(".lds-hourglass").css("display", "none");
							alert('not enough seats! only ' + only
									+ ' seats left for this flight');
						} else {
							var regi = {
								id : regiId,
								flightId : id_flight,
								passengerId : user,
								seat : $('#seats').val()
							}
							$
									.ajax({
										url : "/ws/reservation/new",
										type : 'put',
										dataType : 'json',
										contentType : 'application/json',
										data : JSON.stringify(regi),
										success : function(data) {
											if (data.result == 'success !') {
												location.href = 'http://localhost:8081/congratuation.html';
											} else {

											}
										}
									})
						}
					});
}

/**
 * book function
 * 
 * @returns redirection to congratulation page
 * 
 */
function bookFlight() {
	$(".lds-hourglass").css("display", "blocked");
	var id_flight = sessionStorage.getItem('flight_info');
	var user = sessionStorage.getItem("user");

	if (user == null) {
		alert("please Login! redirection to login page...");
		location.replace("../login.html");
	} else {
		checkSeat(id_flight);

	}

}

/**
 * delete flight function
 * 
 * @returns
 * 
 */
function deleteFlight() {
	var id_flight = sessionStorage.getItem('flight_info');
	var user = sessionStorage.getItem("user");
	$.ajax({
		type : "get",
		url : '/ws/flight/getbyid/' + id_flight,
		success : function(data) {
			if (data.pilotId == user) {
				$.ajax({
					type : "delete",
					url : '/ws/flight/delete/' + id_flight,
					success : function(data2) {
						alert("sucessful!");
						location.replace("pilotNav.html");
					}
				})
			} else {
				alert('no rights to do that');
			}
		}
	});

}
$(function(){
	$('#pilot_info').hide();
$('#show_pilot').hover(function (){
	$('#pilot_info').toggle();
});

})