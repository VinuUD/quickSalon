$( document ).ready(function() {

    $.get("http://localhost:8080/quickSalon_war_exploded/serviceList", function(data, status){
        $('#empid').val(data);
    });
    
});

