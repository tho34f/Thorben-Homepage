$(window).scroll(function() {
    if ($(this).scrollTop() > 1){  
        $('header').addClass("sticky");
    }
    else{
        $('header').removeClass("sticky");
    }
});

function fn_showNavDropdown(navElement = 1) {
	$("#navElement" + navElement).addClass("show");
	$("#navElement" + navElement).parent().addClass("show");
	
}

function fn_hideNavDropdown(navElement = 1) {
	$("#navElement" + navElement).removeClass("show");
	$("#navElement" + navElement).parent().removeClass("show");
}