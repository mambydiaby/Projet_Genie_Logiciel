/**
 * this function updates this page according to flightId and userId 1. hide
 * login button when already login 2. update background img and all info 3. get
 * pilot info according to his Id(get from flightId) 4. hide/show private info
 * 
 * @returns
 */
$(document).ready(function() {
	setTimeout(affiche_map, 1000);
	var id = sessionStorage.getItem('flight_info');
	var user = sessionStorage.getItem("user");
	$(".lds-hourglass").css("display", "none");
	if (user != null) {
		$('.login_ref').html("Log Out");
		$('.login_ref').click(function(){
			sessionStorage.removeItem("type");
			sessionStorage.removeItem("user");
			alert("Log out successfully");
		})
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
			$('#arr').html(data.arrival);
			$('#trajet').html("Trajet: " + data.trajet);
			$('#infoPrivate').html("Private info: " + data.privateInfo);
			$('#info').html("More info: " + data.info);
			$('.departure').html("Departure: " + data.departure);
			$('#dep').html(data.departure);
			$('.date').html("Departure Date: " + data.date);
			$('.time').html(" Departure Time: " + data.time);
			$('.seat').html("Seat available: " + data.seat);
			$('.duration').html("Duration: " + data.duration);
			$('#price').html(data.price+"€");
			window.arrival=data.arrival;
			window.departure=data.departure;
			var des = data.arrival + "";
			var url1 = "../img/" + des.toLowerCase() + ".jpg";
			$('body').css('background-image', "url"+"\'(\'" + url1 + "\')\'");
			updatePilot(data.pilotId);
			privateInfo(id, user);
		},
		error : function() {
			alert("can't find coresponding flights");
		}
	})
});

function affiche_map() {
	var dep1 = document.getElementById("dep").firstChild.data;
	var arr1 = document.getElementById("arr").firstChild.data;

	var coord = {
			Paris:{lt:48.8534, ln:2.3488},
			Lyon: {lt:45.75, ln:4.85},
			Toulouse: {lt:43.6, ln:1.4333},
			Nancy: {lt:48.6833, ln:6.2},
			Limoges: {lt:45.85, ln:1.25},
			Strasbourg: {lt:48.5833, ln:7.75},
			Marseille: {lt:43.2961, ln:5.3699},
			Bordeaux: {lt:44.8333, ln:-0.5666},
			Nice: {lt: 43.7,ln: 7.25},
			Lille: {lt: 50.6333,ln: 3.0666},
			Nantes: {lt: 47.2166,ln:-1.55},
			Montpellier: {lt: 43.6, ln: 3.8833},
	};

	var x = (coord[dep1].lt + coord[arr1].lt) / 2.0;
	var y = (coord[dep1].ln + coord[arr1].ln) / 2.0;

	var dep=[coord[dep1].lt,coord[dep1].ln];
	var arr=[coord[arr1].lt,coord[arr1].ln];


	const myMap = L.map('map').setView([x,y], 6);

	L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token={accessToken}', {
		attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, <a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
		maxZoom: 18,
		id: 'mapbox.streets',
		accessToken: 'pk.eyJ1Ijoia3lsZXZpbGxlbmV1dmUiLCJhIjoiY2puOTZ0eXkyMDM3eDNzcHVzeGJhZmMxYyJ9.7oIopPnX7qwOmOkL7dsSCQ' }).
		addTo(myMap);
	var marker1 = L.marker(dep).addTo(myMap);
	marker1.bindPopup('<h3> ' + dep1 + ', FRA </h3>');
	var marker2 = L.marker(arr).addTo(myMap);
	marker2.bindPopup('<h3> ' + arr1 + ', FRA </h3>');
	L.polyline([dep,arr], {
		color: 'orange'
	}).addTo(myMap);

}

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
		$("#p_qualification").html("Qualification : " + id.qualification);
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
	if( $('#seats').val()<=0|| $('#seats').val().trim()==''){
	
		alert('invalide field (seats booked: '+$('#seats').val()+')');
		return;
	}
		
	var user = sessionStorage.getItem("user");
	var regiId = id_flight + user + $('#seats').val()+ Math.floor(Math.random() * 55);
	$.get(
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
					$.ajax({
						url : "/ws/reservation/new",
						type : 'put',
						dataType : 'json',
						contentType : 'application/json',
						data : JSON.stringify(regi),
						success : function(data) {
							if (data.result == 'success !') {
								location.href = 'http://localhost:8081/congratulation.html';
							} else {

							}
						}
					})
				}
			});
}

/**
 * book function,if not login ->login page
 * @returns redirection to congratulation page
 * 
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
