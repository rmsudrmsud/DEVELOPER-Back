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
package com.developer.board.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.developer.board.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
	
	/**
	 * 글전체 출력
	 * @author choigeunhyeong
	 * @return
	 */
	@Query(value="SELECT * FROM BOARD", nativeQuery = true)
	public List<Board> findTest1();
	
	/**
	 * 글 전체중 번호, 내용만 출력
	 * @author choigeunhyeong
	 * @return
	 */
	@Query(value= "SELECT post_seq, content FROM BOARD", nativeQuery = true)
	public List<Object[]> findTest2();
	
	/**
	 * 제목으로 검색
	 * @author choigeunhyeong
	 * @param title
	 * @return
	 */
	public List<Board> findByTitleLike(String title);
	
	/**
	 * 게시글 글번호로 상세검색 (게시글한개 클릭시 들어갈 페이지)
	 * 닉네임+글 상세정보+댓글 상세정보
	 * @author choigeunhyeong
	 * @param postSeq
	 * @return
	 * 댓글 작성자..도 출력해야하는데 이부분추가
	 */
	@Query(value= "select u.user_id,"
			+ "		u.nickname, b.post_seq, b.category, b.title, b.content , b.img_path,"
			+ "		b.c_date,"
			+ "		b.recommend, b.cnt, r.content AS rContent, r.cdate"
			+ "		from users u"
			+ "		full join board b"
			+ "		on u.user_id = b.user_id"
			+ "		full join board_rep r"
			+ "		on b.post_seq = r.post_seq"
			+ "		where b.post_seq= :postSeq", nativeQuery = true)
	public Map<String, Object> findPostSeq(@Param("postSeq") Long postSeq);
	
	/**
	 * 게시글목록 작성일 순으로 정렬해서 출력
	 * @author choigeunhyeong
	 * @return
	 */
	@Query(value="select users.nickname, board.post_seq, board.category, board.title, board.content,"
			+ "		board.img_path, board.c_date, board.recommend, board.cnt"
			+ "		from users"
			+ "		inner join board"
			+ "		on users.user_id = board.user_id"
			+ "		order by c_date desc", nativeQuery = true)
	public List<Map<String, Object>> findAllBoardc_date();
	
	/**
	 * 게시글목록 조회수높은 순으로 정렬해서 출력
	 * @author choigeunhyeong
	 * @return
	 */
	@Query(value="select users.nickname, board.post_seq, board.category, board.title, board.content,"
			+ "		board.img_path, board.c_date, board.recommend, board.cnt"
			+ "		from users"
			+ "		inner join board"
			+ "		on users.user_id = board.user_id"
			+ "		order by cnt desc", nativeQuery = true)
	public List<Map<String, Object>> findAllBoardCnt();
	
	/**
	 * 게시글목록 추천많은순 순으로 정렬해서 출력
	 * @author choigeunhyeong
	 * @return
	 */
	@Query(value="select users.nickname, board.post_seq, board.category, board.title, board.content,"
			+ "		board.img_path, board.c_date, board.recommend, board.cnt"
			+ "		from users"
			+ "		inner join board"
			+ "		on users.user_id = board.user_id"
			+ "		order by recommend desc", nativeQuery = true)
	public List<Map<String, Object>> findAllBoardRecommend();
	
	
	/**
	 * 게시글 조회수 증가 
	 * @author choigeunhyeong
	 * @param postSeq
	 */
	@Query(value="update board set cnt ="
			+ "		cnt+1 where post_seq= :postSeq", nativeQuery = true)
	public Board updateCnt(@Param("postSeq") Long postSeq);
	
	
}
