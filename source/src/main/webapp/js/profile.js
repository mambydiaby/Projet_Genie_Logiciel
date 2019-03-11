$(function() {
	$('#profile-image1').on('click', function() {
		$('#profile-image-upload').click();
	});

	document.getElementById('utype').innerText = sessionStorage.getItem("type");
	
	$.ajax({
      url: '/ws/user/profile/'+sessionStorage.getItem("user"),
      type: 'get',

      success: function(data) {
          document.getElementById('usr').innerText =data.id;
           document.getElementById('fname').innerText =data.firstName;
          document.getElementById('lname').innerText =data.lastName;
           document.getElementById('email').innerText =data.email;
           document.getElementById('exp').innerText =data.experience;
           document.getElementById('qual').innerText =data.qualification;
           document.getElementById('name').innerText =data.firstName+' '+data.lastName;
        }
    })
  

});       