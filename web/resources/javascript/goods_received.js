var journal = [
    {grnNo:"GRN001", poNo:"PO001", date:"01/01/2020", vendorID:"VND001", acceptorID:"EMP001"},
    {grnNo:"GRN001", poNo:"PO001", date:"01/01/2020", vendorID:"VND001", acceptorID:"EMP001"},
    {grnNo:"GRN001", poNo:"PO001", date:"01/01/2020", vendorID:"VND001", acceptorID:"EMP001"},
    {grnNo:"GRN001", poNo:"PO001", date:"01/01/2020", vendorID:"VND001", acceptorID:"EMP001"}
  ];

  var i,j;

  for(i=0;i<journal.length;i++)
  {
      console.log(journal[i].name);
  }


var text= "";
for(i=0;i<journal.length;i++)
{
    text = text + "<tr>" +
    "<td>" + journal[i].grnNo + "</td>" +
    "<td>" + journal[i].poNo + "</td>" +
    "<td>" + journal[i].date + "</td>" +
    "<td>" + journal[i].vendorID + "</td>" +
    "<td>" + journal[i].acceptorID + "</td>" +
    "<td>" + "<button title='View GRN Details.' class = 'btn34' onclick = 'show()'>" + "<i class='fa fa-eye'>"+ "</i>" +  "</button>" + "</td>" +
     "</tr>";

}

document.getElementById("tbl").innerHTML = text;

function show()
{
    document.getElementById("pop").style.display="block";

    var journal2 = [
      {productID:"P001", productName:"Scissor", size:"Large", qty:"15", unitPrice:"500.00"},
      {productID:"P001", productName:"Scissor", size:"Large", qty:"15", unitPrice:"500.00"},
      {productID:"P001", productName:"Scissor", size:"Large", qty:"15", unitPrice:"500.00"},
      {productID:"P001", productName:"Scissor", size:"Large", qty:"15", unitPrice:"500.00"},
      {productID:"P001", productName:"Scissor", size:"Large", qty:"15", unitPrice:"500.00"}
    ];

    var i,j;

    for(i=0;i<journal2.length;i++)
    {
        console.log(journal2[i].name);
    }


    var text2 = "";

    for(i=0;i<journal2.length;i++)
    {
        text2 = text2 + "<tr>" +
        "<td>" + journal2[i].productID + "</td>" +
        "<td>" + journal2[i].productName + "</td>" +
        "<td>" + journal2[i].size + "</td>" +
        "<td>" + journal2[i].qty + "</td>" +
        "<td>" + journal2[i].unitPrice + "</td>" +
         "</tr>";
    }

    document.getElementById("tbl-popup").innerHTML = text2;
}

function hide()
{
    document.getElementById("pop").style.display="none";
}
