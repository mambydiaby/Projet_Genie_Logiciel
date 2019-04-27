console.clear()

var testinputs = function() {
  var t=true;
  
  $('input[required]').each(function(i){
    if ( $(this).val()=="" ) {
      t=false;
      return false;
    }
  })
  
  if (t) $(':submit').prop( "disabled", false )
  else      $(':submit').prop( "disabled", true )
}

$(document).ready(function () {
	testinputs()
  
  $(':input').on("change keyup", function(e){
    testinputs()
  })
})