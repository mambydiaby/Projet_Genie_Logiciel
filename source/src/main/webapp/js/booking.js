function mysubmit() {
	var departure = $('#departure').val();
	var date = $('#date').val();
	$.ajax({
		type : "post",
		url : '/ws/flight/search',
		data : {
			'departure' : departure,
			'date' : date
		},
		dataType : "json",
		success : function(data) {
			
				alert(data);
			

		}
	})
}
