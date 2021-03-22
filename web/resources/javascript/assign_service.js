$( document ).ready(function() {

    $.get("http://localhost:8080/quickSalon_war_exploded/registerSP", function(data, status){
        $('#empid').val(data);
    });
    
});


