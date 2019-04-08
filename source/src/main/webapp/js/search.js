//search result in table 

//function result of my Search
function mysubmit() {
	var departure = $('#departure').val();
	var date = $('#date').val();
	$.ajax({
		type: "post",
		url: '/ws/flight/search',
		data: {
			'departure': departure,
			'date': date
		},
		dataType: "json",
		success: function(data) {

			$('#filter').css("display","block");
			$('#input_search').hide();
			$('#departure_filter').val(departure);
			$('#date_filter').val(date);
			var tbl = "<table class=\"table table-striped custab\">\r\n" +
				"               <thead>\r\n" +
				"                  <tr>\r\n" +
				"                     <th>ID</th>\r\n" +
				"                     <th>Departure</th>\r\n" +
				"                      <th>Destination</th>\r\n" +
				"                     <th>Date</th>\r\n" +
				"                     <th>Seat</th>\r\n" +
				"                     <th>Price</th>\r\n" +
				"                     <th class=\"text-center\">Action</th>\r\n" +
				"                  </tr>\r\n" +
				"               </thead>"

			for (var i = 0; i < data.length; i++) {
				console.log(data[i]);
				var obj = JSON.parse(data[i]);
				var td0 = "<tr><td>" + obj.id + "</td>";
				var td1 = "<td>" + obj.departure + "</td>";
				var td1_5="<td>" + obj.arrival + "</td>";
				var td2 = "<td>" + moment(obj.date).format("MMMM Do YYYY") + "</td>";
				var td3 = "<td>" + obj.seat + "</td>";
				var td4 = "<td>" + obj.price + "</td>";
				var td5 = "<td class=\"text-center\"><a class='btn btn-info btn-xs' href=\"#\"><span class=\"glyphicon glyphicon-ok\"></span> See</a> </td></tr>"

				tbl += td0 + td1 +td1_5+ td2 + td3 + td4 + td5;

			}
			tbl += "</table>"
			document.getElementById("div1").innerHTML = "";
			$("#div1").append(tbl);

		},
		error: function(){
			alert("can't find coresponding flights");
			
		}
	})
}

$(function() {
	var today = moment().format("YYYY-MM-DD");
	document.getElementById('date').setAttribute("min", today);
	
});




//function result of filter
function filter() {
	var departure = $('#departure_filter').val();
	var date = $('#date_filter').val();
	var destination=$('#destination_filter').val();
	var seat=$('#seat_filter').val();
	console.log('departure'+departure+
			'arrival'+destination+
			'seat'+seat+
			'date'+ date);
	$.ajax({
		type: "post",
		url: '/ws/flight/filter',
		data: {
			'departure':departure,
			'arrival':destination,
			'seat':seat,
			'date':date
		},
		dataType: "json",
		success: function(data) {
			if(data.length==0){
				alert("sorry, no corresponding flight found.");
			}
			var  tbl = "<table class=\"table table-striped custab\">\r\n" +
			"               <thead>\r\n" +
			"                  <tr>\r\n" +
			"                     <th>ID</th>\r\n" +
			"                     <th>Departure</th>\r\n" +
			"                      <th>Destination</th>\r\n" +
			"                     <th>Date</th>\r\n" +
			"                     <th>Seat</th>\r\n" +
			"                     <th>Price</th>\r\n" +
			"                     <th class=\"text-center\">Action</th>\r\n" +
			"                  </tr>\r\n" +
			"               </thead>"
			
			for (var i = 0; i < data.length; i++) {
				console.log(data[i]);
				var obj = JSON.parse(data[i]);
				var td0 = "<tr><td>" + obj.id + "</td>";
				var td1 = "<td>" + obj.departure + "</td>";
				var td1_5="<td>" + obj.arrival + "</td>";
				var td2 = "<td>" + moment(obj.date).format("MMMM Do YYYY") + "</td>";
				var td3 = "<td>" + obj.seat + "</td>";
				var td4 = "<td>" + obj.price + "</td>";
				var td5 = "<td class=\"text-center\"><a class='btn btn-info btn-xs' href=\"#\"><span class=\"glyphicon glyphicon-ok\"></span> See</a> </td></tr>";
				tbl += td0 + td1 +td1_5+ td2 + td3 + td4 + td5;


			}
			tbl += "</table>"
			document.getElementById("div1").innerHTML = "";
			$("#div1").append(tbl);
		},
		error: function(error){
			alert("can't find coresponding flights "+error);
			
		}
	})
}

