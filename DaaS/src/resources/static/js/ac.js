// General utility functions
function prepareTabs(jquery){
	$('[data-toggle="tabajax"]')
	.click(
			function(e) {
				var $this = $(this);
				loadurl = $this
						.attr('href'), targ = $this
						.attr('data-target');
confirm(targ);
				$.get(loadurl, function(data) {
					$(targ).html(data);
				});
				try{
					$this.tab('show');
				} catch (err){
					confirm(err);
				}
				confirm("here");
				return false;
			});	
}

function multiselect(jSelector){
	$(jSelector).multiselect({
		buttonWidth : '100%'
	});	
}

// Provide class of forms and clicked jquery object for radio button
function showFormPerRadioChoice(formClass, radioObj){
	var inputValue = radioObj.attr("value");
	confirm(inputValue);
	var targetBox = $("." + inputValue);
	confirm($(targetBox));
	$(formClass).not(targetBox).hide();
	$(targetBox).show();
}

function hideDiv(jSelector){
	confirm("hiding");
	$(jSelector).hide();
}

function emptyDiv(jSelector){
	$(jSelector).html("");
}

function enableEdit(){
	$(".save-btn").removeClass("disabled");
	$(".edit-btn").addClass("disabled");	
	$(".saveDiv").show();
	$(".editDiv").hide();
}

function enableSave(){
	$(".save-btn").addClass("disabled");
	$(".edit-btn").removeClass("disabled");	
	$(".saveDiv").hide();
	$(".editDiv").show();
}

function getLink(source_element, event, s_callback, e_callback){
	get(source_element.attr('href'), source_element, event, s_callback, e_callback);
}

function get(url, source_element, event, s_callback, e_callback){
	__sendRequest(url, source_element, event, "GET", "", s_callback, e_callback);
}

function stringifyForm(jqueryForm){
	var map = {};
	jqueryForm.serializeArray().map(function(x){map[x.name] = x.value;});
	return JSON.stringify(map);
}

function postForm(source_element, event, s_callback, e_callback){
	//var req_data = source_element.closest("form").serializeArray();
	//confirm(stringifyForm(source_element.closest("form")));
	var req_data = stringifyForm(source_element.closest("form")).toString();
	var url = source_element.closest("form").attr('action');
	__sendRequest(url, source_element, event, "POST", req_data, s_callback, e_callback);
}

function postFormWithButtonLink(source_element, action, event, s_callback, e_callback){
	//var req_data = source_element.closest("form").serializeArray();
	//confirm(stringifyForm(source_element.closest("form")));
	//confirm(source_element.closest("form"));
	var req_data = stringifyForm(source_element.closest("form")).toString();
	var url = action;
	__sendRequest(url, source_element, event, "POST", req_data, s_callback, e_callback);
}

function __sendRequest(url, source_element, event, req_type, req_data, s_callback, e_callback) {
	event.preventDefault();	
	if(event.handled !== true)
	  {		
		//alert($("#formConvertASCII").attr('action'));
		$.ajax({
			type: req_type,
			url: url,
			cache: false,
			data: req_data,
			success: function(jdata){
				s_callback(jdata, source_element)
			},
			error: function(jqXHR,error, errorThrown){
				e_callback(jqXHR.responseText, error);	
			}
		}).responseText;
		event.handled = true;
	}
	return false;	
}

function sendDirectGetRequest(url, s_callback, e_callback) {
	confirm("dg");
	$.ajax({
		type: "GET",
		url: url,
		cache: false,
		success: function(jdata){
			s_callback(jdata)
		},
		error: function(jqXHR,error, errorThrown){
			e_callback(jqXHR.responseText, error);	
		}
	}).responseText;	
	return false;
}

function displayError(jdata,error){
	$("#errorContent").html("");
	$("#errorContent").html(jdata);	
}

function appendToProject(jdata, source_id) {
	$("#projContent").html("");
	$("#projContent").html(jdata);
}


function appendToConfContent(jdata, source_id) {
	$("#confContent").html("");
	$("#confContent").html(jdata);
}

function pvtResultsClear(jdata){
	if (jdata.RESULT == "ERROR"){
		$("#results").html("");
		$("#results").html(jdata.CONTENT);
		return -1;
	}
	else{
		return 0;
	}
}

function pvtAddDebugItem(item){	
	$("#results").html(item + "<p>");	
}

function pvtResultsDone(){
	$("#results").html("");		
	$("#results").html("Done.<p>");	
}

function clearTable( tableID, headerString){
	$(tableID).html("");
	$(tableID).html(headerString);
}

function insertCells(rowElement, count){
	var arrCells = [];
	for (var i=0; i < count; i++){
		var cell = rowElement.insertCell(i);
		arrCells.push(cell);
	}
	return arrCells;
}

function addCellContents(arrCells, cellContentMap){
	var tStamp = new Date().getTime().toString();
	for (var i = 0; i < cellContentMap.length; i++) {
		if (cellContentMap[i][0] != "link"){
			arrCells[i].innerHTML = "<div id='" + cellContentMap[i][0] + tStamp + "'> " +  cellContentMap[i][1] + "</div>";
		}
		else{
			arrCells[i].innerHTML = "<a id='" + cellContentMap[i][1]["id_prefix"] + tStamp + "' href='" +  cellContentMap[i][1]["href"] + "' onclick='handleLink(event)'>" + cellContentMap[i][1]["display_name"] +"</a>";
		}
	}
}