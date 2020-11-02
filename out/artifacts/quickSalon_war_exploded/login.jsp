<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html >
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="resources/css/login.css">
    <link rel="stylesheet" href="resources/css/34.css">
    <title>Login</title>
</head>
<body>
 <div id="background" class="background">
    </div>

    <div class="mainDiv">

        <div class="innerDiv">
           <img style="width: 150px; height: 150px; opacity: 0.5;"  src="resources/icons/user.png" alt="">
        </div>

        <form action="login" method="post">

            <div class="innerDiv">
                <input class="input" type="text" style="font-family:Arial, FontAwesome"  placeholder=' &#xF007 &nbsp Username'>
            </div>

            <div class="innerDiv">
                <input class="input" type="text" style="font-family:Arial, FontAwesome"  placeholder=' &#xF023 &nbsp Password'>
            </div>

            <div class="buttonDiv">

                <button type="submit"> <b>Log In</b> </button>
            </div>

            <div  style="margin-top: 15px;" class="innerDiv">
                <small style="color: white;">Forgot Password?</small>
            </div>

        </form>
        <div  style="margin-top: 15px;" class="innerDiv">
            <small style="color: white;">Forgot Password?</small>
        </div>
        
     
 </div>
<script src="resources/javascript/login.js"></script>
</body>
</html>