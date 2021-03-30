$("document").ready(function () {
  var ratingsDetails = [];
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/quickSalon_war_exploded/updateRatings",
    async: false,
    success: function (response) {
      ratingsDetails = response;
    },
  });

  $.get(
    "http://localhost:8080/quickSalon_war_exploded/viewSp",
    function (response) {
      console.log(response);
      response.map(function (x) {
        $("#tbl").append(
          "<tr>" +
            "<td>" +
            x.empId +
            "</td>" +
            "<td>" +
            x.firstName +
            " " +
            x.lastName +
            "</td>" +
            "<td>" +
            x.contactNum +
            "</td>" +
            "<td>" +
            x.nic +
            "</td>" +
            "<td style='display:none'>" +
            x.salary +
            "</td>" +
            "<td style='display:none'>" +
            x.address +
            "</td>" +
            "<td>" +
            "<button title='view employee details' class = 'btn34' onclick = 'show(event)'>" +
            "<i class='fa fa-eye'>" +
            "</i>" +
            "</button>" +
            "</td>" +
            "<td>" +
            "<icon id='plusIcon' class='plusIcon fa fa-fw fa-plus-square'>" +
            "</icon>" +
            "<icon id='minusIcon' class='minusIcon fa fa-fw fa-minus-square'>" +
            "</icon>" +
            "</td>" +
            "<td class='ratingtd'>" +
            "</td>" +
            "</tr>"
        );
        // console.log(x.email);
        // console.log(x.nic);
      });

      var max = 10;
      var def = 10;
      var min = 0;

      $(".ratingtd").html(def);
      var i = 0;
      $(".ratingtd").map(function () {
        this.innerHTML = ratingsDetails[i].ratings;
        i = i + 1;
      });

      //when clicked plus icon
      $(".plusIcon").on("click", function () {
        var currentRow = $(this).closest("tr");

        var x = parseFloat(currentRow.find("td:eq(8)").text()) + 1;
        var empID = parseInt(currentRow.find("td:eq(0)").text());

        currentRow.find("td:eq(8)").html(x);
        if (parseInt(currentRow.find("td:eq(8)").text()) > 19) {
          currentRow.find("td:eq(8)").html("20");
          console.log("blaaaa");
        }

        console.log(currentRow.find("td:eq(0)").text());

        $.ajax({
          type: "POST",
          url: "http://localhost:8080/quickSalon_war_exploded/updateRatings",
          data: {
            empID: empID,
            rateVal: parseFloat(currentRow.find("td:eq(8)").text()),
          },
          success: function (response) {
            if (response == 1) {
              console.log("success");
            }
          },
        });
      });
      //when clicked minus icon
      $(".minusIcon").on("click", function () {
        var currentRow = $(this).closest("tr");

        var y = parseFloat(currentRow.find("td:eq(8)").text()) - 1;
        var empID = parseInt(currentRow.find("td:eq(0)").text());
        // def = def + 0.5;
        currentRow.find("td:eq(8)").html(y);
        if (parseInt(currentRow.find("td:eq(8)").text()) < 1) {
          currentRow.find("td:eq(8)").html("0");
          console.log("blooo");
        }

        console.log(currentRow.find("td:eq(0)").text());

        $.ajax({
          type: "POST",
          url: "http://localhost:8080/quickSalon_war_exploded/updateRatings",
          data: {
            empID: empID,
            rateVal: parseFloat(currentRow.find("td:eq(8)").text()),
          },
          success: function (response) {
            if (response == 1) {
              console.log("success");
            }
          },
        });
      });

      $("#myTable").on("click", ".btn34", function () {
        var currentRow = $(this).closest("tr");

        var col1 = currentRow.find("td:eq(0)").text(); //emp
        var col2 = currentRow.find("td:eq(1)").text(); //fname  //lname
        var col3 = currentRow.find("td:eq(2)").text(); //c num
        var col4 = currentRow.find("td:eq(3)").text(); //nic
        var col5 = currentRow.find("td:eq(4)").text(); //sal
        var col6 = currentRow.find("td:eq(5)").text(); //address
        // var col7 = currentRow.find("td:eq(6)").text();
        // var data = col1 + "\n" + col2 + "\n" + col3 + "\n" + col4;
        $("#empHeader").html(col2);
        $("#updateHeader").html("Update " + col2);

        var inputFields = document
          .getElementById("inDiv")
          .getElementsByTagName("input");
        inputFields[0].value = col1;
        inputFields[1].value = col2;
        inputFields[2].value = col3;
        inputFields[3].value = col5;
        inputFields[4].value = col4;
        inputFields[5].value = col6;

        $("#eID").val(col1);
        $("#name").val(col2);
        $("#cNum").val(col3);
        $("#salary").val(col5);
        $("#nic").val(col4);

        $("#address").val(col6);
        //  console.log(inputFields);

        //////////////////////////////////////////////////
        $("#updateManager").click(function () {
          var eID = $("#eID").val();
          var cNum = $("#cNum").val();
          var salary = $("#salary").val();
          var address = $("#address").val();

          var dataObj = {
            eID: `${eID}`,
            cNum: `${cNum}`,
            salary: `${salary}`,
            address: `${address}`,
          };

          $.ajax({
            type: "GET",
            url: "http://localhost:8080/quickSalon_war_exploded/updateSp",
            data: dataObj,
            success: function (response) {
              console.log(response);
              if (response == 1) {
                alert("Service Provider Update Successfully!");
                location.reload();
              } else if (response == 0) {
                alert(
                  "Service Provider update Unsuccessfully!! please try again !"
                );
                location.reload();
              }
            },
          });

          // document.getElementById("myForm").reset();
        });
        // //////////////////////////

        // //////////////////////
        $("#removeManager").click(function () {
          var password = $("#password").val();
          console.log(password);
          console.log(col1);
          $.ajax({
            type: "POST",
            url: "http://localhost:8080/quickSalon_war_exploded/removeSP",
            data: { id: `${col1}`, password: `${password}` },
            success: function (response) {
              console.log(response);
              if (response == 1) {
                alert("Service Provider Removed Successfully!");
                location.reload();
              } else if (response == 0) {
                alert(
                  "Service Provider Remove Unsuccessfully!! please try again !"
                );
                location.reload();
              } else if (response == 4) {
                alert("Password does not match! Try again!");
                location.reload();
              }
            },
          });

          // document.getElementById("myForm").reset();
        });
        // //////////////////////////
      });
    }
  );
});
