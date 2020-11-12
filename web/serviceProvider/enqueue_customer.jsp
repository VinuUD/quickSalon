<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: SDW
  Date: 11/1/2020
  Time: 3:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="../resources/css/mainDiv.css">
    <link rel="stylesheet" href="../resources/css/34.css">
    <link rel="stylesheet" href="../resources/css/sideNavbar.css">
    <link rel="stylesheet" href="../resources/css/enqueuecustomer.css">
    <link rel="stylesheet" href="../resources/css/calendar.css">
    <link rel="stylesheet" href="../resources/css/modal.css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <title>Enqueue Customer</title>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

</head>

<body onresize="whenResizing()">
<!-- side nav bar -->
<div id="mySidenav" class="sidenav">
    <div class="closeIcon"><a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a></div>
    <a href="#">About</a>
    <a href="#">Services</a>
    <a href="#">Clients</a>
    <a href="#">Contact</a>
</div>

<!-- ////////////////////////////// -->
<div id="main">

    <div class="back">
        <div id="navIcon" onclick="openNav()" class="navIcon"><img src="../resources/icons/three.png" alt="Ooops">
        </div>
        <button class="btn34">Back</button>
    </div>
    <br><br>

    <div class="topBar">
        <b>Enqueue Customer</b>
    </div>

    <div class="mainDiv">

        <div class="subDiv">

            <div class="row-1">
                <!-- Name box -->
                <div>
                    <input class="name-txt" placeholder="Enter Customer Name" id="name" type="text">
                </div>

                <!--Select box  -->
                <div>
                    <select id="servicedropdownlist" class="select-opt">
                        <option value="-1">Not Select</option>

                    </select>
                </div>
                <!-- Service provider -->
                <div>
                    <select id="spdropdownlist" class="select-sp">
                        <option value="0">Default</option>
                    </select>
                </div>
            </div>


            <div class="row-2">

                <!-- Calendar -->
                <div class="calDiv">
                    <div class="calendar" id="calendar"></div>

                    <div class="add-btn">
                        <button id="buttonLoad" class="btn34">Add</button>
                    </div>
                </div>


                <div class="tableDiv34 table">
                    <table id='table_temp' class="table34">
                        <thead>
                        <tr>
                            <th>Service</th>
                            <th>Service Provider</th>
                            <th>Time</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>

                            <td>Hair Cut</td>
                            <td>H K James</td>
                            <td>10.30 a.m</td>
                        </tr>

                        <tr>
                            <td>Beard Trim</td>
                            <td>H P Perera</td>
                            <td>11.30 a.m</td>
                        </tr>
                        </tbody>
                    </table>
                    </br>
                    <!-- Button -bottem -->
                    <div class="cancel-btn">
                        <button class="danger34">Cancel</button>
                    </div>

                    <diV class="confirm-btn">
                        <button  id="confirm"  class="btn34">Confirm</button>
                    </div>

                </div>


            </div>
        </div>
    </div>

    <!-- Pop up time box -->
    <div class="time-modal" id="time-popup">

        <div class="modal-content">

            <div class="title">
                <div id="day-slots"></div>
                <button class="close t-modal-close"><i class="fa fa-close"></i></button>
            </div>
            <div>
    <h>Hour</h>
    <div class="radio-toolbar">
        <input id='1' onclick="selectTime(this)" name='hour' type="radio" ><label  for="1">1</label>
        <input id='2' onclick="selectTime(this)" type="radio" name='hour' ><label  for="2">2</label>
        <input id='3' onclick="selectTime(this)" type="radio" name='hour'><label for="3">3</label>
        <input id='4' onclick="selectTime(this)" type="radio" name='hour'><label for="4">4</label>
        <input id='5' onclick="selectTime(this)" name='hour' type="radio" ><label for="5">5</label>
        <input id='6' onclick="selectTime(this)" type="radio" name='hour' ><label for="6">6</label>
        <input id='9' onclick="selectTime(this)" name='hour' type="radio" ><label for="9">9</label>
        <input id='10' onclick="selectTime(this)" type="radio" name='hour' ><label for="10">10</label>
        <input id='11' onclick="selectTime(this)" type="radio" name='hour'><label for="11">11</label>
        <input id='12' onclick="selectTime(this)" type="radio" name='hour'><label for="12">12</label>

    </div>

    <h>Min</h>
    <div class="radio-toolbar">
        <input id='m0' name='min' type="radio" ><label id='0' for="m0">0</label>
        <input id='m15' type="radio" name='min' ><label id='15' for="m15">15</label>
        <input id='m30' type="radio" name='min'><label id='30' for="m30">30</label>
        <input id='m45' type="radio" name='min'><label id='45' for="m45">45</label>

    </div>

    <h>AM/PM</h>
    <div class="radio-toolbar">
        <input id='am' name='ampm' type="radio" ><label id='lam' for="am">AM</label>
        <input id='pm' type="radio" name='ampm' ><label id='lpm' for="pm">PM</label>

    </div>
                <button class="btn34 modal-btn" >Confirm</button>
            </div>
        </div>
    </div>

    <!-- Modal -->
    <div id="modal" class="modal">
        <div class="modal-cont">
            <div class="title">Please Confirm !</div>
            <div class="cont">Are you sure you want to continue ?</div>
            <button id='cancel' class="cancel-modal-btn btn-danger34">Cancel</button>
            <button class="confirm-modal-btn btn-success34">Confirm</button>
        </div>
    </div>
</div>

<script src="../resources/javascript/side_navbar.js"></script>
<script src="../resources/javascript/calendar.js"></script>
<script src="../resources/javascript/modal.js"></script>

<%--<script src="../resources/javascript/enqueue_customer.js"></script>--%>

<script>
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
            }else if(h1==h2 && this.min>=time.min){
                return true;
            }else{
                return false
            }
        }

    }


    var serviceDetails=new Array();
    var appointments=new Array();

    //Get all services
    $(document).ready(function() {
        var i=0;
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
       // var sid = $("#servicedropdownlist option:selected").val();
        window.selectedServiceIndex=$("#servicedropdownlist option:selected").val();
        var sid = serviceDetails[selectedServiceIndex].serviceId;

        window.timeTaken=serviceDetails[selectedServiceIndex].time
        //window.timeTaken=new Time(serviceDetails[selectedServiceIndex].time)

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


    //Populate Free Times for selected Service Provider
    $("#spdropdownlist").change(function (){
        window.queueIds=new Array();
        window.apt_sp=new Array();
        var spId = $("#spdropdownlist option:selected").val();

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




    function freeSlots(obj){
        var year=obj.id.substr(0,4)
        var month=obj.id.substr(4,3)
        var day=obj.id.substr(7,)
        date=month+' '+day+', '+year;

        // Print date top of the modal (Heading)
        document.getElementById("day-slots").innerHTML=year+"-"+month+"-"+day;
        document.getElementById("time-popup").style.display='block'

        var spId=document.getElementById("spdropdownlist").value

        window.resTimes=new Array()

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

</script>
</body>

</html>
