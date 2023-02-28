package com.developer.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.developer.board.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
	
	//[SR]메인페이지 - 글작성 최신순으로 list 출력
	@Query(value="SELECT *"
			+ "FROM (SELECT b.post_seq, u.nickname, b.category, b.title, b.content, b.img_path, b.c_date, b.recommend, b.cnt "
			+ "      FROM users u INNER JOIN board b ON u.user_id = b.user_id "
			+ "      ORDER BY c_date DESC)"
			+ "WHERE rownum BETWEEN 1 AND 6", nativeQuery = true)
	public List<Object[]> selectAllByDate();
}
