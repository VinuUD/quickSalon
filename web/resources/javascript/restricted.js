
function checkRedirect(){
    
    $.get("http://localhost:8080/quickSalon_war_exploded/session?utype="+'1', function (userType) {
        if(userType == 9){
            window.location.href='../quickSalon_war_exploded/index.html';  
            // window.location.href='../../quickSalon_war_exploded/customer/cust_home.html';
        }

        else if(userType == 1){
            window.location.href='../quickSalon_war_exploded/upperStaff/owner/ownerHome.html';
        }

        else if(userType == 2){
            window.location.href='../quickSalon_war_exploded/upperStaff/manager/manager_home.html';
        }

        else if(userType == 3){
            window.location.href='../quickSalon_war_exploded/serviceProvider/sp_home.html';
        }

        else if(userType == 4){
            window.location.href='../quickSalon_war_exploded/customer/cust_home.html';
        }
    });

}
