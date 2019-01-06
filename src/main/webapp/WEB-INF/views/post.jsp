<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Board</title>
<!-- Bootstrap -->
<link rel='stylesheet' type='text/css' href='webjars/bootstrap/3.3.7/css/bootstrap.min.css' />
<link rel='stylesheet' type='text/css' href='css/non-responsive.css' />
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
  <div class="container">
    <jsp:include page="/WEB-INF/template/navigation.jsp" />
    <div class="panel panel-default">
      <div class="panel-body">
        ${boardPost.postText}
      </div>
    </div>    
    <form method="post" id="newCommentForm" class="form-horizontal">
      <div class="form-group">
        <textarea class="form-control" name="newCommentText" rows="1" placeholder="Create new post" id="newCommentText"></textarea>
      </div>
      <div id="newCommentButton"></div>
    </form>
    <div class="list-group">
      <c:forEach var="comment" items="${comments}">
        <a class="list-group-item" href="${pageContext.request.contextPath}/post?postId=${boardPost.id}&commentDetail=${comment.id}">${comment.commentText}</a>
      </c:forEach>
    </div>
  </div>
  <script src="webjars/jquery/1.12.4/jquery.min.js" type="text/javascript"></script>
  <script src="webjars/bootstrap/3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
  <script src="/js/post.js" type="text/javascript"></script>
</body>
</html>