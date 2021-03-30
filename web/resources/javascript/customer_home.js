$(document).ready(function() {
  $("#place_appointment").on("click",function (){
    window.location.href="./place_appointment.html";
  });

  $("#manage_appoinment").on("click",function (){
    window.location.href="./manage_appointment.html";
  });

  $("#view_services").on("click",function (){
    window.location.href="../service_details.html";
  });

  $("#review").on("click",function (){
    window.location.href="./review.html";
  });
})