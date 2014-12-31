$(function() { 
	var urlJson = "http://jsonplaceholder.typicode.com/";

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
	$.ajax(urlJson + 'posts', {
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
	console.log('Task 12');
	$('#addbutton').click(function() {
		var textInputText = $('#textinput').val();
		if (textInputText == '') {
			alert('you must enter text');
		} else { // task 13
			console.log('Task 13');
			var data =  {
				title: textInputText,
 				body: 'random body',
 				userId: 666
			}

			sendPost(data);
		
		}
	});

	function sendPost(data) {
		$.ajax({
	  		type: "POST",
	  		url: urlJson + 'posts',
	  		data: data,
	  		success: function(postResponse) {
	  			console.log('Task 14');
	  			$.ajax({ 									//task 14
	  				type: "GET",
	  				url: urlJson + 'posts/' + postResponse.id,
	  				success: function(getResponse) {
	  					appendToList($('#posts'), getResponse);
	  				}
	  			});
	  		}
		});
	}
});