(function($) {
    "use strict";
    $("#changePassword").hide();
    $("#verification").hide();
    $(".center").hide();
  // <!-- Enter email component -->
    $('#submitEmail').on('click', function(){
      var input = $('#email');
      var check=true;
      for(var i = 0; i < input.length; i++){
        if(validate(input[i]) == false){
          showValidate(input[i]);
          check = false;
        }
      }
      if(check){
        //login(); ///CAll ajax request
        $( "#reset_password" ).slideUp( "Fast", function() {
            $( "#verification" ).slideDown( "Fast", function() {
                $("#verification").show();
              });
        });
      }
    });

    // <!-- Enter pincode component -->
    $('#submitPin').on('click', function(){
      var input = $('#pin');
      var check=true;
      for(var i = 0; i < input.length; i++){
        if(validate(input[i]) == false){
          showValidate(input[i]);
          check = false;
        }
      }
      if(check){
        //login(); ///CAll ajax request
        $( "#verification" ).slideUp( "Fast", function() {
            $( "#changePassword" ).slideDown( "Fast", function() {
                $("#changePassword").show();
              });
          });
      }
    });

    //<!-- Reset password component -->
    $('#resetPassword').on('click', function(){
      var input = $('#password');
      var input2 = $('#repassword');
      var check=true;

      // Check pwd is empty or not
      if($(input).val().trim() == ''){
        showValidate(input);
      }
      if(input.val() === input2.val()){
        // alert("Reset success! ")  
        $(".limiter").css({"filter":"blur(8px)","-webkit-filter": "blur(8px)" });
        $(".center").css("display","block" );

      }
      else{
        // Password,Re not match
        showValidate(input2);
        
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

   
   

    

  })(jQuery);
  
  
  