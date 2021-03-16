function add() {
  var tr = document.createElement("tr");
  tr.setAttribute("class", "tr");

  var td = document.createElement("td");
  td.style.fontSize = "16px";

  var select = document.createElement("select");
  select.setAttribute("class", "blo"); //should be there
  select.setAttribute("id", "sel");

  // add remove button ///////////////////////////////////////////////////////

  var tdRemove = document.createElement("td");
  tdRemove.style.textAlign = "center";
  var icon = document.createElement("i");

  icon.onclick = function () {
    var x = icon.parentNode.parentNode.rowIndex;
    console.log(x);
    document.getElementById("table34").deleteRow(x); //function for delete row which makes dynamically
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
  option.setAttribute("class", "option");
  option.innerHTML = "waruna";

  var option1 = document.createElement("option");
  option1.setAttribute("id", "option");
  option1.innerHTML = "praba";

  var option3 = document.createElement("option");
  option3.setAttribute("id", "option");
  option3.innerHTML = "select S P";

  select.appendChild(option3);
  select.appendChild(option);
  select.appendChild(option1);
  td.appendChild(select);
  tr.appendChild(td);
  tr.appendChild(tdRemove);

  document.getElementById("add").appendChild(tr);

  document.getElementById("sel").addEventListener("change", function () {
    td.innerHTML = document.getElementById("sel").value;
    console.log("djh");
  });
}

function remove(x) {
  var i = x.parentNode.parentNode.rowIndex;
  document.getElementById("table34").deleteRow(i);
}
