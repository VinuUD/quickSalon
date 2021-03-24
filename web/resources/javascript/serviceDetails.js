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

    

    $("#services").change(function () {
        var selectedVal = $(this).val();
        // alert(selectedVal)

        $.get(
            "http://localhost:8080/quickSalon_war_exploded/serviceDetailsByID",{serviceID:selectedVal},
            function (data) {
                // console.log(data[0].serviceName);
                $("#serviceID").text(data[0].serviceID);
                 $("#serviceName").text(data[0].serviceName);
                $("#price").text(data[0].price);
                $("#timeTaken").text(data[0].timeTaken);
                $("#desc").text(data[0].serviceDescription);
                
            }
          );
        
    });


});