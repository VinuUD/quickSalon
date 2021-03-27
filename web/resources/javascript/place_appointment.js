var monthArray = [
  "Jan",
  "Feb",
  "Mar",
  "Apr",
  "May",
  "Jun",
  "Jul",
  "Aug",
  "Sep",
  "Oct",
  "Nov",
  "Dec",
];

var filledTimeSlots = [];
var timeTaken = 0;
var spIDsRealatedToService = [];
var assignedSpID = "";
var assignedSpFname = "";
var assignedSpLname = "";

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

  subAbs(time) {
    var h = this.hour;
    if (h < 8) {
      h = h + 12;
    }
    var t1 = h * 60 + this.min;
    var t2 = time.hour * 60 + time.min;
    return new Time(
      Math.floor(Math.abs(t1 - t2) / 60) % 12,
      Math.abs(t1 - t2) % 60
    );
  }
  add(time) {
    var m = (this.min + time.min) % 60;
    var h = this.hour + time.hour + Math.floor((this.min + time.min) / 60);

    if (h > 12) {
      h = h % 12;
    }
    return new Time(h, m);
  }
  //twentyFour hour format
  addTwentyFour(time) {
    var m = (this.min + time.min) % 60;
    var h = this.hour + time.hour + Math.floor((this.min + time.min) / 60);

    if (h > 24) {
      h = h % 24;
    }
    return new Time(h, m);
  }
  getMinutes() {
    return parseInt(this.hour) * 60 + this.min;
  }
  //12h format
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

//serviceID,serviceName,timeTaken---Return
$(document).ready(function () {
  // Populate Service List
  $.get(
    "http://localhost:8080/quickSalon_war_exploded/serviceList",
    function (data) {
      data.map(function (sData) {
        $("#services").append(
          `<option class="serviceOpt" id="${sData.timeTaken}" value="${sData.serviceID}"> ${sData.serviceName} </option>`
        );
      });
    }
  );

  // Get all Appointments
  $.get(
    "http://localhost:8080/quickSalon_war_exploded/appointments",
    function (responseJson) {
      // var $select = $("#servicedropdownlist");
      $.each(responseJson, function (index, appointment) {
        //set date into 2020Jan05 format
        res = appointment.day.replace(",", "").split(" ");
        day = res[1] < 10 ? "0" + res[1] : res[1];
        selectId = res[2] + res[0] + day;
        $("#" + selectId).css("background-color", "#F58F79");
      });
    }
  );

  var appointmentList = []; //all appointmentList from DB

  // populate Sp List with selected Service
  $("#services").change(function () {
    $(".td-white").css("background-color", "white");
    timeTaken = $(this).find("option:selected").attr("id");

    //load the service providers relavent to the selected service
    $("#serviceProvider").html(`<option value="1111">Default</option>`);
    var selectedVal = $(this).val();
    $.ajax({
      type: "GET",
      url: "http://localhost:8080/quickSalon_war_exploded/spListService",
      data: { id: `${selectedVal}` },
      success: function (response) {
        response.map(function (sData) {
          spIDsRealatedToService.push(sData.employeeId);
          $("#serviceProvider").append(
            `<option value="${sData.employeeId}"> ${
              sData.firstName + " " + sData.lastName
            }</option>`
          );
        });
      },
    });

    //load all the appointments relevent to the selected service
    $.ajax({
      type: "GET",
      url:
        "http://localhost:8080/quickSalon_war_exploded/appointmentsByserviceID",
      data: { serviceID: `${selectedVal}` },
      async: false,
      success: function (response) {
        appointmentList = response;
      }, //set date into 2020Jan05 format
    });

    displayAppointmentsOnCalendar(appointmentList);
  });

  //Plot appointment on calendar
  function displayAppointmentsOnCalendar(apointments) {
    var day = [];
    $.each(apointments, function (index, appointment) {
      day = appointment.date.split("-");
      //set date into 2020Jan05 format
      var selectId = day[0] + monthArray[parseInt(day[1]) - 1] + day[2];
      $("#" + selectId).css("background-color", "#F58F79");
    });
  }

  var clickedDate = "";

  //Onclick day slots on calendar
  $(".day-btn").on("click", function () {
    var id = $(this).attr("id");
    clickedDate = id;

    //If not selected a service
    if ($("#services").val() == 0) {
      $("#services").css("border", "2px red solid");
    } else {
      //Display Free time slots table
      $(".calendar-div").css("display", "none");
      $(".time-slots-div").css("display", "block");

      appointmentList.map(function (appointment) {
        var year = id.substr(0, 4);
        var month = id.substr(4, 3);
        var day = id.substr(7);

        var date = appointment.date.split("-");
        //set date into 2020Jan05 format
        // var selectId=day[0]+monthArray[parseInt(day[1])]+day[2];

        if (
          date[0] == year &&
          date[1] == monthArray.indexOf(month) + 1 &&
          parseInt(date[2]) == day
        ) {
          var startTime = appointment.startTime;
          var endTime = appointment.endTime;
          var hs = startTime.split(":")[0];
          var ms = startTime.split(":")[1];

          filledTimeSlots.push(new Time(hs, ms));
        }
      });

      //This will populate the free slot table
      freeSlots();
    }

    function freeSlots() {
      timeTakenObj = new Time(0, parseInt(timeTaken)).add(new Time(0, 0)); //
      var sTime = new Time(9, 0);
      var closeTime = new Time(19, 0);
      var lunchTime = new Time(13, 0);

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
          }</td> <td> <Butt id='tr-icon' class='tr-icon fa fa-fw fa-plus-square'></icon> </td> </tr>`
        );
        sTime = timeTakenObj.addTwentyFour(sTime);
      }
    }
  });

  //When click on + icon
  $(document).on("click", ".tr-icon", function () {
    $("#select-time-btn").prop("disabled", false);

    //clear all selected icons
    $(".tr-icon").css("color", "green");
    $(".tr-icon").removeClass("fa-minus-square");
    $(".tr-icon").addClass("fa-plus-square");

    //track selected tr
    var $row = $(this).closest("tr");
    $selectedTimeSlot = $row.find("td");

    // Remove +
    $(this).removeClass("fa-plus-square");
    $(this).addClass("fa-minus-square");
    $(this).css("color", "red");

    //get Selected times
    selectedStartTime = $selectedTimeSlot[0].innerText;
    selectedEndTime = $selectedTimeSlot[1].innerText;

    // $.ajax({
    //   type: "POST",
    //   url: "http://localhost:8080/quickSalon_war_exploded/getLeastAppSp",
    //   data: { spIDs: [2, 21, 22] },
    //   success: function (response) {
    //     console.log(response);
    //   },
    // });
  });

  //When clicked Select Button
  var appSp = []; //appointment thiyana sp lage spIDs
  // var thisDate_FreeTimeSlotSpIDs = [];
  // var thisDateFreeSpIDs = [];
  $(document).on("click", "#select-time-btn", function () {
    var thisDateFreeSpIDs = [];
    var thisDate_FreeTimeSlotSpIDs = [];
    var thisDateThisTimeNotFree = [];
    var month = monthArray.indexOf(clickedDate.substr(4, 3)) + 1;

    var dateWithFormat = //2021-03-04
      clickedDate.substr(0, 4) +
      "-" +
      "0" +
      month +
      "-" +
      clickedDate.substr(7, 2);

    appointmentList.map(function (app) {
      appSp.push(app.employeeID);
    });

    var appSpWithNoDuplicates = [...new Set(appSp)];
    let difference = spIDsRealatedToService.filter(
      (x) => !appSpWithNoDuplicates.includes(x)
    ); // meken thama balanne me service eka dena okkoma sp lageyi appointment
    // ekak tyna sp lageyi wenasa

    console.log(spIDsRealatedToService);
    console.log(appSpWithNoDuplicates);

    if (difference.length == 1) {
      assignedSp = difference[0]; //completed
    } else if (difference.length > 1) {
      //me service ekata adalawa inna sp lagen appointment nathi sp la*************
      //difference array eke inna ayawa backend ekata yawala eyalagen aduma
      // appointment thyna kenaawa assign krnwa
      $.ajax({
        type: "GET",
        url: "http://localhost:8080/quickSalon_war_exploded/getLeastAppSp",
        data: { spIDs: difference },
        async: false,
        success: function (response) {
          assignedSpFname = response.firstName;
          assignedSpLname = response.lastName;
        },
      });
    } else if (difference.length == 0) {
      //me service ekata adalawa inna okkoma sp lata appontment watila tynwa********
      appointmentList.map(function (app) {
        // console.log(app.date + "-----" + dateWithFormat);
        if (app.date == dateWithFormat) {
          var hs = parseInt(selectedStartTime.split(":")[0]);
          if (hs > 0 && hs < 8) {
            hs = hs + 12;
            thisDateFreeSpIDs;
          }
          var ms = parseInt(selectedStartTime.split(":")[1]);
          var selectedTimeMinute = parseInt(hs * 60 + ms);

          // console.log(selectedTimeMinute);

          // for (var i = 0; i < appointmentList.length; i++) {
          var appHs = parseInt(app.startTime.split(":")[0]);
          var appMs = parseInt(app.startTime.split(":")[1]);

          var appMinute = appHs * 60 + appMs;
          // console.log(selectedTimeMinute + " " + appMinute);
          if (Math.abs(selectedTimeMinute - appMinute) >= parseInt(timeTaken)) {
            // me date ekata adalawa illapu welaawa free denna plwn aya********
            thisDate_FreeTimeSlotSpIDs.push(app.employeeID);
          } else {
            //meka athula inne dawasath samana wela welaawath samana una eun
            thisDateThisTimeNotFree.push(app.employeeID);
            //console.log(app);
          }
          // }
        } else {
          // me dawase mokuth apppointment nathi aya
          thisDateFreeSpIDs.push(app.employeeID);
          // console.log(app);
        }
      });

      var appOnlyAnotherDay2 = thisDateFreeSpIDs.filter(
        (x) => !thisDateThisTimeNotFree.includes(x)
      );

      var appOnlyAnotherDay = appOnlyAnotherDay2.filter(
        (x) => !thisDate_FreeTimeSlotSpIDs.includes(x)
      );

      if (appOnlyAnotherDay.length != 0) {
        if (appOnlyAnotherDay.length == 1) {
          //menna muwa kelinma assign krnna plwn
          assignedSpID = appOnlyAnotherDay[0];
        } else {
          //meyalawa backend ekata yawala aduma app gana tyna kena gnna oneh
          $.ajax({
            type: "POST",
            url: "http://localhost:8080/quickSalon_war_exploded/getLeastAppSp",
            data: { spIDs: appOnlyAnotherDay },
            async: false,
            success: function (response) {
              assignedSpFname = response.firstName;
              assignedSpLname = response.lastName;
            },
          });
        }
      } else {
        if (thisDate_FreeTimeSlotSpIDs.length != 0) {
          if (thisDate_FreeTimeSlotSpIDs.length == 1) {
            console.log("dn mn inne meekee 2");
            assignedSpID = thisDate_FreeTimeSlotSpIDs[0];
          } else {
            console.log("dn mn inne meekee 3");
            $.ajax({
              type: "POST",
              url:
                "http://localhost:8080/quickSalon_war_exploded/getLeastAppSp",
              data: { spIDs: thisDate_FreeTimeSlotSpIDs },
              async: false,
              success: function (response) {
                console.log(response);
                assignedSpFname = response.firstName;
                assignedSpLname = response.lastName;
              },
            });
          }
        } else {
          console.log("nothing to do here");
        }
      }
    }

    // console.log("Me dawasee me welawa free nathi aya");
    // console.log(thisDateThisTimeNotFree);
    // console.log("Me dawase free slots tyana aya");
    // console.log(thisDate_FreeTimeSlotSpIDs);
    // console.log("Me dawase nathuwa anith dawasawala appointment tyna aya");
    // console.log(thisDateFreeSpIDs);
    // console.log("Me dawase nathuwa anith dawaswala witrk app tyna aya");
    // console.log(appOnlyAnotherDay);
    // console.log("okkoma appointment tika");
    // console.log(appointmentList);

    console.log(assignedSpID + " ----> " + assignedSpFname);
  });
});
