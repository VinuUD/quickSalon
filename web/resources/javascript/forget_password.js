(function($) {
    "use strict";
    $("#changePassword").hide();
    $("#verification").hide();
    $(".center").hide();
    $('#spinner-center').hide();

  //Enter email component
    $('#submitEmail').on('click', function(){

      var input = $('#email');
      var email = input.val().trim();
      var check=true;
      //loading spinner is enabled
      
      if(validate(input) == false){
          showValidate(input);
          check = false;
      }
      if(check){
        // Start spinner animation
        $('#spinner-center').show();
       ///CAll ajax request
          $.get("http://localhost:8080/quickSalon_war_exploded/forgetpassword", {email:email},
              function(data, status){
                if(data==0){
                    //User email not valid
                    showError(input);
                }else {
                    //email is valid
                    $( "#reset_password" ).slideUp( "Fast", function() {
                        $( "#verification" ).slideDown( "Fast", function() {
                            $("#verification").show();
                        });
                    });
                }
                $('#spinner-center').hide();
              }
          );
      }
    });

    //Enter pincode component
    $('#submitPin').on('click', function(){
      var input = $('#pin');
      var check=true;
      var pincode=input.val().trim();

      if(validate(input) == false){
          showValidate(input);
          check = false;
      }
      if(check){
          $.post("forgetpassword", {
                pin:pincode,
              },
              function(data, status){
                  if(data==0){
                      //pin is not valid
                      showError(input);
                  }else {
                      //pin is valid
                      $( "#verification" ).slideUp( "Fast", function() {
                          $( "#changePassword" ).slideDown( "Fast", function() {
                              $("#changePassword").show();
                          });
                      });
                  }
              }
          );
      }
    });

    // Reset password component
    $('#resetPassword').on('click', function(){
      var input = $('#password');
      var input2 = $('#repassword');
      var check=true;
      var password=input.val().trim();

      // Check pwd is empty or not
      if($(input).val().trim() == ''){
        showValidate(input);
      }
      if(input.val() === input2.val()){
        // alert("Reset success! ")
          //send reset password request
          $.post("resetpassword", {
                  password:password,
              },
              function(data, status){
                  if(data==0){
                      //Password reset failed
                      alert("Password reset failed ");
                  }else {
                      //Password reset success
                      $(".limiter").css({"filter":"blur(8px)","-webkit-filter": "blur(8px)" });
                      $(".center").css("display","block" );
                  }
              }
          );
      }
      else{
        // Password,Re not match
        showValidate(input2);
      }
    });

    $('.validate-form .input34').each(function(){
      $(this).focus(function(){
        hideValidate(this);
        hideDataValidate(this);
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

    function showError (input) {
        var thisAlert = $(input).parent();
        $(thisAlert).addClass('data-validate');
    }
    function hideDataValidate (input) {
        var thisAlert = $(input).parent();
        $(thisAlert).removeClass('data-validate');
    }

    function hideValidate (input) {
      var thisAlert = $(input).parent();
      $(thisAlert).removeClass('alert-validate');
    }

  })(jQuery);
  
  
  