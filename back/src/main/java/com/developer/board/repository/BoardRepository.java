package com.developer.board.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.developer.board.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
	
	/**
	 * 게시글 하나 상세페이지
	 * @author choigeunhyeong
	 * @param postSeq
	 * @return
	 */
	@Query(value="select users.nickname, board.post_seq, board.category, board.title, board.content,"
			+ "		board.img_path, board.c_date, board.recommend, board.cnt"
			+ "		from users inner join board on users.user_id = board.user_id"
			+ "		where post_seq=:postSeq", nativeQuery = true)
	public Object[] detailBoard(@Param("postSeq") Long postSeq);
	
	/**
	 * 제목으로 검색
	 * @author choigeunhyeong
	 * @param title
	 * @return
	 */
	public List<Board> findByTitleLike(String title);
	
	
	/**
	 * 게시글 글번호로 상세검색 (게시글 클릭시 들어갈 상세페이지)
	 * 닉네임+글 상세정보+댓글 상세정보
	 * @author choigeunhyeong
	 * @param postSeq
	 * @return
	 */
	@Query(value= "select u.user_id,"
			+ "		u.nickname, b.post_seq, b.category, b.title, b.content , b.img_path,"
			+ "		b.c_date,"
			+ "		b.recommend, b.cnt, r.content AS rContent, r.cdate, u.role, r.user_id As rUser_id"
			+ "		from users u"
			+ "		full join board b"
			+ "		on u.user_id = b.user_id"
			+ "		full join board_rep r"
			+ "		on b.post_seq = r.post_seq"
			+ "		where b.post_seq= :postSeq", nativeQuery = true)
	public List<Object[]> findPostSeq(@Param("postSeq") Long postSeq);

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
	public List<Object[]>getBoardByC_date();	

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
	public List<Object[]> getBoardByCnt();

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
	public List<Object[]> getBoardByRecommend();
	
	
	/**
	 * 게시글 조회수 증가 
	 * @author choigeunhyeong
	 * @param postSeq
	 */
	@Query(value="update board set cnt ="
			+ "		cnt+1 where post_seq= :postSeq", nativeQuery = true)
	public Board updateCnt(@Param("postSeq") Long postSeq);



	//[SR]메인페이지 - 글작성 최신순으로 list 출력
	@Query(value="SELECT *"
			+ "FROM (SELECT b.post_seq, u.nickname, b.category, b.title, b.content, b.img_path, b.c_date, b.recommend, b.cnt "
			+ "      FROM users u INNER JOIN board b ON u.user_id = b.user_id "
			+ "      ORDER BY c_date DESC)"
			+ "WHERE rownum BETWEEN 1 AND 6", nativeQuery = true)
	public List<Object[]> selectAllByDate();
	
}
