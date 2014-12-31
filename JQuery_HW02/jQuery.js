$(function() { 
	'use strict';

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
		var li = $('<li/>').text(post.title);
		list.append(li);
		return li;
	}

	//task 12
	console.log('Task 12');
	$('#addbutton').click(function() {
		var textInputText = $('#textinput').val();
		if (textInputText == '') {
			alert('you must enter text');
		} else { 								// task 13
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
	  					var deleteButton = $('<button id="deleteButton"/>');
	  					deleteButton.appendTo(appendToList($('#posts'), getResponse));

	  					deleteButton.text('X'); 			//task 15
	  					deleteButton.click(function() {
	  						alert("deleting");

	  						if (confirm('Are you sure you want to delete this post?')) { //task 16
	  							$.ajax({
	  								type: "DELETE",
	  								url: urlJson + 'posts/' + getResponse.id,
	  								success: function() { 
	  									deleteButton.parent().remove();
	  								}
	  							});
	  						}
	  					})
	  				}
	  			});
	  		}
		});
	}

	//task 17
	$('#posts').before('<p><input id=filterInput type="text"/>');

	//task 18
	$('#filterInput').change(function() {
		var filterInputText = $('#filterInput').val();
		if (filterInputText != '') {
			$.ajax({
				type: "GET",
				url: urlJson + 'posts?userId=' + filterInputText,
				success: function(getResponse) {
					var $ul = $('#posts');

					$ul.empty();			//task 19

					$.each(getResponse, function() {
						var id = this.id;
						var deleteButton = $('<button/>');
						appendToList($('#posts'), this).append(deleteButton.text('X').click(function() {
							console.log(id);
							if (confirm("Are you sure you want to delete this post?")) {		//task 20
								$.ajax({
									type: "DELETE",
									url: urlJson + 'posts/' + id,
									success: function(response) {
										console.log('DELETE request success');
										deleteButton.parent().remove();
									}
								});
							}
						}));
					});
				}
			});
		}
	})
});