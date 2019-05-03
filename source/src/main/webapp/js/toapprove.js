


/**


 *   When  finished action , refresh page.
 * @returns
 */
function back() {
	modal.style.display = "none";
	document.location.reload(true);
}
/**
 * approve a flight 
 * @param id flightId
 * @returns
 */
function approve(id) {
	var result = id;
	$.ajax({
		type : "post",
		url : "/ws/reservation/approve/" + id,
		success : function(data) {
			$('#name').html("");
			$('#seat_wanted').html("");
			$('#vec').html("success, the reservation has been approved!");
			$('#seat_left').html("");
			$('#date').html("");
			$('#arrival').html("");
			$('#departure').html("");
			$('#more').html("");
			$('#accept').attr("onclick", 'back();');
			$('#accept').attr("value", 'Ok');

		},
		error : function(error) {
			alert('error');
		},
	});
}
/**
 * dispprove a reservation
 * @param id flightId
 * @returns
 */
function disapprove(id) {

	var result = id;
	$.ajax({
		type : "delete",
		url : "/ws/reservation/disapprove/" + id,
		success : function(data) {
			$('#name').html("");
			$('#seat_wanted').html("");
			$('#vec').html(
					"success, the reservation has been disapproved/canceled!");
			$('#seat_left').html("");
			$('#date').html("");
			$('#arrival').html("");
			$('#departure').html("");
			$('#more').html("");

			$('#accept').attr("onclick", 'back();');
			$('#accept').attr("value", 'Ok');

		},
		error : function(error) {
			alert(error);
		},
	});
}

/**
 * little message box to comfirm,
 * show when click approve
 * @param id
 * @returns
 */
// When the user clicks the button, open the modal
function show(id) {
	$.get("/ws/reservation/getbyid/" + id, function(data) {
		var seat = data.seat;
		var flightId = data.flightId;
		var pas = data.passengerId;
		sessionStorage.setItem("flight_info", flightId);
		$('#more').attr("href", "flight_detail.html");
		$('#more').html("more information");
		$('#seat_wanted').html("Seats booked : " + seat);
		$('#vec').html("Accepting this reservation?");
		$.get("/ws/flight/getbyid/" + flightId, function(data2) {
			var seat = data2.seat;
			var date = data2.date;
			var arrival = data2.arrival;
			var departure = data2.departure;
			$('#seat_left').html("Seats left :" + seat);
			$('#date').html("date : " + date);
			$('#arrival').html("arrival : " + arrival);
			$('#departure').html("departure : " + departure);
		});
		$.get("/ws/user/profile/" + pas, function(data3) {
			var fn = data3.firstName;
			var ln = data3.lastName;
			$('#name').html("Passenger : " + fn + " " + ln);
		});
	});
	modal.style.display = "block";
	$('#accept').attr("onclick", 'approve(\'' + id + '\')');
	$('#accept').attr("value", 'Approve');

}
/**
  * little message box to comfirm,
 * show when click deny
 * @param id
 * @returns
 */
function show2(id) {
	$.get("/ws/reservation/getbyid/" + id, function(data) {
		var seat = data.seat;
		var flightId = data.flightId;
		var pas = data.passengerId;
		sessionStorage.setItem("flight_info", flightId);
		$('#more').attr("href", "flight_detail.html");
		$('#more').html("more information");
		$('#seat_wanted').html("Seats booked : " + seat);
		$('#vec').html("Denying/Disapproving this reservation?");
		$.get("/ws/flight/getbyid/" + flightId, function(data2) {
			var seat = data2.seat;
			var date = data2.date;
			var arrival = data2.arrival;
			var departure = data2.departure;
			$('#seat_left').html("Seats left :" + seat);
			$('#date').html("date : " + date);
			$('#arrival').html("arrival : " + arrival);
			$('#departure').html("departure : " + departure);
		});
		$.get("/ws/user/profile/" + pas, function(data3) {
			var fn = data3.firstName;
			var ln = data3.lastName;
			$('#name').html("Passenger : " + fn + " " + ln);
		});
	});
	modal.style.display = "block";
	$('#accept').attr("onclick", 'disapprove(\'' + id + '\')');
	$('#accept').attr("value", 'Disapprove');


	
}

/**
 * get all the reservations to be approved / disapproved
 * @returns
 */
$(function() {
	$
			.ajax({
				type : "get",
				url : '/ws/reservation/toapprove/'
						+ sessionStorage.getItem("user"),
				success : function(data) {
					var tbl = "<table id=\"itable\" class=\"table table-striped custab\">\r\n"
							+ "               <thead>\r\n"
							+ "                  <tr>\r\n"
							+ "                     <th>ID</th>\r\n"
							+ "                     <th>Flight</th>\r\n"
							+ "                     <th>Passenger</th>\r\n"
							+ "                     <th>Seat</th>\r\n"
							+ "                     <th class=\"text-center\">Action</th>\r\n"
							+ "                  </tr>\r\n"
							+ "               </thead>"
					for (var i = 0; i < data.length; i++) {
						var obj = JSON.parse(data[i]);
						var td0 = "<tr><td>" + obj.id + "</td>";
						var td1 = "<td>" + obj.flightId + "</td>";
						var td2 = "<td>" + obj.passengerId + "</td>";
						var td3 = "<td>" + obj.seat + "</td>";
						var td4 = "<td class=\"text-center\"><a class='btn btn-info btn-xs okbtn'  onclick=\"show(\'"
								+ obj.id
								+ "\');\"><span class=\"glyphicon glyphicon-ok\"></span> Accept</a> <a  onclick=\"show2(\'"
								+ obj.id
								+ "\');\" class=\"btn btn-danger btn-xs delbtn\"><span class=\"glyphicon glyphicon-remove\"></span> Disapprove</a></td></tr>"

						tbl += td0 + td1 + td2 + td3 + td4;

					}
					tbl += "</table>";
					document.getElementById("div1").innerHTML = "";
					$("#div1").append(tbl);
					$("#itable").DataTable();

				}
			});
});


/**
 *  Back button
 * @returns
 */
$(document).ready(function() {
	var type = sessionStorage.getItem("type");
	if (type == "passenger") {
		$('#back1').attr("href", "passengerNav.html");
	} else {
		$('#back1').attr("href", 'pilotNav.html');
	}
});
