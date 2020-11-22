var index = 0;

setInterval(function (){
    var images = ["resources/images/login1.jpg","resources/images/login3.jpg","resources/images/login5.jpg"];

    // var randNum = Math.floor(Math.random()*images.length);
    var image = images[index];
    index = index + 1;
    if(index>2) {
        index = 0;
    }

    document.getElementById("background").style.backgroundImage = "url("+image+")";
    console.log(image);

}, 10000);


$("#submit").click(function(){

    var username = $("#username").val().trim();
    var password = $("#password").val().trim();

    //alert(username+'='+password);

    // if( username != "" && password != "" ){
    $.post("login",
        {
            username:username,
            password:password
        },
        function(data, status){
            alert("Data: " + data + "\nStatus: " + status);
        }
     );
   //}
});



