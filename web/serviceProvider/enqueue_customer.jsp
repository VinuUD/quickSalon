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
                        <option value="0">Not Select</option>

                    </select>
                </div>
                <!-- Service provider -->
                <div>
                    <select id="spdropdownlist" class="select-sp">
                        <option>Default</option>
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
            <div class="tableDiv34">

                <table class="table34">
                    <thead>
                    <th>Time</th>
                    <th>Select</th>
                    </thead>
                    <tbody>
                    <tr>
                        <td>7.00 am - 9.00 am </td>
                        <td><input class="check-box" type="checkbox" ></td>
                    </tr>

                    <tr>
                        <td>10.00 am - 12.00 pm </td>
                        <td><input class="check-box" type="checkbox" ></td>
                    </tr>
                    <tr>
                        <td>10.00 am - 12.00 pm </td>
                        <td><input class="check-box" type="checkbox"></td>
                    </tr>

                    </tbody>
                </table>
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

    //Get all services
    $(document).ready(function() {
        var serviceDetails=new Array();
        var i=0;
        //GET service List
        $.get("enqueue_customer.jsp/serviceList", function(responseJson) {
            var $select = $("#servicedropdownlist");
            $.each(responseJson, function(index, service) {
                $("<option>").val(service.serviceID).text(service.serviceName).appendTo($select);
                serviceDetails.push({
                    id:index,
                    serviceId:service.serviceID,
                    time:service.timeTaken
                })
            });
        });

    //    Get all Appointments
        $.get("enqueue_customer.jsp/appointments", function(responseJson) {
            // var $select = $("#servicedropdownlist");
            $.each(responseJson, function(index, appointments) {
                 //set date into 2020Jan05 format
                 res = appointments.date.replace(",", "").split(" ");
                 day=res[1]< 10 ? "0" + res[1] :res[1] ;
                 selectId=res[2]+res[0]+day;
                $('#'+selectId).css('background-color', '#F58F79')
            });
        });


    });


        //GET service Provider List according to the service
    var spList;
    $("#servicedropdownlist").change(function (){
        var sid = $("#servicedropdownlist option:selected").val();
        $.get("enqueue_customer.jsp/serviceProviderList?sid="+sid, function(responseJSON) {
            var $select = $("#spdropdownlist");
            spList=responseJSON;
            $select.find("option").remove();
            $("<option>").val(0).text("Auto").appendTo($select);
            $.each(responseJSON, function(index, sp) {
                $("<option>").val(sp).text(sp).appendTo($select);
            });

        });
    });

</script>
</body>

</html>
