$('#signup').click(function(){


    var fname = $("#fname").val().trim();
    var lname = $("#lname").val().trim();
    var uname = $("#uname").val().trim();
    var contactno = $("#contactno").val().trim(); 
    var nic = $("#nic").val().trim();
    var email = $("#email").val().trim();
    var address = $("#address").val().trim();
    var password = $("#password").val().trim();
    var passwordRepeat = $("#password-repeat").val().trim();


        //     $.ajax({
        //     type: 'POST',
        //     url: 'http://localhost:8080/quickSalon_war_exploded/registercustomer',
        //     data: {
        //                 fname:fname,
        //                 lname:lname,
        //                 uname:uname,
        //                 contactno:contactno,
        //                 nic:nic,
        //                 email:email,
        //                 address:address,
        //                 password:password
        //             },
        //     success:( function(response)
        //     {
        //         alert(response);

        //         if(response ==1)
        //         {
        //             alert("Customer Added successfully!");
        //             window.location.replace("http://localhost:8080/quickSalon_war_exploded/login.html");
        //         }
        //         else if(response==0){
        //             alert("Customer added failed");
        //         }

        //     })

        //   });



    function isEmpty(myVar)
{
    if(myVar === '')
    {
        return true;
    }
    else
    {
        return false;
    }
}

function Empty()
{

    var arr = [fname, lname, uname, contactno,nic , email, address, password, passwordRepeat];
    console.log(fname);
    var arr2 = ["FirstName", "LastName","UserName" ,"ContactNumber",  "NIC", "Email","Address", "Password", "PasswordRepeat" ];
    var emptyIndex = [];
    var empty = [];
    var length = arr.length;
    for(let i = 0; i<length; i++)
    {
        if(isEmpty(arr[i]))
        {
            emptyIndex.push(i);
        }
    }

    for(let i = 0; i< emptyIndex.length; i++)
    {
        empty.push(arr2[emptyIndex[i]]);
    }

    console.log(empty);

    if(empty.length == 0)
    {
        return true;
    }
    else if(empty.length == 1)
    {
        alert(empty[0] + " field is empty!")
    }
    else
    {
        let allEmpty = '';
        for(var j = 0; j< empty.length; j++ )
        {
             allEmpty = allEmpty + empty[j] + "  "  ; 
        }

        alert(allEmpty + " fields are empty!");
    }

}

const emailCheck =  () => {

    if(!email.includes("@") && email.includes(" "))
    {
        alert("missing @ symbol and there is a space in email field!");
        return false;
    }
    else if(!email.includes("@"))
    {
        alert("missing @ symbol in the email field!");
    }
    else if(email.includes(" "))
    {
        alert("There is a space in the email field");
    }
    else
    {
        return true;
    }
    
}

const stringCheck = () => { //string ekak apuwahama methana return wenne false;

    let numArr = contactno.split("");
    
    for(var i = 0; i< numArr.length; i++)
    {
        if(!parseInt(numArr[i]) && numArr[i] != "0" )
        {
            return false;
        }
    }

    
    return true;
}


 const numLen = () => {

    let len = contactno.length;
    if(len == 10)
    {
        return true;
    }
    else
    {
        return false;
    }

 }



const numberCheck = () => {
    if(!stringCheck() && !numLen())
    {
        alert("There are/is string/s in the contact number field ! please enter 10 digit number to the contact number field!")
    }
    else if(!stringCheck())
    {
        alert("There are/is string/s in the contact number field !")
    }
    else if(!numLen())
    {
        alert("Please enter 10 digit number to the contact number field!");
    }
    else
    {
        return true;
    }
}

const passwordMatch =() => {

    if(password === passwordRepeat)
    {
        return true;
    }
    else
    {
        alert("password field and Repeat password field does not match!");
    }
}

    if(Empty() && emailCheck() && numberCheck() && passwordMatch())
    {
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/quickSalon_war_exploded/registercustomer',
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
    }
        

});