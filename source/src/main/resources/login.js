function mysubmit(){
	var usr = $('#usr').val();
	var pwd = $('#pwd').val();
    $.ajax({
        type:"post",
        url:'/ws/user/login',
        data:{'id':usr,
			'pwd':pwd},
		dataType: "json",
        success:function (data) {
	    	if(data.result=='ok'){
	    		sessionStorage.setItem("user",usr)
	    		location.href='http://localhost:8081/test.html';
	    	}else{
		    	alert(data.result);
	    	}

        }
    })
}
