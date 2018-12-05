package com.github.karlnicholas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.karlnicholas.model.BoardPost;

public interface BoardPostRepository extends JpaRepository<BoardPost, Long> {

}
