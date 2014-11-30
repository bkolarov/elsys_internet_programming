$(document).ready(
		function() {
			$('#button_add_item').click(function() {
				var currentLast = $('#list1 :last-child').text();
				$('#list1').append('<li>item' + (parseInt(currentLast[currentLast.length - 1]) + 1) + '</li>');
			});
		});