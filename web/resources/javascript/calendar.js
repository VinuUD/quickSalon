
let calendarShow=1;

//return date obj
function settingDate(date,day){
    date=new Date(date);
    date.setDate(day);
    date.setHours(23);
    return date;
}

//date range
function getDatesBetween(date1,date2){

    let dates=[];
    let range1=new Date(date1);
    let range2= new Date(date2);

    date1=settingDate(date1,31);
    date2=settingDate(date2,31);

    while(date1<=date2){
        if(date1.getDate()!=31){
            temp=settingDate(date1,0);

            if(temp>=range1 && temp<=range2) dates.push(temp);
            date1=settingDate(date1,31);

        }else{
            temp=new Date(date1);
            if(temp>=range1 && temp<=range2) dates.push(temp);
            date1.setMonth(date1.getMonth()+1);
        }
    }
    ///console.log(dates);
    let content="";
    let lastDate,firstDate;
    let weekDays=["Mon","Tue","Wed","Thu","Fri","Sat","Sun"];
    for(let i=0; i<dates.length; i++){
        lastDate=dates[i];
        //get te month
        firstDate=new Date(dates[i].getFullYear(),dates[i].getMonth(),1);


        content+="<div class='calendarDiv' id='calendarTable_"+(i+1)+"'>";

        //Month id=Jan/2020
        content+="<div id="+dates[i].getFullYear()+'/'+firstDate.toString().split(" ")[1] +" class='month'> <ul> <li class='prev' id='prev' onclick='callPrev()'>&#10094;</li> <li class='nxt' id='nxt' onclick='callNxt()' >&#10095;</li> <li>"+firstDate.toString().split(" ")[1]+"-"+firstDate.getFullYear()+"</li></ul></div>";
        content+="<table class='calendar-table'>"
        content+= "<thead> <th>Mon</th> <th>Tue</th> <th>Wed</th> <th>Thu</th> <th>Fri</th> <th>Sat</th> <th>Sun</th> </thead>";
        content+="<tbody class='calendar-body'>";
        let j=1
        let displayNum;

        while(j<=lastDate.getDate()){
            content+="<tr>"
            for(let k=0; k<7; k++){
                displayNum=j< 10 ? "0" + j : j;
                if(j ==1){
                    if(firstDate.toString().split(" ")[0]==weekDays[k].toString() ){
                        // td id='2020Jan1' format
                        content+="<td id="+dates[i].getFullYear()+firstDate.toString().split(" ")[1]+displayNum+" onclick='freeSlots(this)'>"+ displayNum +"</td>"
                        j++;
                    }else{
                        content+="<td> </td>";
                    }
                }else if(j>lastDate.getDate()){
                    content+="<td> </td>";
                }else{
                    // td id='2020/Jan/1' format
                    content+="<td id="+dates[i].getFullYear()+firstDate.toString().split(" ")[1]+displayNum+" onclick='freeSlots(this)'>"+displayNum+"</td>"
                    j++;
                }
            }
            content+=" </tr>";
        }
        content+="</tbody></table></div>";
    }
    return content;
}

function callNxt(){
    //for 3 months
    if(calendarShow==3){
        return;
    }
    let allTables=document.getElementsByClassName("calendarDiv");
    document.getElementById("prev").disabled=false;
    calendarShow++;

    for(let i=0; i<allTables.length; i++){
        allTables[i].style.display="none";
    }

   document.getElementById("calendarTable_" +calendarShow).style.display="block";
}

function callPrev(){
    if(calendarShow==1){
        return;
    }

    let allTables=document.getElementsByClassName("calendarDiv");
    calendarShow--;

    for(let i=0; i<allTables.length; i++){
        allTables[i].style.display="none";
    }

    document.getElementById("calendarTable_" +calendarShow).style.display="block";
}



function closePopup(){
    document.getElementById("time-popup").style.display='none'
}

var modal = document.getElementById("time-popup");

window.onclick=function(event){
    if(event.target== modal){
        modal.style.display='none'
    }
}

var span = document.getElementsByClassName("close")[0];

span.onclick=function(){
    modal.style.display = "none";
}

var range1 = new Date();
var range2 = new Date();
range2.setDate(range2.getDate()+100);
console.log(range2)
let content=getDatesBetween(range1,range2);


document.getElementById('calendar').innerHTML=content;

/* NOTE
  Add <div class="calendar" id="calendar"></div> in Html page along with the calendar.css

  Add clickable to cells, change the 60 & 68 lines onclick='#' to your function
*/


document.getElementById("cancel").onclick=function(event){
    modal.style.display='none'
}


