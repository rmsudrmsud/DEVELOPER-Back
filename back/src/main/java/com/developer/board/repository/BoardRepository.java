package com.developer.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.developer.board.entity.Board;

public interface BoardRepository extends CrudRepository<Board, Long> {
	@Query(value="SELECT * FROM BOARD", nativeQuery = true)
	public List<Board> findTest1();
	
	@Query(value= "SELECT post_seq, content FROM BOARD", nativeQuery = true)
	public List<Object[]> findTest2();
}
