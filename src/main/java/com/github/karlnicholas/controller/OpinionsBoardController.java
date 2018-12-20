package com.github.karlnicholas.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.karlnicholas.model.BoardPost;
import com.github.karlnicholas.service.PostListingService;

@Controller
@RequestMapping("")
public class OpinionsBoardController {
	private static final Logger logger = Logger.getLogger(OpinionsBoardController.class.getName());	
	@Autowired private PostListingService postListingService;
	private static final int defaultListingMaxResults = 10;

	@GetMapping
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Page<BoardPost> posts = postListingService.getBoardPosts(PageRequest.of(0, defaultListingMaxResults, Sort.by(Direction.DESC, "date")));
		request.setAttribute("posts", posts.getContent());
		request.getRequestDispatcher("/WEB-INF/views/board.jsp").forward(request, response);
	}
    
	@PostMapping
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if ( request.getParameterMap().containsKey("newPostSubmit") ) {
			String newPostText = request.getParameter("newPostText");
			logger.fine(()-> "NewPostText: " + newPostText);
			if ( newPostText != null && !newPostText.isEmpty() ) {
				BoardPost boardPost = new BoardPost();
				boardPost.setPostText(newPostText.trim());
				postListingService.createNewBoardPost(boardPost);
			}
		}
		response.sendRedirect(request.getContextPath());
    }

}
