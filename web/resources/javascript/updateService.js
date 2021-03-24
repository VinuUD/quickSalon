// function add() {
//   // dynamically create table row with dropdown

//   // first column dropdown
//   var tr = document.createElement("tr");
//   tr.setAttribute("class", "tr");

//   var td = document.createElement("td");
//   td.style.fontSize = "16px";

//   var select = document.createElement("select");
//   select.setAttribute("class", "blo"); //should be there
//   select.setAttribute("id", "sel");
//   select.onchange = function () {
//     td.innerHTML = document.getElementById("sel").value;
//   };

//   var option = document.createElement("option");
//   option.innerHTML = "Haircut";

//   var option1 = document.createElement("option");
//   option1.innerHTML = "Tattoo";

//   var optionNew = document.createElement("option");
//   optionNew.innerHTML = "Service";

//   td.appendChild(select);
//   select.appendChild(optionNew);
//   select.appendChild(option);
//   select.appendChild(option1);
//   // tr.appendChild(td);

//   // add remove button ///////////////////////////////////////////////////////

//   var tdRemove = document.createElement("td");
//   tdRemove.style.textAlign = "center";
//   var icon = document.createElement("i");

//   icon.onclick = function () {
//     var x = icon.parentNode.parentNode.rowIndex;
//     document.getElementById("table34").deleteRow(x); //function for delete row which makes dynamically
//     console.log(x);
//   };

//   var image = document.createElement("img");
//   image.src = "../../resources/icons/cross.png";
//   image.style.width = "20px";
//   image.style.height = "20px";
//   image.style.marginRight = "10px";
//   image.style.cursor = "pointer";

//   icon.appendChild(image);
//   tdRemove.appendChild(icon);

//   /////////////////////////////////////////////////////////////////////////////

//   // second column dropdown
//   var td1 = document.createElement("td");
//   td1.style.fontSize = "16px";

//   var select1 = document.createElement("select");
//   select1.setAttribute("class", "blo"); //should be there
//   select1.setAttribute("id", "sel1");

//   var optionNew1 = document.createElement("option");
//   optionNew1.innerHTML = "S P";

//   var option2 = document.createElement("option");
//   option2.innerHTML = "Nimal";

//   var option12 = document.createElement("option");
//   option12.innerHTML = "Kaanthi";

//   td1.appendChild(select1);

//   select1.appendChild(optionNew1);
//   select1.appendChild(option2);
//   select1.appendChild(option12);
//   tr.appendChild(td1);
//   tr.appendChild(tdRemove);

//   document.getElementById("add").appendChild(tr); //append table to tr tag

//   // create row with option tag values

//   document.getElementById("sel1").addEventListener("change", function () {
//     td1.innerHTML = document.getElementById("sel1").value;
//   });
// }

// function showRemove() {
//   document.getElementById("removePopup").style.display = "block";
// }

// function hideRemove() {
//   document.getElementById("removePopup").style.display = "none";
// }

// function remove(x) {
//   var i = x.parentNode.parentNode.rowIndex;
//   document.getElementById("table34").deleteRow(i);
// }

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

      //1) add to service table--return serviceID
      //   $.post(
      //     "http://localhost:8080/quickSalon_war_exploded/registerservice",
      //     {
      //       sname: $("#serviceName").val(),
      //       desc: $("#description").val(),
      //       timeTaken: $("#timeTaken").val(),
      //       price: $("#price").val(),
      //       spIDs: spIDs,
      //     },
      //     function (data, status) {
      //       if (data == "true") {
      //         alert("Successfully Added !");
      //       } else {
      //         alert("Added failed !");
      //       }
      //     }
      //   );
    }
  });
});
