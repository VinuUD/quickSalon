const emailCheck = () => {
  let email = $("#email").val();
  if (!email.includes("@") && email.includes(" ")) {
    alert("missing @ symbol and there is a space in email field!");
    return false;
  } else if (!email.includes("@")) {
    alert("missing @ symbol in the email field!");
  } else if (email.includes(" ")) {
    alert("There is a space in the email field");
  } else {
    return true;
  }
};

const numLen = () => {
  let len = $("#cnum").val().length;
  if (len == 10) {
    return true;
  } else {
    return false;
  }
};

const stringCheck = () => {
  //string ekak apuwahama methana return wenne false;

  let contactNumber = $("#cnum").val();
  let numArr = contactNumber.split("");

  for (var i = 0; i < numArr.length; i++) {
    if (!parseInt(numArr[i]) && numArr[i] != "0") {
      return false;
    }
  }

  return true;
};

const numberCheck = () => {
  if (!stringCheck() && !numLen()) {
    alert(
      "There are/is string/s in the contact number field ! please enter 10 digit number to the contact number field!"
    );
  } else if (!stringCheck()) {
    alert("There are/is string/s in the contact number field !");
  } else if (!numLen()) {
    alert("Please enter 10 digit number to the contact number field!");
  } else {
    return true;
  }
};

$("document").ready(function () {
  $("#save").click(function () {
    var fname = $("#fname").val();
    var lname = $("#lname").val();
    var uname = $("#uname").val();
    var cnum = $("#cnum").val();
    var nic = $("#nic").val();
    var email = $("#email").val();
    var address = $("#address").val();

    var details = {
      fname: fname,
      lname: lname,
      uname: uname,
      cnum: cnum,
      nic: nic,
      email: email,
      address: address,
    };

    if (
      fname == "" &&
      lname == "" &&
      uname == "" &&
      cnum == "" &&
      nic == "" &&
      email == "" &&
      address == ""
    ) {
      alert("please fill all the fields!");
    } else {
      if (emailCheck() && numberCheck()) {
        if (confirm("Do you want to update profile?")) {
          $.ajax({
            type: "POST",
            url: "http://localhost:8080/quickSalon_war_exploded/updateCustomer",
            data: details,
            success: function (response) {
              console.log(response);
              if (response == 1) {
                alert("Details Updated!");
                location.reload();
              } else {
                alert("Unsuccess! please try again!");
                location.reload();
              }
            },
          });
        }
      }
    }
  });
});
