package com.github.karlnicholas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.github.karlnicholas.model.BoardComment;
import com.github.karlnicholas.model.BoardReply;

public interface BoardReplyRepository extends JpaRepository<BoardReply, Long> {

	void deleteByBoardComment(BoardComment boardComment);

	Page<BoardReply> findAllByBoardComment(BoardComment boardComment, Pageable pageable);

}
