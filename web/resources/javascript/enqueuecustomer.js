//Time class
class Time {
  constructor(hour, min) {
    this.hour = hour;
    this.min = min;
  }
  sub(time) {
    var h = this.hour;
    if (h < 8) {
      h = h + 12;
    }
    var t1 = h * 60 + this.min;
    var t2 = time.hour * 60 + time.min;
    return new Time(Math.floor((t1 - t2) / 60) % 12, (t1 - t2) % 60);
  }
  add(time) {
    var m = (this.min + time.min) % 60;
    var h = this.hour + time.hour + Math.floor((this.min + time.min) / 60);

    if (h > 12) {
      h = h % 12;
    }
    return new Time(h, m);
  }
  getMinutes() {
    return parseInt(this.hour) * 60 + this.min;
  }
  greater(time) {
    var h1 = this.hour;
    var h2 = time.hour;
    if (h1 < 8) {
      h1 = h1 + 12;
    }
    if (h2 < 8) {
      h2 = h2 + 12;
    }
    if (h1 > h2) {
      return true;
    } else return h1 == h2 && this.min >= time.min;
  }
}

var serviceDetails = [];
var appointments = [];
var sid = null;

//json
var allServices;
var allAppointments;

//Get all services
$(document).ready(function () {

  
  //1) GET service List of 
  $.get("http://localhost:8080/quickSalon_war_exploded/servicesp",
    function (data) {
      console.log(data);
        $.each(data, function (index, service) {
          
          // $("<option>").val(service.serviceID).text(service.serviceName).appendTo($select);
          $("#servicedropdownlist").append( `<option class="serviceOpt" id="${service.timeTaken}" value="${service.serviceID}"> ${service.serviceName} </option>`);
        })
      }
    );

    //2)All sps relevat to the selected service
    $("#servicedropdownlist").change(function () {
      spIDsRealatedToService = [];
      var selectedVal = $(this).val();
         $.post("http://localhost:8080/quickSalon_war_exploded/appointmentSp", {
                    serviceID:selectedVal,
                },
                function(data, status){

                    $.each(data, function (index, appointment) {

                      //set date into 2020Jan05 format
                      res = appointment.day.replace(",", "").split(" ");
                      day = res[1] < 10 ? "0" + res[1] : res[1];
                      selectId = res[2] + res[0] + day;
                      $("#" + selectId).css("background-color", "#F58F79");
                  });
                }
            );
    })

    // $.get(
    //   "http://localhost:8080/quickSalon_war_exploded/appointments",
    //   function (responseJson) {
    //     // var $select = $("#servicedropdownlist");
    //     $.each(responseJson, function (index, appointment) {
    //       //set date into 2020Jan05 format
    //       res = appointment.day.replace(",", "").split(" ");
    //       day = res[1] < 10 ? "0" + res[1] : res[1];
    //       selectId = res[2] + res[0] + day;
    //       $("#" + selectId).css("background-color", "#F58F79");
    //     });
    //   }
    // );





      
      // $.ajax({
      //   type: "POST",
      //   url: "http://localhost:8080/quickSalon_war_exploded/appointmentSP",
      //   data: id:selectedVal ,
      //   success: function (response) {
      //     console.log(response);
      //     response.map(function (sData) {
      //       spIDsRealatedToService.push(sData.employeeId);
      //       $("#spdropdownlist").append(`<option value="${sData.employeeId}"> ${ sData.firstName + " " + sData.lastName}</option>`);
            
      //     });
      //   },
      // });
    });


    //3)load all appointments related to sp





