// Get the modal
var modal = document.getElementById('myModal');

			// Get the button that opens the modal
var btn = document.getElementById("myBtn");

			// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

		
			// When the user clicks the button, open the modal 
btn.onclick = function() {
  	modal.style.display = "block";
}				

			// When the user clicks on <span> (x), close the modal
span.onclick = function() {
	modal.style.display = "none";
		}
			//When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  	if (event.target == modal) {
   			modal.style.display = "none";
  			}
}

const myMap = L.map('map').setView([43.2961743,5.3699525], 6);

var dep=[43.2961743,5.3699525];
var arr=[45.7578137,4.8320114];


L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token={accessToken}', {
    attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, <a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
    maxZoom: 18,
    id: 'mapbox.streets',
    accessToken: 'pk.eyJ1Ijoia3lsZXZpbGxlbmV1dmUiLCJhIjoiY2puOTZ0eXkyMDM3eDNzcHVzeGJhZmMxYyJ9.7oIopPnX7qwOmOkL7dsSCQ' }).
addTo(myMap);
var marker1 = L.marker(dep).addTo(myMap);
marker1.bindPopup('<h4> Marseille, FRA </h4>');
var marker2 = L.marker(arr).addTo(myMap);
marker2.bindPopup('<h4> Lyon, FRA </h4>');
L.polyline([dep,arr], {
      color: 'orange'
}).addTo(myMap);