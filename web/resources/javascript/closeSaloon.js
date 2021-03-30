var i = 0;

function hello() {
  i = i + 1;
  return i;
}

function change() {
  if (i == 0) {
    return 0;
  } else {
    document.getElementById("mainPopupDiv").style.display = "block";
    i = 0;
  }
}

function hide() {
  document.getElementById("mainPopupDiv").style.display = "none";
}

function show() {
  document.getElementById("mainPopupDivCal").style.display = "block";
}

$("document").ready(function () {
  var fromDate = "";
  var toDate = "";
  var msg = "";

  $("#fromDate").on("change", function () {
    fromDate = $(this).val();
  });

  $("#toDate").on("change", function () {
    toDate = $(this).val();
  });

  $("#send").click(function () {
    if (
      $("#fromDate").val() == "" ||
      $("#toDate").val() == "" ||
      $("#msg").val() == ""
    ) {
      alert("Please fill all the fields!");
    } else {
      msg = $("#msg").val();
      if (confirm("confirm Dates?")) {
        $.ajax({
          type: "GET",
          url: "http://localhost:8080/quickSalon_war_exploded/closeSaloon",
          data: { fromDate: fromDate, toDate: toDate, msg: msg },
          success: function (response) {
            console.log(response);
            if (response == 1) {
              alert("Added Closing Dates !");
              location.reload();
            } else if (response == 0) {
              alert("please try again !");
              location.reload();
            }
          },
        });

        $.ajax({
          type: "POST",
          url: "http://localhost:8080/quickSalon_war_exploded/closeSaloon",
          data: { msg: msg },
          success: function (response) {
            console.log(response);
          },
        });
      }
    }
  });
});
