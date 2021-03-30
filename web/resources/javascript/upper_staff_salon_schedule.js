// $(document).ready(function() {

//     $("#date").change(function(){
//         $('#aptTable').empty();
//         // alert($(this).val())
//          $.get("http://localhost:8080/quickSalon_war_exploded/upperStaff/appointmentsByDate,{
//                 date: $(this).val() },
//                 function(data, status){
//                     // console.log(data);
//                     $.each(data, function( index, value ) {
//                         $('#aptTable').append(`<tr><td>${value.firstName+' '+value.lastName}</td> <td>${value.serviceName}</td> <td>${value.telephone}</td> <td>${value.startTime}</td> <td>${value.endTime}</td></tr>`);
//                     });
//                 }
//             );
//     });



// });


$(document).ready(function() {

    $("#date").change(function(){
        $('#aptTable').empty();
         $.get("http://localhost:8080/quickSalon_war_exploded/upperStaff/appointmentsByDate", {
                    date:$(this).val()
                },
                function(data, status){
                    $.each(data, function( index, value ) {
                        $('#aptTable').append(`<tr><td>${value.custFirstName+' '+value.custLastName}</td> <td>${value.telephone}</td> <td>${value.serviceName}</td>  <td>${value.startTime}</td> <td>${value.endTime}</td> <td>${value.empFirstName+' '+value.empLastName}</td> </tr>`);
                    });
                }
            );
    });
});



