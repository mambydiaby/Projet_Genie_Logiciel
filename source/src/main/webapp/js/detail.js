
$(document).ready(function() {
	var id=sessionStorage.getItem('flight_info');
	var user=sessionStorage.getItem("user");
	$(".lds-hourglass").css("display","none");
	if(user!=null){
		$('#login_ref').hide();
	}
	$.ajax({
		type: "get",
		url: '/ws/flight/getbyid/'+id,
		success: function(data) {
			$('.arrival').html(data.arrival);
			$('#trajet').html("trajet: "+data.trajet);
			$('#infoPrivate').html("private info: "+data.privateInfo);
			$('#info').html("more info: "+data.info);
			$('.departure').html("Departure:"+data.departure);
			$('.date').html("date: "+data.date);
			$('.duration').html("Duration: "+data.duration);
			$('.price').html(data.price);
			var des=data.arrival+"";
			var url1="../img/"+des.toLowerCase()+".jpg";
			console.log(url1);
			$('body').css('background-image', "url(\""+url1+ "\")");
			updatePilot(data.pilotId);
			privateInfo(id,user);
		},
		error: function(){
			alert("can't find coresponding flights");
		}
	}) 
});


function updatePilot(data){
	$.get( "/ws/user/profile/"+data, function( id ) {
		  $( ".pilot" ).html("Pilot : "+id.firstName+" "+id.lastName);
		});
}

function privateInfo(id,user){
	$('#infoPrivate').hide();
	$.get("/ws/flight/getbyid/"+id , function(data ){
		var isPilot=(data.pilotId==user);
		
		console.log(user+data.passengerId.indexOf(user));

		var isPassenger=(data.passengerId.indexOf(user)!=-1);
		if(isPilot||isPassenger){
			$('#infoPrivate').show();
		}
	});
}


function checkSeat(id_flight){
	var user=sessionStorage.getItem("user");
	var regiId=id_flight+user+$('#seats').val()+Math.floor(Math.random() * 55);
	$.get( '/ws/flight/getbyid/'+id_flight, function( flight ) {
		var num=$('#seats').val();
		var	only=flight.seat;
		  	if(num>only){
		  		$(".lds-hourglass").css("display","none");
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
								
							}
						}
					})
			}
		});
}

function bookFlight(){
	$(".lds-hourglass").css("display","blocked");
	var id_flight=sessionStorage.getItem('flight_info');
	var user=sessionStorage.getItem("user");

	if(user==null){
		alert("please Login! redirection to login page...");
		location.replace("../login.html");
	}else{
		checkSeat(id_flight);

	}

}

