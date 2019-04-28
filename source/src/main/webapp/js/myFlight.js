$(function(){
$.ajax({
         type: "post",
         data: {'id':sessionStorage.getItem("user")},
         url: '/ws/flight/myflights/',
         success: function(data) {
 			var tbl = "<table id=\"itable1\" class=\"table table-striped custab\" >\r\n" +
 				"               <thead>\r\n" +
 				"                  <tr>\r\n" +
 				"                     <th>ID</th>\r\n" +
 				"                     <th>Departure</th>\r\n" +
 				"                      <th>Destination</th>\r\n" +
 				"                     <th>Date</th>\r\n" +
 				"                     <th>Seat</th>\r\n" +
 				"                     <th>Price</th>\r\n" +
 				"                     <th class=\"text-center\">Action</th>\r\n" +
 				"                  </tr>\r\n" +
 				"               </thead><tbody>"
 		
 			for (var i = 0; i < data.length; i++) {
 				console.log(data[i]);
 				var obj = JSON.parse(data[i]);
 				var td0 = "<tr><td>" + obj.id + "</td>";
 				var td1 = "<td>" + obj.departure + "</td>";
 				var td1_5="<td>" + obj.arrival + "</td>";
 				var td2 = "<td>" + moment(obj.date).format("YYYY-MM-DD") + "</td>";
 				var td3 = "<td>" + obj.seat + "</td>";
 				var td4 = "<td>" + obj.price + "</td>";
 				var td5 = "<td class=\"text-center\"><a onclick=\"storing('"+obj.id+"');\" target=\"_blank\" class='btn btn-info btn-xs' href=\"../flight_detail.html\" >"+
 				"<span class=\"glyphicon glyphicon-ok\"></span> See</a> <a onclick=\"storing('"+obj.id+"');\" target=\"_blank\" class='btn btn-info btn-xs' href=\"../Edit_Flight.html\" ><span class=\"glyphicon glyphicon-ok\"></span> Edit</a> </td></tr>"
 				tbl += td0 + td1 +td1_5+ td2 + td3 + td4 + td5;
 			}
 			tbl += "</tbody></table>"
 			document.getElementById("div1").innerHTML = "";
 			$("#div1").append(tbl);
 			$("#itable1").DataTable({searching: false});
 		},
 		error: function(){
 			alert("can't find coresponding flights");
 		}
         
         });
       	$(document).ready(function(){
       		var type=sessionStorage.getItem("type");
       		if(type=="Passenger"){
       			$('#back').attr("href","test.html");
       		}else{
       			$('#back').attr("href",'testp.html');
       		}
       	});    
}) ;

function storing(obj){
	sessionStorage.setItem('flight_info',obj);
}