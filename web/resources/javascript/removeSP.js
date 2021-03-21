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
        //set name selected
        $("#empname").val($(this).text());
        //clear the suggestion list
        $('#search-list').empty();
        //set employee ID
        $("#empid").val($(this).attr('id'));


    //    Load upcoming appointments of the SP
        

    });


});