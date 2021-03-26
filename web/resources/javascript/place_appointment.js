
var monthArray=['Jan','Feb','Mar','Apr', 'May','Jun', 'Jul','Aug','Sep','Oct','Nov', 'Dec']
var filledTimeSlots = [];
var timeTaken = 0;


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

  subAbs(time)
  {
    var h = this.hour;
    if (h < 8) {
      h = h + 12;
    }
    var t1 = h * 60 + this.min;
    var t2 = time.hour * 60 + time.min;
    return new Time(Math.floor(Math.abs((t1 - t2)) / 60) % 12, Math.abs((t1 - t2)) % 60);
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
  addTwentyFour(time){
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
   $.get("http://localhost:8080/quickSalon_war_exploded/appointments",
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


  var appointmentList=[]; //all appointmentList from DB

  // populate Sp List with selected Service
  $("#services").change(function () {
    $(".td-white").css("background-color", "white");
    timeTaken = $(this).find('option:selected').attr('id');

    //load the service providers relavent to the selected service
    $("#serviceProvider").html(`<option value="1111">Default</option>`);
    var selectedVal = $(this).val();
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

    //load all the appointments relevent to the selected service
    $.ajax({
      type: "GET",
      url: "http://localhost:8080/quickSalon_war_exploded/appointmentsByserviceID",
      data: { serviceID : `${selectedVal}` },
      async:false,
      success: function (response) {
        appointmentList = response;
      },   //set date into 2020Jan05 format 
    });

    displayAppointmentsOnCalendar(appointmentList)
  });

  //Plot appointment on calendar
  function displayAppointmentsOnCalendar(apointments){
    var day=[]
    $.each(apointments, function (index, appointment) {
      day=appointment.date.split('-');
      //set date into 2020Jan05 format
      var selectId=day[0]+monthArray[parseInt(day[1])-1]+day[2];
      $("#" + selectId).css("background-color", "#F58F79");
    });
  }

  
//Onclick day slots on calendar
$(".day-btn").on('click', function(){
  var id=$(this).attr('id');

  //If not selected a service
  if ($('#services').val()==0) {
    $('#services').css("border", "2px red solid");
  }
  else{
    //Display Free time slots table
    $(".calendar-div").css("display", "none");
    $(".time-slots-div").css("display", "block");


      appointmentList.map(function(appointment){
      var year = id.substr(0, 4);
      var month = id.substr(4, 3);
      var day =  id.substr(7); 
    
        var date=appointment.date.split('-');
        //set date into 2020Jan05 format
        // var selectId=day[0]+monthArray[parseInt(day[1])]+day[2];
        if( date[0]==year && date[1]==monthArray.indexOf(month) && parseInt(date[2])==day){
              var startTime = appointment.startTime;
              var endTime = appointment.endTime;
        
              var hs=startTime.split(':')[0];
              var ms=startTime.split(':')[1];
        
              filledTimeSlots.push(new Time(hs,ms));
        }
      })
          //This will populate the free slot table
      freeSlots();
  }


  function freeSlots(){
    var timeTakenObj=(new Time(0,parseInt(timeTaken)).add(new  Time(0,0))); //
      var sTime=new Time(9,0);
      var closeTime=new Time(19,0);
      var lunchTime=new Time(13,0);
  
      while(sTime.hour!=closeTime.hour){
          filledTimeSlots.forEach((time)=>{
            if(time.hour==sTime.hour && time.min==sTime.min ){
              sTime=timeTakenObj.addTwentyFour(sTime);
            } 
          }); 
          var nxtSlot=timeTakenObj.add(sTime);
          var thisSlot=sTime.add(new Time(0,0));
          $('#time-slots').append(`<tr id="timeSlotTr"><td>${((thisSlot.hour< 10) ? '0'+thisSlot.hour : thisSlot.hour)+':'+((thisSlot.min< 10) ? thisSlot.min+'0' : thisSlot.min)}</td> <td>${((nxtSlot.hour< 10) ? '0'+nxtSlot.hour : nxtSlot.hour)+':'+((nxtSlot.min< 10) ? nxtSlot.min+'0' : nxtSlot.min)}</td> <td> <Butt id='tr-icon' class='tr-icon fa fa-fw fa-plus-square'></icon> </td> </tr>`);
          sTime=timeTakenObj.addTwentyFour(sTime);
      } 
  }
  
  });

  //When click on + icon
  $(document).on('click','.tr-icon',function(){

       $("#select-time-btn").prop("disabled",false);

        //clear all selected icons
        $('.tr-icon').css("color","green");
        $('.tr-icon').removeClass("fa-minus-square");
        $('.tr-icon').addClass("fa-plus-square");

        //track selected tr
        var $row = $(this).closest("tr");    
        $selectedTimeSlot=$row.find("td"); 

        // Remove +
        $(this).removeClass("fa-plus-square");
        $(this).addClass("fa-minus-square");
        $(this).css("color","red");

        //get Selected times
        selectedStartTime=$selectedTimeSlot[0].innerText;
        selectedEndTime=$selectedTimeSlot[1].innerText;
          
  });


   //When clicked Select Button
  $(document).on('click','#select-time-btn',function(){

    alert(selectedStartTime+' , '+selectedEndTime)
     
  });






  

});




// $("#timeSlot").click(function(){


//   var currentRow = $(this).closest("tr");

//   var StartTime = currentRow.find("td:eq(0)").text();
//   var endTime = currentRow.find("td:eq(1)").text(); //clicked time slot value
  
//     var hs=parseInt(startTime.split(':')[0]);
//     var ms=parseInt(startTime.split(':')[1]);
//     var len = appointmentList.length
//     var spIDs = []; 

//     for(var i =0; i< len; i++)
//     {
//       var appHs = appointmentList[i].split(':')[0];
//       var appMs = appointmentList[i].split(':')[0];
//         time1 = new Time(hs,ms).
//         time2 = new Time(appHs, appMs);
       
//         if(Math.abs( (time1.subAbs(time2))).greater(timeTakenObj) )
//            {
//              spIDs.push(appointmentList[i].employeeId);
//            }
//     }

//     console.log(spIDs);

    
//     // $.ajax({
//     //   type: "GET",
//     //   url: "link for get least Appointment sp with first name and last name",
//     //   data: {spIDs: spIDs},
//     //   success: function (response) {
//     //     console.log(response);
        
//     //   },
//     // });

    
// })

// $('#tr-icon').on('click', function(){



//   alert("hello")

//   $("#select-time-btn").prop("disabled",false);

//   //clear all selected icons
//   $('icon').css("color","green");
//   $('icon').removeClass("fa-minus-square");
//   $('icon').addClass("fa-plus-square");

//   //track selected tr
//   var $row = $(this).closest("tr");    
//   $selectedTimeSlot=$row.find("td"); 

//   // Remove +
//   $(this).removeClass("fa-plus-square");
//   $(this).addClass("fa-minus-square");
//   $(this).css("color","red");


//   // $(".time-slots-div").css("display", "block");
//   // var $row = $(this).closest("tr");    
//   // var $tds = $row.find("td");   // Retrieves the text within <td>
//   // alert($tds[0])       
//   // console.log($tds[0].innerText)
//   // console.log($tds[1].innerText)

//   var currentRow = $(this).closest("tr");

//   var StartTime = currentRow.find("td:eq(0)").text();
//   var endTime = currentRow.find("td:eq(1)").text(); //clicked time slot value
  
//     var hs=parseInt(startTime.split(':')[0]);
//     var ms=parseInt(startTime.split(':')[1]);
//     var len = appointmentList.length
//     var spIDs = []; 

//     for(var i =0; i< len; i++)
//     {
//       var appHs = appointmentList[i].split(':')[0];
//       var appMs = appointmentList[i].split(':')[0];
//         time1 = new Time(hs,ms);
//         time2 = new Time(appHs, appMs);
       
//         if(Math.abs( (time1.subAbs(time2))).greater(timeTakenObj) )
//            {
//              spIDs.push(appointmentList[i].employeeId);
//            }
//     }

//     console.log(spIDs)
// });


// });





// $('#tr-icon').on('click', function(){
//   // alert(this.innerHTML)
//   alert(this.id) 

//   $(".calendar-div").css("display", "none");
//   $(".time-slots-div").css("display", "block");

// });


















  // Populate Calendar with seleccted SP
  // $("#serviceProvider").change(function () {
  //   spId = $(this).val(); //global variable ****************
  //   $(".td-white").css("background","white");
  //   $.get(
  //     "http://localhost:8080/quickSalon_war_exploded/appointmentSp?spId=" +
  //       spId,
  //       function (responseJson) {
  //         appointmentList=responseJson;
  //         $.each(responseJson, function (index,appointment) {
  //           appointmentList=responseJson; //set aptList to response apointment list
  //           res = appointment.day.replace(",", "").split(" ");
  //           day = res[1] < 10 ? "0" + res[1] : res[1];
  //           selectId = res[2] + res[0] + day;
  //           $("#" + selectId).css("background-color", "#F58F79");
  //         });
  //       }
  //   );
  // });






// Onclick day this 
//   $('td').on('click', function(){
//     // alert(this.innerHTML)
//     // alert(this.id)

//     $(".calendar-div").css("display", "none");
//     $(".time-slots-div").css("display", "block");
// });


















// $(".day-btn").on('click', function(){ *************************************

//    console.log($(this).text());
//    var id=$(this).attr('id');
 

//    var year = id.substr(0, 4);
//   var month = id.substr(4, 3);
//   var day =  id.substr(7);

//   date = new Date(day + " " + month + " " + year);

//   var d=new Date(date);

//   console.log(d.getMonth()+1);

// });

  // $(".calendarDiv").on('click', function(){
  //   var date = $(this).attr("id");
  //   var spID = $("#serviceProvider").val();
  //   $.ajax({
  //     type: "GET",
  //     url: "addLeave.html/cancel",
  //     data: { spID : spID, date : date },
  //     success: function (response) {
  //       console.log(response);
        
  //     },
  //   });
  // })

  // SELECT j4f9qe_appointmentsassigned.employeeID, j4f9qe_appointments.date, j4f9qe_appointments.startTime, j4f9qe_appointments.endTime FROM j4f9qe_appointmentsassigned INNER JOIN j4f9qe_appointments ON j4f9qe_appointmentsassigned.qID = j4f9qe_appointments.qID WHERE j4f9qe_appointments.date = "2021-03-31" AND j4f9qe_appointmentsassigned.employeeID = 22
  
// });








