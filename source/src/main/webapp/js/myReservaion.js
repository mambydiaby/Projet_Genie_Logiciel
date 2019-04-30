$(function(){
	 $.ajax({
         type: "get",
         url: '/ws/reservation/myreservations/'+sessionStorage.getItem("user"),
         success: function(data) {
         //alert(data);
         	var tbl="<table id=\"itable\" class=\"table table-striped custab\">\r\n" + 
         "               <thead>\r\n" + 
         "                  <tr>\r\n" + 
         "                     <th>ID</th>\r\n" + 
         "                     <th>Flight</th>\r\n" + 
         "                     <th>Seat Booked</th>\r\n" + 
         "                     <th>State</th>\r\n" + 
         "                     <th class=\"text-center\">Action</th>\r\n" + 
         "                  </tr>\r\n" + 
         "               </thead>"
         
         for(var i=0;i<data.length;i++)
         {
             var obj = JSON.parse(data[i]);
             var td0="<tr><td>"+obj.id+"</td>";
             var td1="<td>"+obj.flightId+"</td>";
             var td3="<td>"+obj.seat+"</td>";
          
             var td3_5="<td>"+(obj.approved=="true"?"approved":"waiting")+"</td>";
             var td4="<td class=\"text-center\"><a  onclick=\"show(\'"+obj.id+"\');\"  	class='btn btn-info btn-xs' ><span class=\"glyphicon glyphicon-ok\" ></span> View</a>"

         tbl+=td0+td1+td3+td3_5+td4; 
           
         }
        	tbl += "</table>";
    			document.getElementById("div1").innerHTML = "";
    			$("#div1").append(tbl);
    			$("#itable").DataTable();

         }
         });

       	$(document).ready(function(){
       		var type=sessionStorage.getItem("type");
       		if(type=="passenger"){
       			$('#back1').attr("href","test.html");
       		}else{
       			$('#back1').attr("href",'testp.html');
       		}
       	});
       	
}) ; 
    

        function back(){
      	  modal.style.display = "none";
      	  document.location.reload(true);
        }
       
        
        function cancel(id){
      	  console.log("disapprove"+id);
      	  var result=id;
      	  $.ajax({
      		  type:"delete",
      		  url:"/ws/reservation/disapprove/"+id,
      		  success: function (data) {
    			  	console.log("success : "+data);
      				console.log(data.result);
        		  	$('#name').html("");	
        		  	$('#seat_wanted').html("");	
        		  	$('#vec').html("success, the reservation has been disapproved/canceled!");	
      		  		$('#seat_left').html("");
    	    	    $('#date').html("");
    	    	    $('#arrival').html("");
    	    	    $('#departure').html("");
    	    	    $('#accept').attr("onclick",'back();');
   	    	    $('#accept').attr("value",'Ok');

    		  },
    		  error:function(error){
  	    		alert(error);
    		  },   		  
    		});	 	    
      }
        // When the user clicks the button, open the modal 
       function show(id) {
      	 $.get( "/ws/reservation/getbyid/"+id, function( data ) {
  			var seat=data.seat;
  			var flightId=data.flightId;
      	     $('#seat_wanted').html("Seats booked : "+seat);
        	 $('#vec').html("Information flight");
        	 sessionStorage.setItem("flight_info",flightId);
 			$('#more').attr("href","flight_detail.html");
 			$('#more').html("more information");
      	     $.get( "/ws/flight/getbyid/"+flightId, function( data2 ) {
      				var seat=data2.seat;
      				var date=data2.date;
      				var arrival=data2.arrival;
      				var departure=data2.departure;
  	     			var pil=data2.pilotId;
      	    	     $('#seat_left').html("Seats left :" +seat);
      	    	     $('#date').html("date : "+date);
      	    	     $('#arrival').html("arrival : "+arrival);
      	    	     $('#departure').html("departure : "+departure);
      	    	  	 $.get( "/ws/user/profile/"+pil, function( data3 ) {
      	   				var fn=data3.firstName;
      	   				var ln=data3.lastName;
      	   	    	     $('#name').html("Pilot : "+fn+" "+ ln);
      	   	    		});
      	      		});
      	    		});
      	    
          modal.style.display = "block";
          $('#accept').attr("onclick",'back();');
          $('#accept').attr("value",'OK');

        }
        
       function show2(id) {
      	 show(id);
      	 $('#vec').html("Deleting this reservation?");
           modal.style.display = "block";
           $('#accept').attr("onclick","cancel(''"+id+"'');");
           $('#accept').attr("value",'Delete');

         }
      