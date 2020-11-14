//Time class
class Time {
    constructor(hour, min) {
        this.hour = hour;
        this.min = min;
    }
    sub(time){
        var h=this.hour
        if(h<8){
            h=h+12;
        }
        var t1=h*60+this.min
        var t2=time.hour*60+time.min
        return new Time((Math.floor((t1-t2)/60)%12),(t1-t2)%60);
    }
    add(time){
        var m=(this.min+time.min)%60
        var h=this.hour+ time.hour+ Math.floor((this.min+time.min)/60)

        if(h>12){
            h=h%12;
        }
        return new Time(h,m);
    }
    getMinutes(){
        return parseInt(this.hour)*60+this.min
    }
    greater(time){
        var h1=this.hour
        var h2=time.hour
        if(h1<8){
            h1=h1+12;
        }
        if(h2<8){
            h2=h2+12;
        }
        if(h1>h2){
            return true;
        }else return h1 == h2 && this.min >= time.min;
    }
}


var serviceDetails=[];
var appointments=[];
var sid=null;

//Get all services
$(document).ready(function() {
    //GET service List
    $.get("enqueue_customer.jsp/serviceList", function(responseJson) {
        var $select = $("#servicedropdownlist");
        $.each(responseJson, function(index, service) {
            $("<option>").val(index).text(service.serviceName).appendTo($select);
            serviceDetails.push({
                serviceId:service.serviceID,
                service:service.serviceName,
                time:new Time(Math.floor(service.timeTaken/60),service.timeTaken%60)
            })
        });
    });

    //    Get all Appointments
    $.get("enqueue_customer.jsp/appointments", function(responseJson) {
        // var $select = $("#servicedropdownlist");
        $.each(responseJson, function(index, appointment) {
            //set date into 2020Jan05 format
            res = appointment.date.replace(",", "").split(" ");
            day=res[1]< 10 ? "0" + res[1] :res[1] ;
            selectId=res[2]+res[0]+day;
            $('#'+selectId).css('background-color', '#F58F79')
            appointments.push({
                qId:appointment.qId,
                date:appointment.date,
                startTime:new Time(parseInt(appointment.startTime.substr(0,2)),parseInt(appointment.startTime.substr(3,2))),
                endTime:new Time(parseInt(appointment.endTime.substr(0,2)),parseInt(appointment.endTime.substr(3,2)))
            })
        });

    });

});

//GET service Provider List according to the service
var spList;
$("#servicedropdownlist").change(function (){

    window.selectedServiceIndex=$("#servicedropdownlist option:selected").val();
    sid = serviceDetails[selectedServiceIndex].serviceId;
    window.timeTaken=serviceDetails[selectedServiceIndex].time

    $.get("enqueue_customer.jsp/serviceProviderList?sid="+sid, function(responseJSON) {
        var $select = $("#spdropdownlist");
        spList=responseJSON;
        $select.find("option").remove();
        $("<option>").val(0).text("Default").appendTo($select);
        $.each(responseJSON, function(index, sp) {
            $("<option>").val(sp.employeeId).text(sp.firstName+' '+sp.lastName).appendTo($select);
        });
    });
});

var apt_sp=[]; //appointment related to sps'

//Populate Free Times for selected Service Provider
$("#spdropdownlist").change(function (){
    window.queueIds=[];
    apt_sp=[]
    window.spId = $("#spdropdownlist option:selected").val();
    $.get("enqueue_customer.jsp/spappointments?spId="+spId, function(responseJSON) {
        $.each(responseJSON, function(index,qId) {
            queueIds.push(qId)
        });
        $.each(appointments, function(index,apt) {
            res = apt.date.replace(",", "").split(" ");
            day=res[1]< 10 ? "0" + res[1] :res[1] ;
            selectId=res[2]+res[0]+day;
            if(queueIds.includes(apt.qId)){
                apt_sp.push({
                    qId:apt.qId,
                    date:apt.date,
                    startTime:apt.startTime,
                    endTime:apt.endTime
                })
                $('#'+selectId).css('background-color', '#F58F79')
            }else{
                $('#'+selectId).css('background-color', 'inherit')
            }
        });

    });

});
//myAppointment

var myAppointment=[];
var date;

function freeSlots(obj){
    if(sid==null){
        alert("You are not selected a service...");
        // document.getElementById("servicedropdownlist").style
        return
    }

    if(apt_sp.length==0){
        apt_sp=appointments;
    }

    var year=obj.id.substr(0,4)
    var month=obj.id.substr(4,3)
    var day=obj.id.substr(7,)
    date=month+' '+day+', '+year;

    // Print date top of the modal (Heading)
    document.getElementById("day-slots").innerHTML=year+"-"+month+"-"+day;
    document.getElementById("time-popup").style.display='block'

    var spId=document.getElementById("spdropdownlist").value


    //Restricted time slots
    window.resTimes=[]

    //Calculate restricted time slots
    apt_sp.forEach(apt=>{
        if(apt.date==date){
            var t1=apt.startTime;
            t1=t1.sub(timeTaken)
            var t2=apt.endTime
            t1=t1.add(new Time(0,15))
            while(!t1.greater(t2)){
                resTimes.push(t1)
                t1=t1.add(new Time(0,15))
            }
        }
    })
}

//Onclick hours
function selectTime(hour){

    //Reset the form
    document.getElementById('0').style.display='block'
    document.getElementById('15').style.display='block'
    document.getElementById('30').style.display='block'
    document.getElementById('45').style.display='block'
    document.getElementById("lpm").style.display='block'
    document.getElementById("lam").style.display='block'

    if(hour.id<9){
        document.getElementById("lam").style.display='none'
    }else{
        document.getElementById("lpm").style.display='none'
    }

    //Filter time slots
    for(i=0;i<resTimes.length;i++){
        if(resTimes[i].hour==hour.id){
            document.getElementById(resTimes[i].min).style.display='none'
        }
    }
}

//Add appointment details to the table
//sid-serviceID
//
let appointmentArray=[]

function addAppointment(){

    var tableContent="";
    //Hour radio
    radios=document.getElementsByName("hour");
    for (var i = 0, length = radios.length; i < length; i++) {
        if (radios[i].checked) {
            hour=radios[i].id;
            break;
        }
    }

    //Min radio
    radios=document.getElementsByName("min");
    for (var i = 0, length = radios.length; i < length; i++) {
        if (radios[i].checked) {
            min=radios[i].id.split('_')[1];
            break;
        }
    }
    ampm='pm';
    if(document.getElementById("am").checked==true){
        ampm='am'
    }
    var selectedSp=document.getElementById('spdropdownlist')

    appointmentArray.push({
        spId: spId,
        serviceID: sid,
        customerName:document.getElementById('cname').value,
        telephone:document.getElementById('teleno').value,
        service: serviceDetails[selectedServiceIndex].service,
        spName:selectedSp.options[selectedSp.selectedIndex].text,
        date:date,
        time: hour+":"+min+" "+ampm,
        timeTaken:timeTaken
    });

    appointmentArray.forEach((appointment,index) =>{
        tableContent+="<tr><td>"+appointment.service+"</td><td>"+appointment.spName+"</td><td>"+appointment.date+"</td><td>"+appointment.time+"</td><td><button onclick='removeApt("+index+")'>x</button></td></tr>"
    })

    document.getElementById("table_appointment").innerHTML=tableContent

    document.getElementById("time-popup").style.display='none'
}

function removeApt(index){
    content=""
    appointmentArray.splice(index,1);

    appointmentArray.forEach((appointment,index) =>{
        content+="<tr><td>"+appointment.service+"</td><td>"+appointment.spName+"</td><td>"+appointment.date+"</td><td>"+appointment.time+"</td><td><button onclick='removeApt("+index+")'>x</button></td></tr>"
    })
    document.getElementById("table_appointment").innerHTML=content

}

//SPs names
function getSelectedOption(sel) {
    var opt;
    for ( var i = 0, len = sel.options.length; i < len; i++ ) {
        opt = sel.options[i];
        if ( opt.selected === true ) {
            break;
        }
    }
    return opt;
}

// spId: spId,
//     serviceID: sid,
//     customerName:document.getElementById('cname').value,
//     telephone:document.getElementById('teleno').value,
//     service: serviceDetails[selectedServiceIndex].service,
//     spName:selectedSp.options[selectedSp.selectedIndex].text,
//     date:date,
//     time: hour+":"+min+" "+ampm,
//     timeTaken:timeTaken

//Confirm data
function placeAppointment(){
        var len = document.getElementById("apt-table").rows.length-1;
        var json

        appointmentArray.forEach(appointment=>{
            $.post("enqueue_customer.jsp/spappointments",
                {
                    spId: appointment.spId,
                    serviceID: appointment.serviceID,
                    customerName:appointment.customerName,
                    telephone:appointment.telephone,
                    date:date,
                    time: appointment.time,
                    timeTaken:appointment.timeTaken
                },
                function(data, status){
                    console.log("Data: " + data + "\nStatus: " + status);
                });
        })
        for (i=0;i<len-1;i++){



        }
      // json =JSON.stringify(appointmentArray)



}

