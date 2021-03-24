var spIDs = [];
serviceID = 0;

function remove(x) {
  var i = x.parentNode.parentNode.rowIndex;
  var id = x.parentNode.parentNode.id;
  document.getElementById("table34").deleteRow(i);

  // Delete x from spIDs array
  var index = spIDs.indexOf(id);
  if (index !== -1) {
    spIDs.splice(index, 1);
  }
}
function add() {
  var empID = $("#select-sp").val();
  if (!spIDs.includes(empID)) {
    spIDs.push(empID);
    $("#assign-sp").append(
      `<tr id=${empID}> <td>${$(
        "#select-sp option:selected"
      ).text()}</td><td style="text-align: center;"><i onclick="remove(this)"><img class="cross"  src="../../resources/icons/cross.png" alt=""></i></td> <tr>`
    );
  }
}

$(document).ready(function () {
  // Get all SPs
  $.post(
    "http://localhost:8080/quickSalon_war_exploded/searchSP",
    { name: "" },
    function (data, status) {
      $.each(data, function (index, emp) {
        $("#select-sp").append(
          `<option value="${emp.employeeId}"> ${
            emp.firstName + " " + emp.lastName
          } </option>`
        );
      });
    }
  );

  $("#add-btn").on("click", function () {
    if ($("#serviceName").val() == "") {
      $("#serviceName").css("border", "4px red solid");
    }
    if ($("#description").val() == "") {
      $("#description").css("border", "4px red solid");
    } else if ($("#price").val() == "") {
      $("#price").css("border", "4px red solid");
    } else if ($("#timeTaken").val() == "") {
      $("#timeTaken").css("border", "4px red solid");
    } else {
      //1) add to service table--return serviceID
      $.post(
        "http://localhost:8080/quickSalon_war_exploded/registerservice",
        {
          sname: $("#serviceName").val(),
          desc: $("#description").val(),
          timeTaken: $("#timeTaken").val(),
          price: $("#price").val(),
          spIDs: spIDs,
        },
        function (data, status) {
          if (data == 1) {
            alert("Successfully Added !");
          } else {
            alert("Added failed !");
          }
        }
      );
    }
  });
});
