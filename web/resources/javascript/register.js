$("#signup").click(function(){

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
    var passwordRepeat = $("#password").val().trim();

    //alert(username+'='+password);

    // if validate pass
    $.post("registercustomer",
        {
            fname:fname,
            lname:lname,
            uname:uname,
            contactno:contactno,
            nic:nic,
            email:email,
            address:address,
            password:password
        },
        function(data, status){
            alert("Data: " + data + "\nStatus: " + status);
        }
    );

});