$(window).scroll(function(){
  var s = $(window).scrollTop();
  opacityVal = (s / 250.0);

  $('.login').css('opacity', 1 - opacityVal);
  $('.title').css('opacity', 1 - opacityVal);
  $('.text').css('opacity', 1 - opacityVal);
});

// salon-schedule

$(document).ready(function () {

  $("#salon-schedule").on("click", function () {
    window.location.href = 'http://localhost:8080/quickSalon_war_exploded/salon_schedule.html'
  })

})
