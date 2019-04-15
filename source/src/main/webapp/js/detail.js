
$(document).ready(function() {
	var id=localStorage.getItem('flight_info')	;
	
	$.ajax({
		type: "get",
		url: '/ws/flight/getbyid/'+id,
		success: function(data) {
			$('#arrival').html(data.arrival);
			$('#departure').html("Departure :"+data.departure);
			$('#date').html("date: "+data.date);
			$('#duration').html("Duration: "+data.duration);
			$('#price').html(data.price);
		},
		error: function(){
			alert("can't find coresponding flights");
		}
	})
	  
});
