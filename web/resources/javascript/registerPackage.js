
function add()
{
    var tr = document.createElement("tr");
    tr.setAttribute("class", "tr");

    var td = document.createElement("td");
    var td1 = document.createElement("td");
    var td2 = document.createElement("td");
   

    var select = document.createElement("select");
    select.setAttribute("class","blo"); //should be there
    select.setAttribute("id","sel");

     // add remove button ///////////////////////////////////////////////////////

     var tdRemove = document.createElement("td");
     tdRemove.style.textAlign = "center";
     var icon = document.createElement("i");
 
 
     icon.onclick = function (){
         var x = icon.parentNode.parentNode.rowIndex;
         document.getElementById("table34").deleteRow(x); //function for delete row which makes dynamically
         console.log(x);
     };
 
     var image = document.createElement("img");
     image.src = "../../resources/icons/cross.png";
     image.style.width = "20px";
     image.style.height = "20px";
     image.style.marginRight = "10px";
     image.style.cursor = "pointer";
 
     icon.appendChild(image);
     tdRemove.appendChild(icon);
 
 
     /////////////////////////////////////////////////////////////////////////////
    
    var option = document.createElement("option");
    option.setAttribute("class","option");
    option.innerHTML = "select service";

    var option1 = document.createElement("option");
    option1.setAttribute("id","option");
    option1.innerHTML = "Beard";

    var option2 = document.createElement("option");
    option2.setAttribute("id","option");
    option2.innerHTML = "Tatto";

    td.appendChild(select);
    select.appendChild(option);
    select.appendChild(option1);
    select.appendChild(option2);
    tr.appendChild(td);
    tr.appendChild(td1);
    tr.appendChild(td2);
   


    document.getElementById("add").appendChild(tr);

    

    document.getElementById("sel").addEventListener("change", function ()
    {
        tr.innerHTML = "<td>" + document.getElementById("sel").value + "</td>" + 
                        "<td style = 'text-align:center;'>" + "<button class = 'btn34' onclick = 'showContinue()'>" + "<i class='fa fa-eye'>"+ "</i>" + "</button>" + "</td>";
                        tr.appendChild(tdRemove);
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

function remove(x)
{
    var i = x.parentNode.parentNode.rowIndex;
    document.getElementById("table34").deleteRow(i);
}





