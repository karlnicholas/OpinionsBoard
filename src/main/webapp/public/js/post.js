// Use ready() to make a function available after the document is loaded
$(document).ready(function() {
  $('#newCommentText').click(function(e) {
    e.preventDefault();
      if ($('#newCommentSubmit').length === 0) {
    	  $("#newCommentButton").load('html/newCommentButtons.html');
	  }
	});
  $('#newCommentButton').on('click', '#newCommentCancel', function(){
	  $('#newCommentButton').empty();
	  $('#newCommentText').val('');
	});
});
