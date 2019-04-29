$(document).ready(function() {
	var id=sessionStorage.getItem('flight_info');
	var user=sessionStorage.getItem("user");
	sessionStorage.setItem("userId",user);
	$.ajax({
		type: "get",
		url: '/ws/flight/getbyid/'+id,
		success: function(data) {
			 $('#places').attr("placeholder",data.seat);
    		 $('#departure').attr("placeholder",data.departure);
    		 $('#arrival').attr("placeholder",data.arrival);
    		 $('#duration').attr("placeholder",data.duration);
    		 $('#price').attr("placeholder",data.price);
    		$('#description').attr("placeholder",data.description);
		},
		error: function(){
			alert("can't find coresponding flights");
		}
	}) 
});

function reset(){
	var id=sessionStorage.getItem('flight_info');
	var user=sessionStorage.getItem("user");
	sessionStorage.setItem("userId",user);
	$.ajax({
		type: "get",
		url: '/ws/flight/getbyid/'+id,
		success: function(data) {
			$('#places').attr("placeholder",data.seat);
    		$('#departure').attr("placeholder",data.departure);
    		$('#arrival').attr("placeholder",data.arrival);
    		$('#duration').attr("placeholder",data.duration);
    		$('#price').attr("placeholder",data.price);
    		$('#description').attr("placeholder",data.description);
		},
		error: function(){
			alert("can't find coresponding flights");
		}
	})
}



	function modify() {
		var flight_id = sessionStorage.getItem('flight_info');
		var user_id = sessionStorage.getItem("user");
		var f_seat;
		var f_passengerId;
		var f_departure;
		var f_arrival;
		var f_date;
		var f_time;
		var f_duration;
		var f_price;
		var f_info;
		$.ajax({
			type: "get",
			url: '/ws/flight/getbyid/'+flight_id,
			success: function(data) {
				f_seat=data.seat;
	    		f_passengerId=data.passengerId;
	    		f_arrival=data.arrival;
	    		f_departure=data.departure;
	    		f_duration=data.duration;
	    		f_price=data.price;
	    		f_description=data.description;
	    		f_date=data.date;
	    		f_info=data.info;
	    		f_time=data.time;
	    		console.log(data.info+f_seat);
	    		if($('#places').val()!='')
	    			f_seat=$('#places').val();
	    	

	    		if($('#departure').val()!='')
	    			f_departure=$('#departure').val();
	    		if($('#arrival').val()!='')
	    			f_arrival=$('#arrival').val();
	    		if($('#duration').val()!='')
	    			f_duration=$('#duration').val();
	    		if($('#price').val()!='')
	    			f_price=$('#price').val();
	    		if($('#description').val()!='')
	    			f_info=$('#description').val();
	    		var newFlight = {
	    			id : flight_id,
	    			pilotId : user_id,
	    			seat : f_seat,
	    			passengerId : f_passengerId,
	    			departure : f_departure,
	    			arrival : f_arrival,
	    			date : f_date,
	    			time : f_time,
	    			duration : f_duration,
	    			price : f_price,
	    			info : f_info
	    		}
	    		$.ajax({
	    			url : "/ws/flight/add",
	    			type : 'put',
	    			dataType : 'json',
	    			contentType : 'application/json',
	    			data : JSON.stringify(newFlight),

	    			success : function(data) {
	    				console.log(newFlight);
	    				alert("successful!");
	    				location.replace("testp.html");
	    			}

	    		})
			},
			error: function(){
				alert("can't find coresponding flights");
			}
		})
		
	}