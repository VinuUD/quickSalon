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
                    <label for="cname">Customer Name</label>
                    <input id="cname" class="name-txt" placeholder="Enter Customer Name" type="text" required>
                </div>

                <div>
                    <label for="teleno">Telephone No</label>
                    <input id="teleno" class="name-txt" placeholder="Enter telephone number" type="text" required>
                </div>
            </br>

                <!--Select box  -->
                <div>
                    <label for="servicedropdownlist">Service</label>
                    <select id="servicedropdownlist" class="select-opt" >
                        <option value="0">Not selected</option>
                    </select>
                </div>
                <!-- Service provider -->
                <div>
                    <label for="spdropdownlist">Service</label>
                    <select id="spdropdownlist" class="select-sp">
                        <option value="0">Default</option>
                    </select>
                </div>
            </div>


            <div class="row-2">
                <!-- Calendar -->
                <div class="calDiv">
                    <div class="calendar" id="calendar"></div>
                </div>

                <div class="tableDiv34 table">
                    <table id="apt-table" class="table34">
                        <thead>
                        <tr>
                            <th>Service</th>
                            <th>Service Provider</th>
                            <th>Date</th>
                            <th>Time</th>
                            <th>Remove</th>
                        </tr>
                        </thead>
                        <tbody id='table_appointment'>

                        </tbody>
                    </table>
                    </br>
                    <!-- Button -bottem -->
                    <div class="cancel-btn">
                        <button class="danger34">Cancel</button>
                    </div>

                    <diV class="confirm-btn">
                            <button type="submit" id="confirm" class="btn34" >Confirm</button>
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
        <input id='m_0' name='min' type="radio" ><label id='0' for="m_0">0</label>
        <input id='m_15' type="radio" name='min' ><label id='15' for="m_15">15</label>
        <input id='m_30' type="radio" name='min'><label id='30' for="m_30">30</label>
        <input id='m_45' type="radio" name='min'><label id='45' for="m_45">45</label>

    </div>

    <h>AM/PM</h>
    <div class="radio-toolbar">
        <input id='am' name='ampm' type="radio" ><label id='lam' for="am">AM</label>
        <input id='pm' type="radio" name='ampm' ><label id='lpm' for="pm">PM</label>

    </div>
                <button id="add" onclick="addAppointment()" class="btn34 modal-btn" >Add</button>
            </div>
        </div>
    </div>

    <!-- Modal -->
    <div id="modal" class="modal">
        <div class="modal-cont">
            <div class="title">Please Confirm !</div>
            <div class="cont">Are you sure you want to continue ?</div>
            <button id='cancel' class="cancel-modal-btn btn-danger34">Cancel</button>
            <button class="confirm-modal-btn btn-success34" onclick="placeAppointment()">Confirm</button>
        </div>
    </div>
</div>

<script src="../resources/javascript/side_navbar.js"></script>
<script src="../resources/javascript/calendar.js"></script>
<script src="../resources/javascript/modal.js"></script>
<script src="../resources/javascript/enqueuecustomer.js"></script>

</body>

</html>
