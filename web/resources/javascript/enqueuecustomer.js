//Time class
var  appointments;
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

  // cancel-btn redirect to home
  $("#cancel-btn").on("click", function () {
    window.location.href="./sp_home.html";    
  });

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



    //2)All appointmentts relevat to the selected service & sp
    $("#servicedropdownlist").change(function () {
      spIDsRealatedToService = [];
      var selectedVal = $(this).val();
      timeTaken=$(this).attr("id");

         $.post("http://localhost:8080/quickSalon_war_exploded/appointmentSp", {
                    serviceID:selectedVal,
                },
                function(data, status){
                    $.each(data, function (index, appointment) {
                      appointments=data;
                      //set date into 2020Jan05 format
                      res = appointment.day.replace(",", "").split(" ");
                      day = res[1] < 10 ? "0" + res[1] : res[1];
                      selectId = res[2] + res[0] + day;
                      $("#" + selectId).css("background-color", "#F58F79");
                  });
                }
            );
    })

    //3)On  click on a date 
    $(".day-btn").on("click", function () {

      $('#time-slots').empty();

      // console.log(appointments);

      var date=$(this).attr("id");

      console.log(date);

      $.each(appointments, function (index, appointment) {

        //set date into 2020Jan05 format
        res = appointment.day.replace(",", "").split(" ");
        day = res[1] < 10 ? "0" + res[1] : res[1];
        selectId = res[2] + res[0] + day;

        
        
        if(selectId==date){
          // console.log('selecrt'+selectId);
          //plot on table
            $('#time-slots').append(` <tr><td>${appointment.startTime}</td> <td>${"hello"}</td></tr>`);

        }
    });


      alert($(this).attr("id"))


      //2021Mar13
      //"day": "Mar 9, 2021"
    });
    
    // var id = $(this).attr("id");
    // clickedDate = id;
    // var mt = monthArray.indexOf(clickedDate.substr(4, 3)) + 1;
    // assignedDate = //2021-03-04
    //   id.substr(0, 4) + "-" + "0" + mt + "-" + clickedDate.substr(7, 2);
    // // console.log("sp wa thorala thiyana welawa");
    // $(".calendar-div").css("display", "none");
    // $(".time-slots-div").css("display", "block");
    // filledTimeSlots = [];
    // appListRelatedSp.map(function (appointment) {
    //   var year = id.substr(0, 4);
    //   var month = id.substr(4, 3);
    //   var day = id.substr(7);

    //   var date = appointment.date.split("-");
    //   //set date into 2020Jan05 format
    //   // var selectId=day[0]+monthArray[parseInt(day[1])]+day[2];

    //   if (
    //     date[0] == year &&
    //     date[1] == monthArray.indexOf(month) + 1 &&
    //     parseInt(date[2]) == day
    //   ) {
    //     var startTime = appointment.startTime;
    //     var endTime = appointment.endTime;
    //     var hs = startTime.split(":")[0];
    //     var ms = startTime.split(":")[1];

    //     filledTimeSlots.push(new Time(hs, ms));
    //   }
    // });
    // // console.log(filledTimeSlots);
    // freeSlots();




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


//     $.get("http://localhost:8080/quickSalon_war_exploded/appointments",
//     function (responseJson) {
//         $.each(responseJson, function (index, appointment) {
//             //set date into 2020Jan05 format
//             res = appointment.day.replace(",", "").split(" ");
//             day = res[1] < 10 ? "0" + res[1] : res[1];
//             selectId = res[2] + res[0] + day;
//             // alert(selectId)
//             $("#" + selectId).css("background-color", "#F58F79");
//         });
//     }
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



    function freeSlots() {
      $("#time-slots").html(`<tr></tr>`);
      timeTakenObj = new Time(0, parseInt(timeTaken)).add(new Time(0, 0)); //
      var sTime = new Time(9, 0); //open time
      var closeTime = new Time(19, 0); //close time
      var lunchTime = new Time(13, 0); //lunch time

      while (sTime.hour != closeTime.hour) {
        filledTimeSlots.forEach((time) => {
          if (time.hour == sTime.hour && time.min == sTime.min) {
            sTime = timeTakenObj.addTwentyFour(sTime);
          }
        });

        var nxtSlot = timeTakenObj.add(sTime);
        var thisSlot = sTime.add(new Time(0, 0));
        $("#time-slots").append(
          `<tr id="timeSlotTr"><td>${
            (thisSlot.hour < 10 ? "0" + thisSlot.hour : thisSlot.hour) +
            ":" +
            (thisSlot.min < 10 ? thisSlot.min + "0" : thisSlot.min)
          }</td> <td>${
            (nxtSlot.hour < 10 ? "0" + nxtSlot.hour : nxtSlot.hour) +
            ":" +
            (nxtSlot.min < 10 ? nxtSlot.min + "0" : nxtSlot.min)
          }</td> <td> <icon id='tr-icon' class='tr-icon fa fa-fw fa-plus-square'></icon> </td> </tr>`
        );
        sTime = timeTakenObj.addTwentyFour(sTime);
      }
    }


    //3)load all appointments related to sp





