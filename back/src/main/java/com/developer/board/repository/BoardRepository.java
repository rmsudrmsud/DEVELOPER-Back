package com.developer.board.repository;

import org.springframework.data.repository.CrudRepository;

import com.developer.board.entity.Board;

public interface BoardRepository extends CrudRepository<Board, Long> {
	
}
