$(document).ready(function() {
	"use strict"
	
	// search for jQuery selectors
	// by element name:
	console.log($("body"));
	// by ID - using #
	console.log($("#list1"));
	// by CSS class - using .
	console.log($(".pretty"));
	// by element name and CSS class:
	console.log($("li.pretty"));
	// by parent (using SPACE) - children of ul that have class pretty:
	console.log($("ul .pretty"));

	// we can assign result to a variable: 
	var list = $("#list1");
	
	// to get children of an element
	console.log(list.children());
	
	console.log($(".pretty"));
	// to search in children of element:
	console.log(list.find(".pretty"));

	// handling events:
	// $("li.pretty").on("click", function() { or
	$("li.pretty").click(function() {
		alert("CLICK!");
	});
	$("li.pretty").on("mouseenter", function() {
		console.log("mouse entered the element");
	});
});
/* shorthand:
$(function() {
	"use strict"
	// something when document is ready
});
*/