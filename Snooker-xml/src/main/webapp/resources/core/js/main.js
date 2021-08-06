$(window).scroll(function() {
    if ($(this).scrollTop() > 1){  
        $('header').addClass("sticky");
        $('#headerImage').addClass("headerImageSticky");
        $('#headerImage').removeClass("headerImage");
    }
    else{
        $('header').removeClass("sticky");
        $('#headerImage').removeClass("headerImageSticky");
        $('#headerImage').addClass("headerImage");
    }
});

function startTime() {
    var today = new Date();
    var h = today.getHours();
    var m = today.getMinutes();
    var s = today.getSeconds();
    m = checkTime(m);
    s = checkTime(s);
    document.getElementById('clock').innerHTML = h + ":" + m + ":" + s;
    setTimeout(startTime,500);
}   
 
 
 
function checkTime(i) {
  if (i < 10) {i = "0" + i}; 
  return i;
}