/* Set the width of the side navigation to 250px and the left margin of the page content to 250px */
function openNav() {
  document.getElementById("mySidenav").style.width = "250px";
  
}

/* Set the width of the side navigation to 0 and the left margin of the page content to 0 */
function closeNav() {
  document.getElementById("mySidenav").style.width = "0px";
  // document.getElementsByClassName("sideNav")[0].style.width= "0px";

  
}

function whenResizing()
{
  if(window.matchMedia("(min-width: 1200px)").matches)
  {
    document.getElementById("mySidenav").style.width = "250px";
    console.log("mona magulakda");
  }
  else
  {
    document.getElementById("mySidenav").style.width = "0px";
  }
}

