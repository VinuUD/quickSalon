$(document).ready(function () {
  $("#password").val("Sp123456");
  // $('#password).attr('type', 'password');

  $.get(
    "http://localhost:8080/quickSalon_war_exploded/registerSP",
    function (data, status) {
      $("#empid").val(data);
    }
  );

  $("#register").on("click", function () {
    if ($("#fname").val() == "") {
      $("#fname").css("border", "4px red solid");
    }
    if ($("#lname").val() == "") {
      $("#lname").css("border", "4px red solid");
    } else if ($("#uname").val() == "") {
      $("#uname").css("border", "4px red solid");
    } else if ($("#nic").val() == "") {
      $("#nic").css("border", "4px red solid");
    } else if ($("#email").val() == "") {
      $("#email").css("border", "4px red solid");
    } else if ($("#salaryl").val() == "") {
      $("#salary").css("border", "4px red solid");
    } else if ($("#address").val() == "") {
      $("#address").css("border", "4px red solid");
    } else if ($("#contactno").val() == "") {
      $("#salary").css("border", "4px red solid");
    } else if ($("#password").val() == "") {
      $("#password").css("border", "4px red solid");
    } else {
      //On success leave added
      confirm("Do you want to add Service Provider?");

      $.post(
        "http://localhost:8080/quickSalon_war_exploded/registerSP",
        {
          empID: $("#empid").val(),
          fname: $("#fname").val(),
          lname: $("#lname").val(),
          uname: $("#uname").val(),
          nic: $("#nic").val(),
          email: $("#email").val(),
          contactno: $("#contactno").val(),
          address: $("#address").val(),
          salary: $("#salary").val(),
          password: $("#password").val(),
        },
        function (data, status) {
          location.reload();
        }
      );
    }
  });

  $("#manager_back").on("click",function (){
    window.location.href="./employee_management.html";
  });
});
