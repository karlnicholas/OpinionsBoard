// Use ready() to make a function available after the document is loaded
$('#newPostText').click(function(e) {
	e.preventDefault();
	if ($('#newPostSubmit').length === 0) {
		$("#newPostButton").load('html/newPostButtons.html');
	}
});
$('#newPostButton').on('click', '#newPostCancel', function(){
	$('#newPostButton').empty();
	$('#newPostText').val('');
});