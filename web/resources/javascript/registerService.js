
function add()
{
    var tr = document.createElement("tr");
    tr.setAttribute("class", "tr");

    var select = document.createElement("select");
    select.setAttribute("class","blo"); //should be there
    select.setAttribute("id","sel");
    
    var option = document.createElement("option");
    option.setAttribute("class","option");
    option.innerHTML = "waruna";

    var option1 = document.createElement("option");
    option1.setAttribute("id","option"); 
    option1.innerHTML = "praba";

    select.appendChild(option);
    select.appendChild(option1);
    tr.appendChild(select)


    document.getElementById("add").appendChild(tr);

    document.getElementById("sel").addEventListener("click", function ()
    {
        tr.innerHTML = "<td>" + document.getElementById("sel").value + "</td>";
    });


   
}






