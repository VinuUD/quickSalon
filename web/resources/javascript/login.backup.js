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

            if(parseInt(data)==1){
                window.location.href='../../quickSalon_war_exploded/upperStaff/owner/ownerHome.html';
            }
            else if(parseInt(data)==2){
                window.location.href='../../quickSalon_war_exploded/upperStaff/manager/manager_home.html';
            }else if(parseInt(data)==3){
                window.location.href='../../quickSalon_war_exploded/serviceProvider/sp_home.html';
            }
            else if(parseInt(data)==4){
                window.location.href='../../quickSalon_war_exploded/customer/cust_home.html';
            }
            else {
                alert(data);
            }

            // window.location.href='../../quickSalon_war_exploded/'+data+'.html'
            // alert("Data: " + data + "\nStatus: " + status);
        }
     );
   //}
});


