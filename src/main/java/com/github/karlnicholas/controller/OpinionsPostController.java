package com.github.karlnicholas.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.karlnicholas.model.BoardComment;
import com.github.karlnicholas.model.BoardPost;
import com.github.karlnicholas.model.BoardReply;
import com.github.karlnicholas.service.PostDetailService;
import com.github.karlnicholas.service.PostListingService;


@Controller
@RequestMapping("/post")
public class OpinionsPostController {
	private static final Logger logger = Logger.getLogger(OpinionsPostController.class.getName());	
	@Autowired private PostListingService postListingService;
	@Autowired private PostDetailService postDetailService;
	private static final int defaultListingMaxResults = 10;
	
	@GetMapping
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String postId = request.getParameter("postId");		
		String commentDetail = request.getParameter("commentDetail");		
		logger.fine(()-> "Post Id: " + postId + " commentDetail: " + commentDetail);
		if ( postId != null && !postId.isEmpty() ) {
			Long id = Long.decode( postId.trim() );
			BoardPost boardPost = postListingService.getBoardPost(id);
			request.setAttribute("boardPost", boardPost);
			Page<BoardComment> comments = postDetailService.getBoardComments(boardPost, PageRequest.of(0, defaultListingMaxResults));
			request.setAttribute("comments",  comments.getContent());
			if ( commentDetail != null ) {
				Long commentId = Long.decode( commentDetail.trim() );
				request.setAttribute("commentDetail", commentDetail);
				for ( BoardComment comment: comments) {
					if ( comment.getId() == commentId ) {
						Page<BoardReply> replies = postDetailService.getBoardReplies(comment, PageRequest.of(0, defaultListingMaxResults));
						request.setAttribute("replies", replies.getContent());
						break;
					}
				}
			}
		}
		request.getRequestDispatcher("/WEB-INF/views/post.jsp").forward(request, response);
	}
    
	@PostMapping
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newPostText = request.getParameter("newCommentText").trim();
		String postId = request.getParameter("postId").trim();
		logger.fine(()-> "Post Id: " + postId + " Post Text: " + newPostText);
		if ( !newPostText.isEmpty() && !postId.isEmpty() ) {
			BoardPost boardPost = postListingService.getBoardPost(Long.decode( postId ));
			BoardComment boardComment = new BoardComment();
			boardComment.setBoardPost(boardPost);
			boardComment.setCommentText(newPostText);
			postDetailService.createNewBoardComment(boardComment);
		}
		response.sendRedirect(request.getContextPath() + "/post?postId=" + postId);
    }

}
