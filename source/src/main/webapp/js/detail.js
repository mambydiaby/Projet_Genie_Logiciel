
$(document).ready(function() {
	var id=sessionStorage.getItem('flight_info');
	var user=sessionStorage.getItem("user");
	sessionStorage.setItem("userId",user);
	if(user!=null){
		$('#login_ref').hide();
	}
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


function checkSeat(id_flight){
	var user=sessionStorage.getItem("user");
	var regiId=id_flight+user+$('#seats').val();
	$.get( '/ws/flight/getbyid/'+id_flight, function( flight ) {
		var num=$('#seats').val();
		var	only=flight.seat;
		  	if(num>only){
				alert('not enough seats! only '+ only+' seats left for this flight' );
			}else{
				var regi = {
						id: regiId,
						flightId: id_flight,
						passengerId: user,
						seat: $('#seats').val()
					}
					$.ajax({
						url: "/ws/reservation/new",
						type: 'put',
						dataType: 'json',
						contentType: 'application/json',
						data: JSON.stringify(regi),
						success: function(data) {
							if (data.result == 'success !') {
								console.log(data);
								location.href = 'http://localhost:8081/congratuation.html';
							} else {
								$('#loginerror').show();
								$('#loginerror').html(data.result);
								$("#loginerror").css("display", "block");
								$('#loginerror').fadeIn();
								$('#loginerror').show();
							}
						}

					})
			}
		});

}
function bookFlight(){
	var id_flight=sessionStorage.getItem('flight_info');
	var user=sessionStorage.getItem("user");

	if(user==null){
		alert("please Login! redirection to login page...");
		location.replace("../login.html");
	}else{
		checkSeat(id_flight);

	}

}

