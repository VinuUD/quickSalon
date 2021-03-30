var users=[];

var selectedUserGroup=[];

$(document).on("click", ".remove", function () {

    var id=$(this).closest('td').siblings().attr("id");
    const index = selectedUserGroup.indexOf(id);
    if (index > -1) {
        selectedUserGroup.splice(index, 1);
    }

    // console.log(selectedUserGroup);

    $(this).closest('tr').remove();

});





$('#addbtn').on('click', function(){

    if(selectedUserGroup.includes($("#usergroup option:selected").val())){
        alert(" Already Selected !")
    }else{

        // alert($("#usergroup option:selected").val())

        // $("#userrow").val($("#usergroup option:selected").text())
    
        $("#userrow").append(`<tr><td id=${$("#usergroup option:selected").val()}>${$("#usergroup option:selected").text()}</td> <td><button id="remove" class='btn btn-small-red remove'>-</button></td> </tr>`)
    
        selectedUserGroup.push($("#usergroup option:selected").val());
    

    }


   
     


    //load user group
    // var usrGroup=("#usergroup :selected").text();
    // if(users.includes(usrGroup)){
    //     alert("Already Selected !")
    // }
    // else{
    //     users.push(usrGroup);
    // }
    // content=''
    // users.forEach((user,index)=>{
    //         content+="<div id= class='row'>"+user+"<button class='remove' onclick='remove("+index+")'>-</button></div>";
    //     }
    // );
    // document.getElementById('userrow').innerHTML=content;


    // selectedUserGroup.push($("#usergroup").val());

});

// $("#usergroup").change(function () {
//     var selectedVal = $(this).val();

//     // alert(selectedVal)

// });


$('#send-btn').on('click', function(){

    var msg=$("#msg").val()

    $.post("http://localhost:8080/quickSalon_war_exploded/notifyUsers", {
        userTypes:selectedUserGroup,
        msg:msg
    },
    function (data, status) {
        alert(data);
        }
    );

});