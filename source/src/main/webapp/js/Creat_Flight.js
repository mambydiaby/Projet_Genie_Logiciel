$(function(){
$('#submitf').click(function() {
		var empty=0;
		$(".form-control").each(function(){
	        var val = $(this).val(); 
	        var id=$(this).attr('id');
	        if(val.trim()==""&&empty==0&&id!="ignore"){
				alert("empty input : "+id);
				empty=1;
	        }
	    });
		if(empty==1)
			return;
    	var n1=Math.ceil(Math.random()*100)+Math.ceil(Math.random()*100);
    	var n2=Math.ceil(Math.random()*100)+Math.ceil(Math.random()*100)+Math.ceil(Math.random()*100);
    	var n3=Math.ceil(Math.random()*100);
		var rand_id="GG"+(n1*n2)%300+""+n3;
		var user_id=sessionStorage.getItem("user");
    	var newFlight = {
    		id:rand_id,
    		pilotId:user_id,
    		seat: $('#places').val(),
    		passengerId:[],
    		departure: $('#departure').val(),
    		arrival: $('#arrival').val(),
    		date:$('#date').val(),
    		time:"00:00",
    		duration: $('#duration').val(),
    		price: $('#price').val(),
    		info:$('#description').val(),
    		privateInfo:$('#descriptionPrivate').val(),
    		trajet:$('#trajet').val()
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