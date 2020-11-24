
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



