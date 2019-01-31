handlers = {	
}

function add_handlers(handler_map){
	jQuery.extend(handlers, handler_map);
}

function getEvent(event){
	return event || arguments[0]; 
}

function getTarget(event){
	return event.target || event.srcElement;
}

function markActiveLink(link_querystr){
	$("#sidebar-wrapper").find(".active").removeClass("active");
	$(link_querystr).addClass("active");
	$(link_querystr).parent().addClass("active");
	/*var parent = $('#' + $(link_querystr).parent().parent().data('parent'));
	parent.addClass("active");
	if (parent.hasClass("collapsed")){
		parent.click();
	}
	*/
}

function match(target, pattern){
	if (target !== null){
		return target.match(pattern);
	} else {
		return false;
	}
}

function validateForm(submitButton){
	var issues = "";
	var f = submitButton.closest("form");
	f.find(":input").each(function(){
		var reg = $(this).data('regex');
		if (typeof reg != 'undefined'){
			var m = match($(this).val(), reg);
			if (!m){
				issues += "Error in " + $(this).data("r-name") + ". ";
				issues += "Rules: <br/>";
				issues += $(this).data("rules");
			}
		}
	});
	
	return issues;
}

// Main Template

function setSectionContent(jdata) {
	confirm("ddf");
	$("#sectionContent").html("");
	$("#sectionContent").html(jdata);
}

function handleNavLink(event, mark_parent) {
	$(".projMenuWrapper").hide();
	var e = getEvent(event);
	var target = getTarget(e);
	var sectionTitle = $("#" + target.id).data('title');
	confirm("jjjjj");
	confirm(sectionTitle);
	if (mark_parent) {
		markActiveLink("#" + target.getAttribute('data-navLink'));
	} else {
		markActiveLink("#" + target.id);
	}
	$("#sectionTitle").html("");
	$("#sectionTitle").html(sectionTitle);
	getLink($("#" + target.id), e, setSectionContent, console);
}

function handleNavLinkInSideBar(event) {
	handleNavLink(event, false);
}

function handleProjLinkInSideBar(event) {
	confirm("hereh");
	var e = getEvent(event);
	var target = getTarget(e);
	var sectionSubTitle = $("#" + target.id).data('title');
	markActiveLink("#" + target.id);
	$("#sectionSubTitle").html("");
	$("#sectionSubTitle").html(sectionSubTitle);
	getLink($("#" + target.id), e, setSectionContent, displayError);
}

function handleNavLinkInBody(event) {
	handleNavLink(event, true);
}

function console(message){
	$("#console").html("");
	$("#console").html(message);
}

// Project Related

function setProjectContent(jdata) {
	confirm("ppp");
	$("#projContent").html("");
	$("#projContent").html(jdata);
}

function showProjectMenu(event, mark_parent) {
	$(".projMenuWrapper").show();
}

function createProjectName(event) {
	var e = getEvent(event);
	var target = getTarget(e);
	var issues = validateForm($("#" + target.id));
	if (issues !== "") {
		$(".projError").html("<hr/>" + issues);
		$(".errorContainer").show();
		e.preventDefault();
		return false;
	}
	postForm($("#" + target.id), e, appendSectionHtml, projError);
}

function goToProjectHome(event){
	$.get("/project/" +$("#existingProjName").val() + "/menu", function(data) {
		$(".projMenuWrapper").html(data);
		$(".projMenuWrapper").show();
	});
	try{
		sendDirectGetRequest("/project/" + $("#existingProjName").val() + "/home", setSectionContent, console);
	} catch (e){
		confirm(e);
	}
}

function projNameCreateCB(jdata, element) {
/* 		$(".container-fluid").html("");
	$(".container-fluid").html(jdata); */
}

function appendToProjExecute(jdata) {
	$("#execProjContent").html("");
	$("#execProjContent").html(jdata);
}

function executeProject(event) {
	var e = getEvent(event);
	var target = getTarget(e);
	postFormWithButtonLink($("#" + target.id), $("#" + target.id).attr(
			'href'), e, appendToProjExecute, displayError);
}

// Arjuna Settings
function appendToArjunaSettings(jdata) {
	$("#arjunaSettings").html("");
	$("#arjunaSettings").html(jdata);
}

function editArjunaSettings(event) {
	var e = getEvent(event);
	var target = getTarget(e);
	$(".saveConf").show();
	$(".editConf").hide();
	getLink($("#" + target.id), e, appendToArjunaSettings, displayError);
}

function saveArjunaSettings(event) {
	var e = getEvent(event);
	var target = getTarget(e);
	$(".editConf").show();
	$(".saveConf").hide();
	postFormWithButtonLink($("#" + target.id), $("#" + target.id).attr(
			'href'), e, appendToArjunaSettings, displayError);
}

function populateArjunaSettings(jquery, existingConfig){
	$('#built-in-report-gen').multiselect({
		buttonWidth : '100%'
	});
	$('#built-in-report-listener').multiselect({
		buttonWidth : '100%'
	});
	
	var radioKeys = ["report_mode"];

	for (var i = 0; i < radioKeys.length; i++){
		var e = $("[name=\"KEY\"]".replace("KEY", radioKeys[i]));
		e.each(function() {
			if ($(this).val() == existingConfig[radioKeys[i]]){
				$(this).prop('checked', true);
			}
		  });
	}
	
	var multiselectKeys = ["report_generators_builtin", "report_listeners_builtin"];

	for (var i = 0; i < multiselectKeys.length; i++){
		var e = $("[name=\"KEY\"]".replace("KEY", multiselectKeys[i]));
		e.each(function() {
			$(this).multiselect('select', existingConfig[multiselectKeys[i]]);
		  });
	}
	
	var selectKeys = ["logging_console_level", "logging_file_level"];

	for (var i = 0; i < selectKeys.length; i++){
		var e = $("[name=\"KEY\"]".replace("KEY", selectKeys[i]));
		e.each(function() {
			$(this).val(existingConfig[selectKeys[i]])
		  });
	}	
}


function displayDisplayLogLevel(chk) {
	var show = chk.checked ? true : false;
	if (show) {
		$(".dll").show();
	} else {
		$(".dll").hide();
	}
}

function displayFileLogLevel(chk) {
	var show = chk.checked ? true : false;
	if (show) {
		$(".fll").show();
	} else {
		$(".fll").hide();
	}
}
// Config
function editConfig(event) {
	var e = getEvent(event);
	var target = getTarget(e);
	$(".configText").removeAttr("readonly");
	$(".saveConf").show();
	$(".editConf").hide();
}

function saveConfig(event) {
	var e = getEvent(event);
	var target = getTarget(e);
	$(".configText").attr("readonly", "readonly");
	$(".editConf").show();
	$(".saveConf").hide();
	postFormWithButtonLink($("#" + target.id), $("#" + target.id).attr(
			'href'), e, appendToConfContent, displayError);
}