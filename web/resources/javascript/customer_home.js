$(document).ready(function() {

  //GET session data
  $.get("http://localhost:8080/quickSalon_war_exploded/session", function (username) {
    document.getElementById("welcome-message").innerText='Welcome '+username;
  });

  //logout
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

  $("#my_profile").on("click",function (){
    window.location.href="./my_profile.html";
  });

  $('#logout').on('click', function(){
    $.get("http://localhost:8080/quickSalon_war_exploded/logout",
        function(data, status){
            window.location.href="../";
        }
    );
  });
})
