$(document).ready(function() {
  $("#cancel").on("click",function (){
    window.location.href="./home.html";
  });

  $('#send-btn').on('click', function(){

    var msg=$("#msg").val()

    $.post("http://localhost:8080/quickSalon_war_exploded/review", {
        msg:msg
    },
    function (data, status) {
        alert(data);
        }
    );

});
})