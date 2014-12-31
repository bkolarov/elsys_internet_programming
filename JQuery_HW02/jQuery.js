$(function() { 
	//task 2
	console.log('Task 2');
	console.log($('#footer').children('a').first().attr('title'));

	//task 3
	console.log('Task 3');
	console.log($('.inscreen').children('div').first().children('p').text());

	//task 4
	console.log('Task 4');
	$("#menu-top-level-menu").append('<li><a>new button</a></li>');

	//task 5
	console.log('Task 5');
	$('#footer').prepend('<div id="dynamiccontent"></div>');

	//task 6
	console.log('Task 6');
	$('#footer').children('div').first().append('<input id="textinput"></input>')
});