
$( document ).ready(function() {

    var index=0;


    
    var now = new Date();
    var day = ("0" + now.getDate()).slice(-2);
    var month = ("0" + (now.getMonth() + 1)).slice(-2);
    var today = now.getFullYear()+"-"+(month)+"-"+(day) ;
    $('#date').val(today);


    $.get("http://localhost:8080/quickSalon_war_exploded/serviceProvider/personalTodaySchedule",
        function(data, status){
            
            todayMyappointmentList=data;
            console.log(data);
        }
    );



    




  //onclick insert name to name field
    $(document.body).on('click', '#complete-btn' ,function(){
        console.log(todayMyappointmentList);
          alert('btn')

    });


    $(document.body).on('click', '#nxt-icon' ,function(){

        $('#prev-icon').css("display","inline-block");
        index ++;

        alert('icon')
    });

    $(document.body).on('click', '#prev-icon' ,function(){
        index --;
        if(index==0){
            $('#prev-icon').css("display","none");
        }
        
        alert('prev-icon')
    });


    function loadAppointmentData(){


    }




});




