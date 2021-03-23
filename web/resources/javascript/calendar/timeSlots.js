// HTML <td> element object
var $selectedTimeSlot;

// Dummy data for table -got from apt-sp
tmeSlots=[
    {"startTime":"12:00", "endTime":"13:00"},
    {"startTime":"11:00", "endTime":"12:00"},
    {"startTime":"`14:00", "endTime":"15:00"},
    {"startTime":"17:00", "endTime":"19:00"}
]

// Fill 

$("#select-time-btn").prop("disabled",true);

$('td').on('click', function(){
    // alert(this.innerHTML)
    // alert(this.id)

    $(".calendar-div").css("display", "none");
    $(".time-slots-div").css("display", "block");
});



$('.ar-icon').on('click', function(){

    $("#select-time-btn").prop("disabled",false);

    //clear all selected icons
    $('icon').css("color","green");
    $('icon').removeClass("fa-minus-square");
    $('icon').addClass("fa-plus-square");

    //track selected tr
    var $row = $(this).closest("tr");    
    $selectedTimeSlot=$row.find("td"); 

    // Remove +
    $(this).removeClass("fa-plus-square");
    $(this).addClass("fa-minus-square");
    $(this).css("color","red");


    // $(".time-slots-div").css("display", "block");
    // var $row = $(this).closest("tr");    
    // var $tds = $row.find("td");   // Retrieves the text within <td>
    // alert($tds[0])       
    // console.log($tds[0].innerText)
    // console.log($tds[1].innerText)

});


$("#select-time-btn").on('click', function(){

});