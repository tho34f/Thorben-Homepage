$( document ).ready(function() {
    fn_resizeHeader();
});

$(window).on('resize', function() {
    $(".pageHeader .headerContent").width($(this).width() - 30);
});

function fn_resizeHeader() {
    $(".pageHeader .headerContent").width($(window).width() -30);
}

function fn_showNavDropdown(navElement = 1) {
	$("#navElement" + navElement).addClass("show");
	$("#navElement" + navElement).parent().addClass("show");
}

function fn_hideNavDropdown(navElement = 1) {
	$("#navElement" + navElement).removeClass("show");
	$("#navElement" + navElement).parent().removeClass("show");
}