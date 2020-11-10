
function add()
{
    // dynamically create table row with dropdown

    // first column dropdown 
    var tr = document.createElement("tr");
    tr.setAttribute("class", "tr");

    var td = document.createElement("td"); 
    td.style.fontSize = "16px";
    
   
    

    var select = document.createElement("select");
    select.setAttribute("class","blo"); //should be there
    select.setAttribute("id","sel");
    select.onchange = function ()
    {
        td.innerHTML =  document.getElementById("sel").value;
    }
    
    
    var option = document.createElement("option");
    option.innerHTML = "Haircut";
    

    var option1 = document.createElement("option");
    option1.innerHTML = "Tattoo";

    var optionNew = document.createElement("option");
    optionNew.innerHTML = "Service";

    td.appendChild(select);
    select.appendChild(optionNew);
    select.appendChild(option);
    select.appendChild(option1);
    // tr.appendChild(td);

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


    // second column dropdown
    var td1 = document.createElement("td");
    td1.style.fontSize = "16px";
    

    var select1 = document.createElement("select");
    select1.setAttribute("class","blo"); //should be there
    select1.setAttribute("id","sel1");

    var optionNew1 = document.createElement("option");
    optionNew1.innerHTML = "S P";

    var option2 = document.createElement("option");
    option2.innerHTML = "Nimal";

    var option12 = document.createElement("option");
    option12.innerHTML = "Kaanthi";

    td1.appendChild(select1);
   
    select1.appendChild(optionNew1);
    select1.appendChild(option2);
    select1.appendChild(option12);
    tr.appendChild(td1);
    tr.appendChild(tdRemove);

    document.getElementById("add").appendChild(tr); //append table to tr tag


    // create row with option tag values

    document.getElementById("sel1").addEventListener("change", function ()
    {
        td1.innerHTML =  document.getElementById("sel1").value;
    });

}

function showRemove()
{
    document.getElementById("removePopup").style.display="block";
}

function hideRemove()
{
    document.getElementById("removePopup").style.display="none";
}

function remove(x)
{
    var i = x.parentNode.parentNode.rowIndex;
    document.getElementById("table34").deleteRow(i);
}





