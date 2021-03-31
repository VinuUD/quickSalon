function logout() {
  $.get(
    "http://localhost:8080/quickSalon_war_exploded/logout",
    function (data) {
      window.location.href = "http://localhost:8080/quickSalon_war_exploded/";
    }
  );
}
