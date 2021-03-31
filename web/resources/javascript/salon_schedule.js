$(document).ready(function () {

    var appointments;
     //    Get all Appointments and load to calendar
   $.get("http://localhost:8080/quickSalon_war_exploded/appointments",
        function (responseJson) {
            appointments=responseJson;
            $.each(responseJson, function (index, appointment) {
                //set date into 2020Jan05 format
                res = appointment.day.replace(",", "").split(" ");
                day = res[1] < 10 ? "0" + res[1] : res[1];
                selectId = res[2] + res[0] + day;
                // alert(selectId)
                $("#" + selectId).css("background-color", "#F58F79");
            });
        }
    );

    //Requst for load alocated time slots
    $(".day-btn").on("click", function () {
        $('#time-slots').empty();
        var date=$(this).attr("id");
        $.each(appointments, function (index, appointment) {
          //set date into 2020Jan05 format
          res = appointment.day.replace(",", "").split(" ");
          day = res[1] < 10 ? "0" + res[1] : res[1];
          selectId = res[2] + res[0] + day;
          if(selectId== date){
            console.log('selecrt'+selectId);
            //plot on table
              $('#time-slots').append(` <tr><td>${appointment.startTime}</td> <td>${appointment.endTime}</td></tr>`);
  
          }
      });
      });

})