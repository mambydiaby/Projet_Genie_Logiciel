/**
 * Store obj to see more info in detail page
 * @param obj
 * @returns
 */
function storing(obj) {
	sessionStorage.setItem('flight_info', obj);
}


/**
 * to search only with departure and date
 * @returns
 */
function mysubmit() {
	var departure = $('#departure').val();
	var date = $('#date').val();
	$
	.ajax({
		type : "post",
		url : '/ws/flight/search',
		data : {
			'departure' : departure,
			'date' : date,

		},
		dataType : "json",
		success : function(data) {
			$('#filter').css("display", "block");
			$('#input_search').hide();
			$('#departure_filter').val(departure);
			$('#date_filter').val(date);
			var tbl = "<table id=\"itable1\" class=\"table table-striped custab\" >\r\n"
				+ "               <thead>\r\n"
				+ "                  <tr>\r\n"
				+ "                     <th>ID</th>\r\n"
				+ "                     <th>Departure</th>\r\n"
				+ "                      <th>Destination</th>\r\n"
				+ "                     <th>Date</th>\r\n"
				+ "                     <th>Seat</th>\r\n"
				+ "                     <th>Price</th>\r\n"
				+ "                     <th class=\"text-center\"></th>\r\n"
				+ "                  </tr>\r\n"
				+ "               </thead><tbody>"

				for (var i = 0; i < data.length; i++) {
					var obj = JSON.parse(data[i]);
					var td0 = "<tr><td>" + obj.id + "</td>";
					var td1 = "<td>" + obj.departure + "</td>";
					var td1_5 = "<td>" + obj.arrival + "</td>";
					var td2 = "<td>"
						+ moment(obj.date).format("YYYY-MM-DD")
						+ "</td>";
					var td3 = "<td>" + obj.seat + "</td>";
					var td4 = "<td>" + obj.price + "</td>";
					var td5 = "<td class=\"text-center\"><a onclick=\"storing('"
						+ obj.id
						+ "');\" target=\"_blank\" class='btn btn-info btn-xs' href=\"../flight_detail.html\" ><span class=\"glyphicon glyphicon-ok\"></span> More info...</a> </td></tr>"

						tbl += td0 + td1 + td1_5 + td2 + td3 + td4 + td5;

				}
			tbl += "</tbody></table>"
				document.getElementById("div1").innerHTML = "";
			$("#div1").append(tbl);
			$("#itable1").DataTable({
				searching : false
			});
		},
		error : function() {
			alert("can't find coresponding flights");
		}
	})
}

/**
 * set date format
 * @returns
 */
$(function() {
	var user=sessionStorage.getItem("user");
	if (user != null) {
		$('.login_ref').html("Log Out");
		$('.login_ref').click(function(){
			sessionStorage.removeItem("type");
			sessionStorage.removeItem("user");
			alert("Log out successfully");
		})
	}
	var today = moment().format("YYYY-MM-DD");
	document.getElementById('date').setAttribute("min", today);
});

//function result of filter
function filter() {
	var departure = $('#departure_filter').val();
	var date = $('#date_filter').val();
	var destination = $('#destination_filter').val();
	var seat = $('#seat_filter').val();
	$
	.ajax({
		type : "post",
		url : '/ws/flight/filter',
		data : {
			'departure' : departure,
			'arrival' : destination,
			'seat' : seat,
			'date' : date
		},
		dataType : "json",
		success : function(data) {
			if (data.length == 0) {
				alert("sorry, no corresponding flight found.");
			}
			var tbl = "<table  id=\"itable\" class=\"table table-striped custab\" >\r\n"
				+ "               <thead>\r\n"
				+ "                  <tr>\r\n"
				+ "                     <th>ID</th>\r\n"
				+ "                     <th>Departure</th>\r\n"
				+ "                      <th>Destination</th>\r\n"
				+ "                     <th>Date</th>\r\n"
				+ "                     <th>Seat</th>\r\n"
				+ "                     <th>Price</th>\r\n"
				+ "                     <th class=\"text-center\"></th>\r\n"
				+ "                  </tr>\r\n"
				+ "               </thead>"

				for (var i = 0; i < data.length; i++) {
					var obj = JSON.parse(data[i]);
					var td0 = "<tr><td>" + obj.id + "</td>";
					var td1 = "<td>" + obj.departure + "</td>";
					var td1_5 = "<td>" + obj.arrival + "</td>";
					var td2 = "<td>"
						+ moment(obj.date).format("YYYY-MM-DD")
						+ "</td>";
					var td3 = "<td>" + obj.seat + "</td>";
					var td4 = "<td>" + obj.price + "</td>";
					var td5 = "<td class=\"text-center\"><a onclick=\"storing('"
						+ obj.id
						+ "');\" target=\"_blank\" class='btn btn-info btn-xs' href=\"../flight_detail.html\" ><span class=\"glyphicon glyphicon-ok\"></span> More info...</a> </td></tr>"
						tbl += td0 + td1 + td1_5 + td2 + td3 + td4 + td5;
				}
			tbl += "</table>";
			document.getElementById("div1").innerHTML = "";
			$("#div1").append(tbl);
			$("#itable").DataTable({
				searching : false
			});
		},
		error : function(error) {
			alert("can't find coresponding flights " + error);

		}
	})
}
