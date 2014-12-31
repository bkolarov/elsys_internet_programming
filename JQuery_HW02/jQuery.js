$(function() { 
	//task 2
	console.log('Task 2');
	console.log($('#footer').children('a').first().attr('title'));

	//task 3
	console.log('Task 3');
	console.log($('.inscreen').children('div').first().children('p').text());

	//task 4, 9 and 10
	console.log('Task 4');
	$("#menu-top-level-menu").append('<li><a>new button</a></li>').click(function() {
		alert("hello world");

		$('.inscreen').children('div').first().insertAfter($('.inscreen div:nth-child(2)'));
	});

	//task 5
	console.log('Task 5');
	$('#footer').prepend('<div id="dynamiccontent"></div>');

	//task 6
	console.log('Task 6');
	$('#footer').children('div').first().append('<input id="textinput"></input>');

	//task 7
	console.log('Task 7');
	$('#footer').children('div').first().append('<input id="addbutton" type="button"></input>');

	//task 8
	console.log('Task 8');
	$('#footer').children('div').first().append('<ul id="posts"></ul>');

	//task 11
	console.log('Task 11');
	$.ajax("http://jsonplaceholder.typicode.com/posts", {
	  method: "GET"
	}).then(processResponse);

	function processResponse(response) {
		var i = 0;
		$.each(response, function() {
			appendToList($("#posts"), this);

			i++;
			if (i >= 5) {
				return false;
			}
		});
	}

	function appendToList(list, post) {
		list.append($("<li/>").text(post.title));
	}

	//task 12
	$('#addbutton').click(function() {
		if ($('#textinput').val() == '') {
			alert('you must enter text');
		}
	});
});