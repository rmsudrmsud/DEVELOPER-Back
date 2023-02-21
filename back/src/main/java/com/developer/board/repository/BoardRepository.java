package com.developer.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.developer.board.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
	
	/**
	 * 글전체 검색
	 * @return
	 */
	@Query(value="SELECT * FROM BOARD", nativeQuery = true)
	public List<Board> findTest1();
	
	/**
	 * 글번호, 내용 검색
	 * @return
	 */
	@Query(value= "SELECT post_seq, content FROM BOARD", nativeQuery = true)
	public List<Object[]> findTest2();
	
	/**
	 * 제목으로 검색
	 * @param title
	 * @return
	 */
	public List<Board> findByTitleLike(String title);
	
	/**
	 * 게시글 글번호로 상세검색
	 * 닉네임+글 상세정보+댓글 상세정보
	 * @param postSeq
	 * @return
	 * 댓글 작성자..도 출력해야하는데 이부분추가
	 */
	@Query(value= "select u.user_id,"
			+ "		u.nickname, b.category, b.title, b.content , b.img_path,"
			+ "		b.c_date,"
			+ "		b.recommend, b.cnt, r.content AS rContent, r.cdate"
			+ "		from users u"
			+ "		full join board b"
			+ "		on u.user_id = b.user_id"
			+ "		full join board_rep r"
			+ "		on b.post_seq = r.post_seq"
			+ "		where b.post_seq= :postSeq", nativeQuery = true)
	public Object findPostSeq(@Param("postSeq") Long postSeq);
	
}
