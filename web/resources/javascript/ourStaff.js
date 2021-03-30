$("document").ready(function () {
  console.log("ksdjfhsd");
  $.get("ourStaff.html/ourStaff", function (response) {
    response.map(function (data) {
      if (data.upperStaffFlag == 5) {
        // card component
        var div_card = document.createElement("div");
        div_card.setAttribute("class", "card");

        var div_card_image = document.createElement("div");
        div_card_image.setAttribute("class", "card_image_container");

        var image = document.createElement("img");
        image.setAttribute("src", "resources/images/avatar.png");
        image.setAttribute("class", "card_image");

        var div_card_details = document.createElement("div");
        div_card_details.setAttribute("class", "card_details");

        var h4 = document.createElement("h4");
        var b = document.createElement("b");
        var p = document.createElement("p");

        b.innerHTML = data.firstName + " " + data.lastName;
        p.innerHTML = "Owner of quickSalon";

        h4.appendChild(b);
        div_card_details.appendChild(h4);
        div_card_details.appendChild(p);

        div_card_image.appendChild(image);

        div_card.appendChild(div_card_image);
        div_card.appendChild(div_card_details);

        document.getElementById("owner_card_container").appendChild(div_card);
      } else if (data.upperStaffFlag == 1) {
        // card component
        var div_card = document.createElement("div");
        div_card.setAttribute("class", "card");

        var div_card_image = document.createElement("div");
        div_card_image.setAttribute("class", "card_image_container");

        var image = document.createElement("img");
        image.setAttribute("src", "resources/images/avatar.png");
        image.setAttribute("class", "card_image");

        var div_card_details = document.createElement("div");
        div_card_details.setAttribute("class", "card_details");

        var h4 = document.createElement("h4");
        var b = document.createElement("b");
        var p = document.createElement("p");

        b.innerHTML = data.firstName + " " + data.lastName;
        p.innerHTML = "Manager";

        h4.appendChild(b);
        div_card_details.appendChild(h4);
        div_card_details.appendChild(p);

        div_card_image.appendChild(image);

        div_card.appendChild(div_card_image);
        div_card.appendChild(div_card_details);

        document
          .getElementById("manager_cards_container")
          .appendChild(div_card);
      } else {
        // card component
        var div_card = document.createElement("div");
        div_card.setAttribute("class", "card");

        var div_card_image = document.createElement("div");
        div_card_image.setAttribute("class", "card_image_container");

        var image = document.createElement("img");
        image.setAttribute("src", "resources/images/avatar.png");
        image.setAttribute("class", "card_image");

        var div_card_details = document.createElement("div");
        div_card_details.setAttribute("class", "card_details");

        var h4 = document.createElement("h4");
        var b = document.createElement("b");
        var p = document.createElement("p");

        b.innerHTML = data.firstName + " " + data.lastName;
        p.innerHTML = "Employee";

        h4.appendChild(b);
        div_card_details.appendChild(h4);
        div_card_details.appendChild(p);

        div_card_image.appendChild(image);

        div_card.appendChild(div_card_image);
        div_card.appendChild(div_card_details);

        document
          .getElementById("employee_cards_container")
          .appendChild(div_card);
      }
    });
  });
});
