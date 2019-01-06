// Use ready() to make a function available after the document is loaded
$('#newCommentText').click(function(e) {
	e.preventDefault();
	if ($('#newCommentSubmit').length === 0) {
		$("#newCommentButton").load('html/newCommentButtons.html');
	}
});
$('#newCommentButton').on('click', '#newCommentCancel', function(e){
	var target = $(event.target);
	$('#newCommentButton').empty();
	$('#newCommentText').val('');
});  
$('div.list-group-item').click(function(e) {
	e.preventDefault();
	if (!$('#newReplySubmit').length) {
		var target = $(event.target);
		$.get('html/newReplyButtons.html', function(content) {
			target.append(content);
		});
	}
});
$('div.list-group-item').on('click', '#newReplyCancel', function(e) {
	var target = $(event.target).parent().parent().parent();
	var textSpan = $(target).find('#textspan');
	$(target).empty().append(textSpan);
});
