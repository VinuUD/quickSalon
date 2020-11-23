var journal = [
    {appNo:"Q001", customerID:"CST001", fullName:"Mary Jane", date:"01/01/2020", time:"10.00 AM"},
    {appNo:"Q001", customerID:"CST001", fullName:"Mary Jane", date:"01/01/2020", time:"10.00 AM"},
    {appNo:"Q001", customerID:"CST001", fullName:"Mary Jane", date:"01/01/2020", time:"10.00 AM"},
    {appNo:"Q001", customerID:"CST001", fullName:"Mary Jane", date:"01/01/2020", time:"10.00 AM"}
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
    "<td>" + journal[i].appNo + "</td>" +
    "<td>" + journal[i].customerID + "</td>" +
    "<td>" + journal[i].fullName + "</td>" +
    "<td>" + journal[i].date + "</td>" +
    "<td>" + journal[i].time + "</td>" +
    "<td>" + "<button title='View AOD Details.' class = 'danger34' onclick = 'show()'>" + "<i class='fa fa-times'>"+ "</i>" +  "</button>" + "</td>" +
     "</tr>";

}

document.getElementById("tbl").innerHTML = text;

function show()
{
    document.getElementById("pop").style.display="block";

}

function hide()
{
    document.getElementById("pop").style.display="none";
}
