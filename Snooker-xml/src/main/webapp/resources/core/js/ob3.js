$(document).ready(function() {
	$("#reset").on("click",function(){
		let url = "/thorben-dierkes/backend/resetObjectBrowserFilter";
		let token = $("#csrfToken").val();
		let ob3Id = $("#ob3id").val();
		let data = {"csrfToken": token, "ob3Id":ob3Id};
		$.ajax({
			type: "post",
		    url: url,
		    data: data
		}).done(function(data){
			console.log(data);
		});
	});
	
	$("#apply").on("click",function(){
		let url = "/thorben-dierkes/backend/applyObjectBrowserFilter";
		let token = $("#csrfToken").val();
		let ob3Id = $("#ob3id").val();
		let data = {"csrfToken": token, "ob3Id":ob3Id};
		$.ajax({
			type: "post",
		    url: url,
		    data: data
		}).done(function(data){
			console.log(data);
		});
	});
});