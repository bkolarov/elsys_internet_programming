(function() {
	"use strict"
	
	// to stop execution to this point:
	debugger;
	// you can add breakpoints from developer console, too
	// conditional breakpoints are supported, too
	
	// to print something (not compatible with all browsers):
	console.log("Hello, world", {a: "b"});
	console.error("Hello, world", {a: "b"});
	
	// display alert on the screen
	alert("Hello world");
	
	// define variable
	var i;
	
	// assign value
	i = 1;
	// increase value
	i++;
	
	if (i === 2) {
		alert("TWO");
	}
	// for, while
	
	// to define array
	var array = [];
	// add to array
	array.push("Hello");
	// size of array
	alert(array.length);
	// access element of an array by index 
	alert(array[0]);

	// define object:
	var o = {
		some: "value"	
	};
	// access field:
	alert(o.some);
	// change field value:
	o.some = "value2";
	// dynamically add fields:
	o.newField = "newValue";
	alert(o.newField);
	
	// dynamic name of field
	o[o.newField] = "dynamic name";
	alert(o.newValue);
	
	// objects are passed by reference
	var otherObject = {};
	// object can contains other objects
	o.subObject = otherObject;
	o.subObject.id = 1;
	if (o.subObject.id === otherObject.id) {
		alert("TRUE");
	}
	

	// define variable and assign anonymous function to it
	var func = function() {
		alert("in func");
	};
	// call function
	func();
	
	// function with parameters:
	var greeting = function(name) {
		alert("Hello, " + name);
		// variable "arguments" contains all parameters
		// passed to function:
		//alert("Hello, " + arguments[0]);
	};
	greeting("World");


	// equality == or ===, see
	// http://dorey.github.io/JavaScript-Equality-Table/
	if ("false") {
		// will be called
	}
	if ("") {
		// will not be called
	}	
	

	// every variable can have value "undefined" 
	// undefined:
	// var bool;
	
	// every variable can have value "null" 
	// null:
	// var bool = null;
	
	// true:
	// var bool = true;
	// same for false
	
	// makes o.subObject to be undefined
	delete o.subObject;
	// this is different than:
	o.subObject = null;	
})();