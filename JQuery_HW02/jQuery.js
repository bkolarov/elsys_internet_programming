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
});