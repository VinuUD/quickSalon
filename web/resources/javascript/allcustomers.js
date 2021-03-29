$("document").ready(function () {


  $.get("http://localhost:8080/quickSalon_war_exploded/allcustomers", {
      key: $("#key").val()
  },
  function (data, status) {
      console.log(data);
      }
  );

  $("#key").keyup(function(){
    
    $.get("http://localhost:8080/quickSalon_war_exploded/allcustomers", {
        key: $("#key").val()
    },
    function (data, status) {
        console.log(data);
        }
    );
  });
});
