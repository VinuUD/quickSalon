$("document").ready(function () {
  alert($("#key").val());

  $.get("http://localhost:8080/quickSalon_war_exploded/allcustomers", {
      key: $("#key").val()
  },
  function (data, status) {
      alert(data);
      }
  );

  // $("#key").keyup(function(){
  //   alert($("#key").val());
  //   $.get("http://localhost:8080/quickSalon_war_exploded/allcustomers", {
  //       key: $("#key").val()
  //   },
  //   function (data, status) {
  //       alert(data);
  //       }
  //   );
  // });
});
