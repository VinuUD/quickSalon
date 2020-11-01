<%@ page import="java.util.ArrayList" %>
<%@ page import="com.g34.quicksalon.entity.ServiceProvider" %>
<%@ page import="com.g34.quicksalon.model.ServiceProviderModel" %>
<%@ page import="com.g34.quicksalon.database.DBConnection" %>
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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link rel="stylesheet" href="../resources/css/enqueuecustomer.css">
    <link rel="stylesheet" href="../resources/css/calendar.css">
    <link rel="stylesheet" href="../resources/css/modal.css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <title>Enqueue Customer</title>
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
                    <select class="select-opt">
                        <option>
                            <Select:d>Service </Select:d>
                        </option>
                        <option>Hair Cut</option>
                        <option>Option 3</option>
                        <option>Option 4</option>
                        <option>Option 5</option>
                    </select>
                </div>
                <!-- Service provider -->
                <div>
                    <select class="select-sp">
                        <option>Default</option>
                        <%
                            HashMap<String, String> spList = new HashMap<String, String>();
//                            This will return empId, and the full name
                            ServiceProviderModel serviceProviderModel = new ServiceProviderModel();
                            if(serviceProviderModel!=null){
                                spList=serviceProviderModel.getSPList();
                            }
                            for (String i : spList.values()) {
                            %>
                                <option><%=i%></option>
                            <%}%>
                    </select>
                </div>
            </div>


            <div class="row-2">

                <!-- Calendar -->
                <div class="calDiv">
                    <div class="calendar" id="calendar"></div>

                    <div class="add-btn">
                        <button class="btn34">Add</button>
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

<script src="../resources/javascript/enqueue_customer.js"></script>
</body>

</html>
