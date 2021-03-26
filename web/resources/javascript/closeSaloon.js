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
  var fromDate = null;
  var toDate = null;

  $("#fromDate").change(function () {
    // $(this).val().getMonth();
    // fromDate = new Date($(this).val());
    console.log($(this).val());
    alert($(this).val());
  });

  $("#toDate").change(function () {
    toDate = $(this).val();
  });

  $("#send").click(function () {
    console.log(fromDate + " " + toDate);
    // $.ajax({
    //   type: "GET",
    //   url: "addLeave.html/confirm",
    //   data: { id: `${selectedVal}` },
    //   success: function (response) {
    //     console.log(response);
    //     if (response == 1) {
    //       alert("Leave added Successfully!");
    //       location.reload();
    //     } else if (response == 0) {
    //       alert(
    //         "Leave added Unsuccessfully!! please try again !"
    //       );
    //       location.reload();
    //     }
    //   },
    // });
  });
});
