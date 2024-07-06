$(document).ready(function() {
	
	let url = "/thorben-dierkes/backend/ob3/" + $("#ob3id").val();
	$('.loaderWrapper').show();
	$('.loaderTableWrapper').show();
	$('.objectBrowserDataTable').hide();
	$.ajax({
		type: "get",
	    url: url
	}).done(function(data){
		fn_renderOb3Filter(data.filter, $("#ob3id").val());
		fn_renderOb3Title(data.title);
		fn_renderOb3Body(data.objectList, $("#ob3id").val());
		$('.loaderWrapper').hide();
		$('.loaderTableWrapper').hide();
		$('.objectBrowserDataTable').show();
	});
	
	
	$("#reset").on("click",function(){
		let url = "/thorben-dierkes/backend/ob3/resetObjectBrowserFilter";
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
		let url = "/thorben-dierkes/backend/ob3/applyObjectBrowserFilter";
		let token = $("#csrfToken").val();
		let ob3Id = $("#ob3id").val();
		let data = {"csrfToken": token, "ob3Id":ob3Id};
		$(".ob3FilterElement").children(".textInput").each(function(){
			data[$(this).attr('id')] = $(this).val();
		});
		$.ajax({
			type: "post",
		    url: url,
		    data: data
		}).done(function(data){
			console.log(data);
		});
	});
});

function fn_renderOb3Filter(filters, objectType){
	let objectBrowserFilterData = $('#objectBrowserFilterData');
	filters.forEach((filter) => {
		let div = document.createElement("div");
		div.classList.add("ob3FilterElement");
		
		let span = document.createElement("span");
		span.appendChild(document.createTextNode(filter.description));
		div.appendChild(span);
		
		let input = document.createElement("input");
		input.setAttribute('id', objectType + "_" + filter.name);
		input.setAttribute('name', objectType + "_" + filter.name);
		input.setAttribute('type', 'text');
		input.setAttribute('placeholder', filter.description);
		input.setAttribute('min', '1');
		input.setAttribute('max', '255');
		input.setAttribute('maxlength', '255');
		input.classList.add("textInput");
		div.appendChild(input);
		
		objectBrowserFilterData.append(div);
	});
	
};

function fn_renderOb3Title(titles){
	let obTableHeader = $('.obTableHeader');
	titles.forEach((title) => {
		let th = document.createElement("th");
		th.classList.add("redThorben", "ob_title_datacolumn_on");
		th.setAttribute('id', "obObject_" + title.name);
		th.appendChild(document.createTextNode(title.description));
		obTableHeader.append(th);
	});
};

function fn_renderOb3Body(objectList, objectType){
	objectList.forEach((object) => {
		console.log(object);
	});
};