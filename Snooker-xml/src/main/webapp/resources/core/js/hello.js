	function fn_resize(){
		$('#content').height($(window).height() - $('#page-header').outerHeight() - $('#page-footer').outerHeight());
	}
	
	$(window).resize(function(){
		fn_resize()
	});