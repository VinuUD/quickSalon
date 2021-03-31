$(document).ready(function () {


     //    Get all Appointments and load to calendar
   $.get("http://localhost:8080/quickSalon_war_exploded/appointments",
        function (responseJson) {
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

   
      

    

})