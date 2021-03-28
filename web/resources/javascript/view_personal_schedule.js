$(document).ready(function() {

    $("#date").change(function(){
        $('#aptTable').empty();
        // alert($(this).val())
         $.post("http://localhost:8080/quickSalon_war_exploded/serviceProvider/myschedule", {
                    date:$(this).val()
                },
                function(data, status){
                    // console.log(data);
                    $.each(data, function( index, value ) {
                        $('#aptTable').append(`<tr><td>${value.firstName+' '+value.lastName}</td> <td>${value.serviceName}</td> <td>${value.telephone}</td> <td>${value.startTime}</td> <td>${value.endTime}</td></tr>`);
                    });
                }
            );
    });



});