 $('#newPostText').click(function(e){
    e.preventDefault();
    if ( $('#newPostSubmit').length === 0) {
    	$('#newPostForm').append('<button type="submit" class="btn btn-default" id="newPostSubmit">Create New Post</button>');
    }
 })