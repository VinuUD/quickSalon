
var i = 0;
function cng()
{
    i = i + 1;
    console.log(i);
    return i;
}

function redirect()
{
    if(i > 0)
    {
         document.getElementById("mainPopupDivBack").style.display="block";
         console.log(i);

    }
    else
    {
        return 0;
    }
}

// function change()
// {
//     if(i==0)
//     {
//         return 0;
//     }
//
//     else
//     {
//         document.getElementById("mainPopupDivBack").style.display="block";
//         i=0;
//     }
// }



function hide()
{
    document.getElementById("mainPopupDiv").style.display="none";
}

function show()
{
    document.getElementById("mainPopupDivCal").style.display="block";
}

function showContinue()
{
    document.getElementById("mainPopupDivContinue").style.display="block";
}

function hideContinue()
{
    document.getElementById("mainPopupDivContinue").style.display="none";
}

function hideBack()
{
    document.getElementById("mainPopupDivBack").style.display="none";
}


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
    var firstName = $("#firstName").val();
    var lastName = $("#lastName").val();
    var cNum = $("#cNum").val();
    var email = $("#email").val();
    var nic = $("#nic").val();
    var address = $("#address").val();
    var salary = $("#salary").val();

    var arr = [firstName, lastName, cNum, email, nic, address, salary ];
    console.log(firstName);
    var arr2 = ["FirstName", "LastName", "ContactNumber", "Email", "NIC", "Address", "Salary" ];
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

    let email = $("#email").val();
    if(!email.includes("@") && email.includes(" "))
    {
        alert("missing @ symbol and there is a space in email field!");
        return false;
    }
    else if(!email.includes("@"))
    {
        alert("missing @ symbol!");
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

const stringCheck = () => {

   
    let contactNumber = $("#cNum").val();
    let numArr = contactNumber.split("");
    console.log(parseInt("1"));
    for(var i = 0; i< numArr.length; i++)
    {
        if(!parseInt(numArr[i]))
        {
            return false;
        }
    }

    
    return true;
}


const numberCheck = () => {

    if(!stringCheck())
    {
        alert("There are/is string/s in the contact number field !")
    }
    else
    {
        return true;
    }
}

function confirm()
{
    if(Empty() && emailCheck() && numberCheck())
    {
        showContinue();
    }
}

