package com.github.karlnicholas.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.karlnicholas.model.BoardComment;
import com.github.karlnicholas.model.BoardPost;
import com.github.karlnicholas.model.BoardReply;
import com.github.karlnicholas.repository.BoardCommentRepository;
import com.github.karlnicholas.repository.BoardReplyRepository;

@Service
@Transactional
public class PostDetailService {
	@Autowired private BoardCommentRepository boardCommentRepo;
	@Autowired private BoardReplyRepository boardReplyRepo;
	
	public Page<BoardComment> getBoardComments(BoardPost boardPost, Pageable pageable) {
		return boardCommentRepo.findAllByBoardPost(boardPost, pageable);
	}
	public void createNewBoardComment(BoardComment boardComment) {
		if ( boardComment.getDate() == null ) {
			boardComment.setDate(LocalDateTime.now());
		}
		boardCommentRepo.save(boardComment);
	}
	public Page<BoardReply> getBoardReplies(BoardComment boardComment, Pageable pageable) {
		return boardReplyRepo.findAllByBoardComment(boardComment, pageable);
	}
	public void deleteBoardComment(BoardComment boardComment) {
		boardReplyRepo.deleteByIdBoardComment(boardComment);
		boardCommentRepo.delete(boardComment);
	}
	public BoardComment updateBoardComment(BoardComment boardComment) {
		return boardCommentRepo.save(boardComment);
	}
	public void createNewBoardReply(BoardReply boardReply) {
		if ( boardReply.getDate() == null ) {
			boardReply.setDate(LocalDateTime.now());
		}
		boardReplyRepo.save(boardReply);
	}
	public BoardReply updateBoardReply(BoardReply boardReply) {
		return boardReplyRepo.save(boardReply);
	}
	public void deleteBoardReply(BoardReply boardReply) {
		boardReplyRepo.delete(boardReply);
	}

}
