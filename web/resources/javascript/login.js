(function($) {
  "use strict";
  var input = $('.validate-input .input34');

  $('#submit').on('click', function(){
    var check=true;
    for(var i = 0; i < input.length; i++){
      if(validate(input[i]) == false){
        showValidate(input[i]);
        check = false;
      }
    }
    if(check){+
      login();
    }

  });

  $('.validate-form .input34').each(function(){
    $(this).focus(function(){
      hideValidate(this);
    })
  });

  function validate (input) {
    if($(input).attr('type') == 'email' || $(input).attr('name') == 'email') {
      if($(input).val().trim().match(/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\]?)$/) == null) {
        return false;
      }
    }
    else {
      if($(input).val().trim() == ''){
          return false;
      }
    }
  }

  function showValidate (input) {
    var thisAlert = $(input).parent();
    $(thisAlert).addClass('alert-validate');
  }

  function hideValidate (input) {
    var thisAlert = $(input).parent();

    $(thisAlert).removeClass('alert-validate');
  }

function login(){
    var email = $("#email").val().trim();
    var password = $("#password").val().trim();
    $.post("login", {
          email:email,
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
            window.location.href='../../quickSalon_war_exploded/customer/home.html';
          }
          else {
            alert(data);
          }
        }
    );
  }



})(jQuery);


