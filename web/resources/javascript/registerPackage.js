
function add()
{
    var tr = document.createElement("tr");
    tr.setAttribute("class", "tr");

    var td = document.createElement("td");
    var td1 = document.createElement("td");

    var select = document.createElement("select");
    select.setAttribute("class","blo"); //should be there
    select.setAttribute("id","sel");
    
    var option = document.createElement("option");
    option.setAttribute("class","option");
    option.innerHTML = "Tattoo";

    var option1 = document.createElement("option");
    option1.setAttribute("id","option");
    option1.innerHTML = "Beard";

    td.appendChild(select);
    select.appendChild(option);
    select.appendChild(option1);
    tr.appendChild(td);
    tr.appendChild(td1);


    document.getElementById("add").appendChild(tr);

    document.getElementById("sel").addEventListener("click", function ()
    {
        tr.innerHTML = "<td>" + document.getElementById("sel").value + "</td>" + 
                        "<td style = 'text-align:center;'>" + "<button class = 'btn34' onclick = 'showContinue()'>" + "</button>" + "</td>";
    });

}

function showContinue()
{
    document.getElementById("mainPopupDivContinue").style.display="block";
}

function hideContinue()
{
    document.getElementById("mainPopupDivContinue").style.display="none";
}





