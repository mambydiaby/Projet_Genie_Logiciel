/**
 * jQuery function: happens when click on submit(create a flight) 1.prevent
 * empty inputs 2.generate random id 3.create empty passenger list -> easier for
 * DB and WS 4. !!!! time set to "00:00" cause no space for it ,ofc we can set
 * it. 5. Spell error Creat
 * 
 * @returns
 */
$(function() {
	$('#submitf').click(
			function() {
				var empty = 0;
				$(".form-control").each(function() {
					var val = $(this).val();
					var id = $(this).attr('id');
					if (val.trim() == "" && empty == 0 && id != "ignore") {
						alert("empty input : " + id);
						empty = 1;
					}
				});
			
				if (empty == 1)
					return;
				if( $('#price').val()<0|| $('#price').val().trim()==''){		
					alert('invalide field (price: '+$('#price').val()+')');
					return;
				}
				
				if( $('#places').val()<=0|| $('#places').val().trim()==''){		
					alert('invalide field (Seats: '+$('#places').val()+')');
					return;
				}
				var n1 = Math.ceil(Math.random() * 100)
						+ Math.ceil(Math.random() * 100);
				var n2 = Math.ceil(Math.random() * 100)
						+ Math.ceil(Math.random() * 100)
						+ Math.ceil(Math.random() * 100);
				var n3 = Math.ceil(Math.random() * 100);
				var rand_id = "GG" + (n1 * n2) % 300 + "" + n3;
				var user_id = sessionStorage.getItem("user");
				var newFlight = {
					id : rand_id,
					pilotId : user_id,
					seat : $('#places').val(),
					passengerId : [],
					departure : $('#departure').val(),
					arrival : $('#arrival').val(),
					date : $('#date').val(),
					time : $('#time').val(),
					duration : $('#duration').val(),
					price : $('#price').val(),
					info : $('#description').val(),
					privateInfo : $('#descriptionPrivate').val(),
					trajet : $('#trajet').val()
				}
				$.ajax({
					url : "/ws/flight/add",
					type : 'put',
					dataType : 'json',
					contentType : 'application/json',
					data : JSON.stringify(newFlight),

					success : function(data) {
						alert("successful!");
						location.replace("pilotNav.html");
					}

				})
			})
});
