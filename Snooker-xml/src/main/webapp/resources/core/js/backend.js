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
	var id = $("#newObject").attr("name");
	
	if(id == 38){
		 window.open("/thorben-dierkes/backend/userwizard","popName",'scrollbars=1,height=650,width=1050').focus();
	 }
	 
	 if(id == 39){
		 window.open("/thorben-dierkes/backend/newswizard","popName",'scrollbars=1,height=650,width=1050').focus();
	 }
	 
	 if(id == 40){
		 window.open("/thorben-dierkes/backend/terminewizard","popName",'scrollbars=1,height=650,width=1050').focus();
	 }
});

function openWizard(id, objectType){
	if(objectType == 38){
		 window.open("/thorben-dierkes/backend/userwizard?id=" + id,"popName",'scrollbars=1,height=650,width=1050').focus();
	}
	 
	if(objectType == 39){
		 window.open("/thorben-dierkes/backend/newswizard?id=" + id,"popName",'scrollbars=1,height=650,width=1050').focus();
	 }
	 
	 if(objectType == 40){
		 window.open("/thorben-dierkes/backend/terminewizard?id=" + id,"popName",'scrollbars=1,height=650,width=1050').focus();
	 }
}
 