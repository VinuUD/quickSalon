$(document).ready(function () {
  $.get(
    "http://localhost:8080/quickSalon_war_exploded/serviceList",
    function (data) {
      data.map(function (sData) {
        $("#services").append(
          `<option value="${sData.serviceID}"> ${sData.serviceName} </option>`
        );
      });
    }
  );

  $("#nameSelect").change(function () {
    var selectedVal = $(this).val();
    console.log(selectedVal);
    $.ajax({
      type: "GET",
      url: "http://localhost:8080/quickSalon_war_exploded/serviceList",
      data: { id: `${selectedVal}` },
      success: function (response) {
        console.log(response);
        if (response == 1) {
          alert("Leave added Successfully!");
          location.reload();
        } else if (response == 0) {
          alert("Leave added Unsuccessfully!! please try again !");
          location.reload();
        }
      },
    });
  });
});
