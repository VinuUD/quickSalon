$(document).ready(function() {
  //GET session data
  $.get("http://localhost:8080/quickSalon_war_exploded/session", function (username) {
    document.getElementById("welcome-message").innerText='Welcome '+username;
  });

  $("#all_service").on("click",function (){
    window.location.href="./allServices.html";
  });

  $("#employee_management").on("click",function (){
    window.location.href="./employee_management.html";
  });

  $("#deq_customer").on("click",function (){
    window.location.href="./dequeue_customer.html";
  });

  $("#add_leave").on("click",function (){
    window.location.href="./leave.html";
  });

  $("#all_sp").on("click",function (){
    window.location.href="./allServiceProviders.html";
  });

  $("#add_sp").on("click",function (){
    window.location.href="./register_service_provider.html";
  });

  $("#back").on("click",function (){
    window.location.href="./manager_home.html";
  });
})
