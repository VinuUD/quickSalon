var i = 0;

function hello()
{
    i = i + 1;
    return i;
}

function change()
{
    if(i==0)
    {
        return 0;
    }

    else
    {
        document.getElementById("mainPopupDiv").style.display="block";
        i=0;
    }
}

function hide()
{
    document.getElementById("mainPopupDiv").style.display="none";
}

function show()
{
    document.getElementById("mainPopupDivCal").style.display="block";
}