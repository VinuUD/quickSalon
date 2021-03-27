$(document).ready(function() {
  

    var now = new Date();
    var day = ("0" + now.getDate()).slice(-2);
    var month = ("0" + (now.getMonth() + 1)).slice(-2);
    var today = now.getFullYear()+"-"+(month)+"-"+(day) ;
    $('#date').val(today);

    //First Initial 
    onChangeDate(today);

    //On change date populate the table
    $("#date").change(function(){
        onChangeDate($(this).val())
    });

    //Dequeue Customer
    // $("#date").change(function(){
    //     onChangeDate($(this).val())
    // });

    $(document).on("click", ".dequeue-btn ", function () {

        if (confirm("Confirm Dequeue ?") == true) {
            var $row = $(this).closest("tr")
            $tds = $row.find("td"); 
            //console.log($tds[0].innerText)

            //post Requset for dequeue
            $.post("http://localhost:8080/quickSalon_war_exploded/cancelAppointment", {
                qId:$tds[0].innerText
            },
            function(data, status){
               if(data==1){
                   alert("Dequeue Confirmed !")
                   location.reload();
               }else{
                alert("Dequeue Failed !")
                location.reload();
               }
            }
        );
           
        }
        
    });

    function onChangeDate(date){
        $('#aptTable').empty();
        // alert($(this).val())
         $.post("http://localhost:8080/quickSalon_war_exploded/appointmentSP", {
                    date:date
                },
                function(data, status){
                    $.each(data, function( index, value ) {
                        $('#aptTable').append(`<tr><td>${value.qId}</td><td>${value.custFirstName+' '+value.custLastName}</td> <td>${value.serviceName}</td> <td>${value.telephone}</td> <td>${value.startTime}</td> <td>${value.endTime}</td><td>${value.empFirstName+' '+value.empLastName}</td> <td><button class="dequeue-btn btn btn-red">Dequeue</button></td></tr>`);
                    });
                }
            );
    }

});

