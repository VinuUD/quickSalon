$( document ).ready(function() {
    //Search list
    $("#empname").keyup(function(){
        value =$("#empname").val();
        $('#search-list').empty();
        if(typeof value === 'string'){
            //onclick ajax request to get employee data
            $.post("http://localhost:8080/quickSalon_war_exploded/searchSP", {
                    name:value,
                },
                function(data, status){
                    $.each(data, function( index, value ) {
                        $('#search-list').append("<li id="+value.employeeId+">"+value.firstName+"</li>");
                    });
                }
            );
        }
    });

  //onclick insert name to name field
    $(document.body).on('click', 'li' ,function(){
        $('#aptTable').empty();
        //set name selected
        $("#empname").val($(this).text());
        //clear the suggestion list
        $('#search-list').empty();
        //set employee ID
        $("#empid").val($(this).attr('id'));

    //    Load upcoming appointments of the SP
        $.get("http://localhost:8080/quickSalon_war_exploded/appointmentSP?empID="+$("#empid").val(), function(data) {
            $.each(data, function( index, value ) {
                    $('#aptTable').append(`<tr><td>${value.qId}</td> <td>${value.date}</td> <td>${value.startTime}</td> <td>${value.endTime}</td> <td> <button>Re-assign</button> <button>Cancel</button></td></li>`);
                });
        });
    //    Set redirect to re-assign or cancel appointments pages
    });


    $('#removeSP-btn').on('click', function(){

        var tbody = $("#aptTable");
        if (tbody.children().length == 0) {
            //Confirm
            if (confirm("Do you want to remove the Service Provider ?")) {

                $.get("http://localhost:8080/quickSalon_war_exploded/removeSP?empID="+$("#empid").val(), function(data) {
                   if(data=='true'){
                       alert("You have successfully removed!");
                   }else{
                       alert("Sorry, Removed Failed!");
                   }
                });

            } else {
                location.reload();
            }
        }else{
            alert("There are appointments assigned to the Service Provider. You can't remove!");
        }




        // if ($('#empname').val()=='') {
        //     $('#empname').css("border", "4px red solid");
        // }
        // if ($('#empid').val() == '') {
        //     $('#empid').css("border", "4px red solid");
        // } else if ($('#empname').val() == '') {
        //     $('#empname').css("border", "4px red solid");
        // } else if ($('#fromdate').val() == '') {
        //     $('#fromdate').css("border", "4px red solid");
        // } else if ($('#todate').val() == '') {
        //     $('#todate').css("border", "4px red solid");
        // } else if ($('#leavetype').val() == '') {
        //     $('#leavetype').css("border", "4px red solid");
        // } else {
        //     //On success leave added
        //     $.post("http://localhost:8080/quickSalon_war_exploded/addLeave", {
        //             leaverID: $("#empid").val(),
        //             fromDate: $("#fromdate").val(),
        //             toDate: $("#todate").val(),
        //             fromTime: $("#fromtime").val(),
        //             toTime: $("#totime").val(),
        //             leaveType: $("#leavetype").val()
        //         },
        //         function (data, status) {
        //             alert(data);
        //         }
        //     );
    
    
        //     $(".limiter").css({"filter": "blur(8px)", "-webkit-filter": "blur(8px)"});
        //     $(".center").css("display", "block");
        // }
    });
    

    


});