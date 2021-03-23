$(document).ready(function () {

  // Polulate Service List
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

   //    Get all Appointments
   $.get("http://localhost:8080/quickSalon_war_exploded/appointments",
    function (responseJson) {
      // var $select = $("#servicedropdownlist");
      $.each(responseJson, function (index, appointment) {
        //set date into 2020Jan05 format
        res = appointment.date.replace(",", "").split(" ");
        day = res[1] < 10 ? "0" + res[1] : res[1];
        selectId = res[2] + res[0] + day;
        // alert(selectId)
        $("#" + selectId).css("background-color", "#F58F79");
        // appointments.push({
        //   qId: appointment.qId,
        //   date: appointment.date,
        //   startTime: new Time(
        //     parseInt(appointment.startTime.substr(0, 2)),
        //     parseInt(appointment.startTime.substr(3, 2))
        //   ),
        //   endTime: new Time(
        //     parseInt(appointment.endTime.substr(0, 2)),
        //     parseInt(appointment.endTime.substr(3, 2))
        //   ),
        // });
      });
    }
  );

  // populate Sp List with selected Service
  $("#services").change(function () {
  
    $("#serviceProvider").html(` `);
    var selectedVal = $(this).val();
    console.log(selectedVal);
    $.ajax({
      type: "GET",
      url: "http://localhost:8080/quickSalon_war_exploded/spListService",
      data: { id: `${selectedVal}` },
      success: function (response) {
        response.map(function (sData) {
          $("#serviceProvider").append(
            `<option value="${sData.employeeId}"> ${sData.firstName +' '+sData.lastName}</option>`
          );
        }); 
      },
    });
  });

 
  
  $("#serviceProvider").change(function () {
    var spId = $(this).val();

    $(".td-white").css("background","white");


    // window.queueIds = [];
    // apt_sp = [];
    // window.spId = $("#spdropdownlist option:selected").val();
  
    $.get(
      "http://localhost:8080/quickSalon_war_exploded/serviceProvider/spappointments?spId=" +
        spId,
        function (responseJson) {
          // var $select = $("#servicedropdownlist");
          $.each(responseJson, function (index, appointment) {
            //set date into 2020Jan05 format
            res = appointment.date.replace(",", "").split(" ");
            day = res[1] < 10 ? "0" + res[1] : res[1];
            selectId = res[2] + res[0] + day;
            // alert(selectId)
            $("#" + selectId).css("background-color", "#F58F79");

          });
        }
    );
  });


  
});
