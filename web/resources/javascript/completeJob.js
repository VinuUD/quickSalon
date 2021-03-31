
todayMyappointmentList=[]
$( document ).ready(function() {

    // alert(service.length)

    var index=0;
    var now = new Date();
    var day = ("0" + now.getDate()).slice(-2);
    var month = ("0" + (now.getMonth() + 1)).slice(-2);
    var today = now.getFullYear()+"-"+(month)+"-"+(day) ;
    $('#date').val(today);

    // loadAppointmentData();

    $.get("http://localhost:8080/quickSalon_war_exploded/serviceProvider/personalTodaySchedule",
        function(data, status){
            todayMyappointmentList=data;
            loadAppointmentData(data,index);
            // console.log(data);
            if(todayMyappointmentList.length==0){
                $('#nxt-icon').css("display","none");
                $('#prev-icon').css("display","none");
                $('#complete-btn').css('display','none')
            }

        }
    );

  //onclick insert name to name field
    $(document.body).on('click', '#complete-btn' ,function(){

        if(confirm("Do you want  to continue ?")){
            //console.log(todayMyappointmentList[index].qID);
            $.get("http://localhost:8080/quickSalon_war_exploded/dequeueCustomer",{ qId:todayMyappointmentList[index].qID},
                function(data, status){
                    if(data==1){
                        alert("Completed....");
                    }else{
                        alert("Sorry..You can't make complete this job !");
                    }
                    location.reload();
                    // todayMyappointmentList=data;
                    // loadAppointmentData(data,index);
                    // // console.log(data);
                }
            );

        }
        // console.log(todayMyappointmentList);
          //alert('btn')

    });




    $(document.body).on('click', '#nxt-icon' ,function(){

        $('#prev-icon').css("display","inline-block");
        if(index == todayMyappointmentList.length-1){
            $('#nxt-icon').css("display","none");
            index--;
        }
        index ++;
        loadAppointmentData(todayMyappointmentList,index)

        //Make complete btn disable
        if(index!=0){
            $('#complete-btn').css('display','none')
        }
        console.log(index);
        // alert('icon')
    });

    $(document.body).on('click', '#prev-icon' ,function(){
        
        if(index==0){
            $('#prev-icon').css("display","none");
            $('#complete-btn').css('display','inline-block')
        }else{
            index --;
        }

        console.log(index);

        loadAppointmentData(todayMyappointmentList,index);
        
    });

    function loadAppointmentData(data,i){
        $("#qid").text('Q'+data[i].qID);
        $("#name").text(data[i].firstName+' '+data[i].lastName);
        $("#service").text(data[i].serviceName);
        $("#time").text(data[i].startTime+' - '+data[i].endTime);
    
    }




});




