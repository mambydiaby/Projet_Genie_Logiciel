$(function(){
$('#submitf').click(function() {
    	var n1=Math.ceil(Math.random()*100)+Math.ceil(Math.random()*100);
    	var n2=Math.ceil(Math.random()*100)+Math.ceil(Math.random()*100)+Math.ceil(Math.random()*100);
    	var n3=Math.ceil(Math.random()*100);
		var rand_id="GG"+(n1*n2)%300+""+n3;
		var user_id=sessionStorage.getItem("user");
    	var newFlight = {
    		id:rand_id,
    		pilotId:user_id,
    		seat: $('#places').val(),
    		passengerId:null,
    		departure: $('#departure').val(),
    		arrival: $('#arrival').val(),
    		date:"2020-09-01",
    		time:null,
    		duration: $('#duration').val(),
    		price: $('#price').val(),
    		info:$('#description').val()
    	}
    	console.log(newFlight);
    	$.ajax({
    		url: "/ws/flight/add",
    		type: 'put',
    		dataType: 'json',
    		contentType: 'application/json',
    		data: JSON.stringify(newFlight),
    	
    		success: function(data) {
				alert("successful!");
				location.replace("testp.html");
    		}

    	})
    })
});