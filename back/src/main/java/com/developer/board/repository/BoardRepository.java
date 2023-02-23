package com.developer.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.developer.board.entity.Board;

public interface BoardRepository extends CrudRepository<Board, Long> {
//	@Query(value="SELECT *\r\n"
//			+ "FROM (\r\n"
//			+ "  SELECT rownum rn, a.*\r\n"
//			+ "  FROM (SELECT * FROM product ORDER BY prod_no) a\r\n"
//			+ ") WHERE rn BETWEEN ?1 AND ?1", nativeQuery = true )
//	List<Object[]> findAll(int startRow, int endRow);
}
