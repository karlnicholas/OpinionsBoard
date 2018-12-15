package com.github.karlnicholas.service;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.karlnicholas.model.BoardComment;
import com.github.karlnicholas.model.BoardPost;
import com.github.karlnicholas.repository.BoardCommentRepository;
import com.github.karlnicholas.repository.BoardPostRepository;
import com.github.karlnicholas.repository.BoardReplyRepository;

@Service
@Transactional
public class PostListingService {
	@Autowired private BoardPostRepository boardPostRepo;
	@Autowired private BoardCommentRepository boardCommentRepo;
	@Autowired private BoardReplyRepository boardReplyRepo;
	
	public Page<BoardPost> getBoardPosts(Pageable pageable) {
		return boardPostRepo.findAll(pageable);
	}
	public void createNewBoardPost(BoardPost boardPost) {
		if ( boardPost.getDate() == null ) {
			boardPost.setDate(LocalDateTime.now());
		}
		boardPostRepo.save(boardPost);
	}
	
	public BoardPost updateBoardPost(BoardPost boardPost) {
		return boardPostRepo.save(boardPost);
	}

	public void deleteBoardPost(BoardPost boardPost) {
		List<BoardComment> comments = boardCommentRepo.findAllByBoardPost(boardPost);
		if ( comments != null && comments.size() > 0 ) {
			for ( BoardComment boardComment: comments) {
				boardReplyRepo.deleteByBoardComment(boardComment);
				boardCommentRepo.delete(boardComment);
			}
		}
		boardPostRepo.delete(boardPost);
	}
	public BoardPost getBoardPost(Long primaryKey) {
		return boardPostRepo.findById(primaryKey).get();
	}
}
