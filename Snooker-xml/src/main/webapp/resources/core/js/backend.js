$(".footer_preview").click(function(){
	$(".footer_preview").attr('href', "../");
});

$("#abmeldeButton").click(function(){
	window.location.href = '/thorben-dierkes/backend/login';
});

$("#wechselButton").click(function(){
	window.location.href = '/thorben-dierkes/';
});

$("#newObject").click(function(){
	 window.open("/thorben-dierkes/","popName",'scrollbars=1,height=650,width=1050').focus();
});
 