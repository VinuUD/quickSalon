var userID=1;

$(document).ready(function() {

    // String customerName;
    // String serviceName;
    // Time startTime;
    // Time endTime;
    // Date date;

    tableContent="";
    //GET All Appointment List for service provider bt spID
    $.get("myschedule", function (responseJson) {
        $.each(responseJson, function (index, appointment) {
           // console.log(appointment.endTime)
            $('#appointment_table').append( '<tr><td>'+appointment.customerName+'</td><td>'+appointment.serviceName+'</td><td>'+appointment.startTime+'</td><td>'+appointment.endTime+'</td></tr>');
           // $('#appointment_table').append()
           // tableContent+="<tr><td>"+appointment.customerName+"</td><td>"+appointment.serviceName+"</td><td>"+appointment.startTime+"</td><td>"+appointment.endTime+"</td></tr>"
        });
    });

   document.getElementById("appointment_table").innerHTML=tableContent
});
