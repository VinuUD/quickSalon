$(window).scroll(function(){
  var s = $(window).scrollTop();
  opacityVal = (s / 250.0);

  $('.login').css('opacity', 1 - opacityVal);
  $('.title').css('opacity', 1 - opacityVal);
  $('.text').css('opacity', 1 - opacityVal);
});