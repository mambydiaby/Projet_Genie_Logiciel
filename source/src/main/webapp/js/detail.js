
$(document).ready(function() {
	var id=localStorage.getItem('flight_info')	;
	
	$.ajax({
		type: "get",
		url: '/ws/flight/getbyid/'+id,
		success: function(data) {
			$('.arrival').html(data.arrival);
			$('.departure').html("Departure :"+data.departure);
			$('.date').html("date: "+data.date);
			$('.duration').html("Duration: "+data.duration);
			$('.price').html(data.price);
			var des=data.arrival+"";
			var url1="../img/"+des.toLowerCase()+".jpg";
			console.log(url1);
			$('body').css('background-image', "url(\""+url1+ "\")");
			updatePilot(data.pilotId);
		},
		error: function(){
			alert("can't find coresponding flights");
		}
	})
	  
});
function updatePilot(data){
	$.get( "/ws/user/profile/"+data, function( id ) {
		  $( ".pilot" ).html(id.firstName+" "+id.lastName);
		});

}