/* Set the width of the side navigation to 250px and the left margin of the page content to 250px */
function openNav() {
  if (window.matchMedia("(max-width: 490px)").matches) {
    document.getElementById("mySidenav").style.width = "250px";
  } else {
    document.getElementById("mySidenav").style.width = "300px";
  }
}

/* Set the width of the side navigation to 0 and the left margin of the page content to 0 */
function closeNav() {
  document.getElementById("mySidenav").style.width = "0px";
  // document.getElementsByClassName("sideNav")[0].style.width= "0px";
}

function whenResizing() {
  if (window.matchMedia("(min-width: 1200px)").matches) {
    document.getElementById("mySidenav").style.width = "300px";
    // console.log("mona magulakda");
  } else {
    document.getElementById("mySidenav").style.width = "0px";
  }
}

function tgl() {
  $("a.subMenu").hide();
  $("a.empM").click(function () {
    $("a.subMenuEmp").toggle();
    $("a.subMenuService").hide();
    $("a.subMenuStk").hide();
  });

  $("a.servM").click(function () {
    $("a.subMenuService").toggle();
    $("a.subMenuEmp").hide();
    $("a.subMenuStk").hide();
  });

  $("a.stkM").click(function () {
    $("a.subMenuStk").toggle();
    $("a.subMenuEmp").hide();
    $("a.subMenuService").hide();
  });
}

$(document).ready(function () {
  $("#mySidenav").load(
    "http://localhost:8080/quickSalon_war_exploded/upperStaff/owner/sideNav.html",
    function () {
      tgl();
    }
  );
});
