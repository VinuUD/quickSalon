$( document ).ready(function() {

    $.get("http://localhost:8080/quickSalon_war_exploded/serviceList", function(data){
      
        data.map(function (sData) {
            $("#services").append(
              `<option value="${sData.serviceID}"> ${sData.serviceName} </option>`
            );
          });
    });
    
});

