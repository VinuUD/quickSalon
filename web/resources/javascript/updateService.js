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
  console.log("remove clicked");
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

$("document").ready(function () {
  var urlString = window.location.href;
  var url = new URL(urlString);
  var serviceID = url.searchParams.get("id");
  //   alert(serviceID);

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

  $.ajax({
    type: "GET",
    url: "http://localhost:8080/quickSalon_war_exploded/serviceDetails",
    data: { serviceID: `${serviceID}` },
    success: function (response) {
      console.log(response);
      response.map(function (service) {
        $("#serviceID").val(service.serviceID);
        $("#serviceDescription").val(service.serviceDescription);
        $("#serviceTitle").val(service.serviceName);
        $("#timeDuration").val(service.timeTaken);
        $("#price").val(service.price);
        if (service.holdFlag == 1) {
          $("#holdFlag").val("yes");
        } else if (service.holdFlag == 0) {
          $("#holdFlag").val("no");
        }
      });
    },
  });

  $.ajax({
    type: "POST",
    url: "http://localhost:8080/quickSalon_war_exploded/serviceDetails",
    data: { spID: `${serviceID}` },
    success: function (response) {
      console.log(response);
      response.map(function (sp) {
        spIDs.push(sp.employeeId.toString());
        $("#assign-sp").append(
          `<tr id=${sp.employeeId}> <td>${
            sp.firstName + " " + sp.lastName
          }</td><td style="text-align: center;"><i onclick="remove(this)"><img class="cross"  src="../../resources/icons/cross.png" alt=""></i></td> <tr>`
        );
      });
    },
  });

  $("#save").on("click", function () {
    if ($("#serviceName").val() == "") {
      $("#serviceName").css("border", "4px red solid");
    }
    if ($("#serviceDescription").val() == "") {
      $("#serviceDescription").css("border", "4px red solid");
    } else if ($("#price").val() == "") {
      $("#price").css("border", "4px red solid");
    } else if ($("#timeTaken").val() == "") {
      $("#timeTaken").css("border", "4px red solid");
    } else {
      var serviceID = $("#serviceID").val();
      var serviceDescription = $("#serviceDescription").val();
      var serviceName = $("#serviceTitle").val();
      var timeTaken = $("#timeDuration").val();
      var price = $("#price").val();
      var holdFlag = 0;
      if ($("#holdFlag").val() == "no") {
        holdFlag = 0;
      } else if ($("#holdFlag").val() == "yes") {
        holdFlag = 1;
      }
      var newServiceDetails = {
        serviceID: serviceID,
        serviceDescription: serviceDescription,
        serviceName: serviceName,
        timeTaken: timeTaken,
        price: price,
        holdFlag: holdFlag,
        spIDs: spIDs,
      };

      console.log(spIDs);

      $.ajax({
        type: "GET",
        url: "http://localhost:8080/quickSalon_war_exploded/updateService",
        data: newServiceDetails,
        success: function (x) {
          console.log(x);
          if (x == 1) {
            alert("Service Updated!");
          } else {
            alert("Service Update Failed!");
          }
        },
      });
    }
  });

  $("#remove").on("click", function () {
    $.ajax({
      type: "POST",
      url: "http://localhost:8080/quickSalon_war_exploded/updateService",
      data: { serviceID: serviceID },
      success: function (x) {
        console.log(x);
        if (x == 1) {
          alert("Service Removed!");
        } else {
          alert("Service Remove Failed!");
        }

        window.location =
          "http://localhost:8080/quickSalon_war_exploded/upperStaff/allServices.html";
      },
    });
  });

  $("#cancel").on("click", function () {
    window.location =
      "http://localhost:8080/quickSalon_war_exploded/upperStaff/allServices.html";
  });

  $("#us_back").on("click", function () {
    window.location.href = "./allServices.html";
  });
});
