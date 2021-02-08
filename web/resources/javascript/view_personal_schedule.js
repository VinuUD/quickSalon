var userID=1;
var appointmentList;

$(document).ready(function() {

    let today = new Date().toISOString().substr(0, 10);
    document.querySelector("#date").value = today;

    // String customerName;
    // String serviceName;
    // Time startTime;
    // Time endTime;
    // Date date;

    tableContent="";
    //GET All Appointment List for service provider bt spID
    $.get("myschedule", function (responseJson) {
        appointmentList=responseJson;
        today=new Date();
        $.each(responseJson, function (index, appointment) {
            d = new Date(appointment.date);
            if (d.getFullYear() == today.getFullYear() && d.getMonth() == today.getMonth() && d.getDate() == today.getDate()) {
                tableContent += '<tr><td>' + appointment.customerName + '</td><td>' + appointment.serviceName + '</td><td>' + appointment.startTime + '</td><td>' + appointment.endTime + '</td></tr>';
            }
        });
        document.getElementById("appointment_table").innerHTML=tableContent
    });


});

function changeDate(event){
    tableContent=""
    date=new Date(event.target.value);
   // alert(date.getDate())
    console.log(appointmentList)
    $.each(appointmentList, function (index,appointment) {
        d=new Date(appointment.date);
        if(d.getFullYear()==date.getFullYear() && d.getMonth()==date.getMonth() && d.getDate()==date.getDate()) {
            tableContent+='<tr><td>' + appointment.customerName + '</td><td>' + appointment.serviceName + '</td><td>' + appointment.startTime + '</td><td>' + appointment.endTime + '</td></tr>';
        }
    });
    document.getElementById("appointment_table").innerHTML=tableContent
}

