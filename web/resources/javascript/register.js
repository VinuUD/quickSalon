$('#signup').click(function(){

    // fname
    // lname
    // uname
    // contactno
    // nic
    // email
    // address
    // password
    // password-repeat

    var fname = $("#fname").val().trim();
    var lname = $("#lname").val().trim();
    var uname = $("#uname").val().trim();
    var contactno = $("#contactno").val().trim();
    var nic = $("#nic").val().trim();
    var email = $("#email").val().trim();
    var address = $("#address").val().trim();
    var password = $("#password").val().trim();
    var passwordRepeat = $("#password-repeat").val().trim();

    //alert(username+'='+password);

    //if validate pass
        $.ajax({
            type: 'POST',
            url: 'registercustomer',
            data: {
                        fname:fname,
                        lname:lname,
                        uname:uname,
                        contactno:contactno,
                        nic:nic,
                        email:email,
                        address:address,
                        password:password
                    },
            success:( function(response)
            {

                if(response ==1)
                {
                    alert("Customer Added successfully!");
                    window.location.replace("http://localhost:8080/quickSalon_war_exploded/login.html");
                }
                else if(response==0){
                    alert("Customer added failed");
                }

            })

        });

});