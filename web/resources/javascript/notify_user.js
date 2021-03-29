var users=[];

var selectedUserGroup=[];

function addUsrGroup(){

   

}

function remove(index){
    users.splice(index,1);

    content=''
    users.forEach((user,index)=>{
            content+="<div class='row'>"+user+"<button class='remove' onclick='remove("+index+")'>-</button></div>";
        }

    );

    document.getElementById('userrow').innerHTML=content;
}



document.getElementById("back").onclick=function(event){
    backmodal.style.display='block'
}


document.getElementById("send-btn").onclick=function(event){
    modal.style.display='block'
}


document.getElementById("No").onclick=function(event){
    backmodal.style.display='none'
}

window.onclick = function(event) {
    if (event.target == document.getElementById("backmodal")) {
        backmodal.style.display = "none";
    }
}





$('#addbtn').on('click', function(){
    //load user group
    var usrGroup=document.getElementById("usergroup").value;
    if(users.includes(usrGroup)){
        alert("Already Selected !")
    }
    else{
        users.push(usrGroup);
    }
    content=''
    users.forEach((user,index)=>{
            content+="<div id= class='row'>"+user+"<button class='remove' onclick='remove("+index+")'>-</button></div>";
        }
    );
    document.getElementById('userrow').innerHTML=content;


    selectedUserGroup.push($("#usergroup").val());




});

$("#usergroup").change(function () {
    var selectedVal = $(this).val();

    // alert(selectedVal)

});


$('#send-btn').on('click', function(){

    $.post("http://localhost:8080/quickSalon_war_exploded/notifyUsers", {
       userGroup:selectedUserGroup
    },
    function (data, status) {

        }
    );

});