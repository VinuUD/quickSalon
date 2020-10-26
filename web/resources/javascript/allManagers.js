var journal = [
    {empId:"e001", name:"shehan sandeepa", cNum:"123", nic:"1234", Salary:"100000"},
    {empId:"e001", name:"shehan sandeepa", cNum:"123", nic:"1234", Salary:"100000"},
    {empId:"e001", name:"shehan sandeepa", cNum:"123", nic:"1234", Salary:"100000"},
    {empId:"e001", name:"shehan sandeepa", cNum:"123", nic:"1234", Salary:"100000"},

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
    "<td>" + journal[i].empId + "</td>" +
    "<td>" + journal[i].name + "</td>" +
    "<td>" + journal[i].cNum + "</td>" +
    "<td>" + journal[i].nic + "</td>" +
    "<td>" + "<button title='view employee details' class = 'btn34' onclick = 'show()'>" + "<i class='fa fa-eye'>"+ "</i>" +  "</button>" + "</td>" +
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

function show2()
{
    document.getElementById("pop1","pop3").style.display="block";
}

function hide2()
{
    document.getElementById("pop1").style.display="none";
}

function show3()
{
    document.getElementById("pop3").style.display="block";
}

function hide3()
{
    document.getElementById("pop3").style.display="none";
}








  