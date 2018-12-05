package com.github.karlnicholas.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.github.karlnicholas.model.BoardComment;
import com.github.karlnicholas.model.BoardPost;

public interface BoardCommentRepository extends JpaRepository<BoardComment, Long> {

	List<BoardComment> findAllByBoardPost(BoardPost boardPost);

	Page<BoardComment> findAllByBoardPost(BoardPost boardPost, Pageable pageable);

}
