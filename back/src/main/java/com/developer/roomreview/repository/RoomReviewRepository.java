package com.developer.roomreview.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.developer.roomreview.entity.RoomReview;

public interface RoomReviewRepository extends CrudRepository<RoomReview, Long> {

//	// srSeq로 특정 스터디룸 후기 리스트 전체출력
//	@Query(value = "	SELECT u.nickname, s.name AS sName,	r.name AS rifName, rev.cdate, rev.star, rev.content\r\n"
//			+ "		FROM STUDYROOM s, ROOM_INFO r,\r\n"
//			+ "		RESERVATION res, ROOM_REVIEW rev, USERS u\r\n"
//			+ "		WHERE res.res_seq =\r\n"
//			+ "		rev.res_seq\r\n"
//			+ "		and u.user_id = res.user_id\r\n"
//			+ "		and r.room_seq = res.room_seq\r\n"
//			+ "		and s.sr_seq = r.sr_seq\r\n"
//			+ "		and s.sr_seq= :srSeq\r\n"
//			+ "		order by rev.cdate DESC")
//	public List<Object> findRoomReviewAll(@Param("srSeq") Long srSeq);
//	
//	//userId로 작성한 이용후기 목록을 출력한다
//	@Query(value="SELECT s.name AS srName, rif.name AS riName, rr.cdate,\r\n"
//			+ "		rr.star\r\n"
//			+ "		FROM studyroom s, room_info rif, reservation r, room_review rr\r\n"
//			+ "		WHERE s.sr_seq = rif.sr_seq\r\n"
//			+ "		AND rif.room_seq = r.room_seq\r\n"
//			+ "		AND rr.res_seq = r.res_seq\r\n"
//			+ "		AND r.user_id = :userId\r\n"
//			+ "		ORDER BY RR.CDATE DESC", nativeQuery = true)
//	public List<Object> findMyRoomReview(@Param("userId") String userId);
//	
//	//resSeq로 해당 예약의 후기를 전체출력한다
////	@Query(name = "	SELECT s.name AS sName, rif.name AS rifName, r.using_date, rr.cdate,rr.star, rr.content\r\n"
////			+ "		FROM studyroom s, room_info rif, reservation r,room_review rr\r\n"
////			+ "		WHERE rr.res_seq = r.res_seq\r\n"
////			+ "		AND rif.room_seq = r.room_seq\r\n"
////			+ "		AND s.sr_seq = rif.sr_seq\r\n"
////			+ "		AND r.res_seq = :resSeq")
////	public Object findMyRoomReviewDetail(@Param("resSeq") Long resSeq);
//	
//	//아이디값으로 후기를 작성하지 않은 예약리스트를 출력한다
//	@Query(value= "SELECT r.res_seq , s.name AS sName, rif.name AS rifName, r.using_date, r.start_time, r.end_time\r\n"
//			+ "FROM studyroom s, room_info rif, reservation r\r\n"
//			+ "WHERE s.sr_seq = rif.sr_seq\r\n"
//			+ "AND rif.room_seq = r.room_seq\r\n"
//			+ "AND r.user_id = :userId\r\n"
//			+ "AND TO_DATE(SYSDATE,'yyyy-mm-dd') > TO_DATE(r.using_date, 'yyyy-mm-dd')\r\n"
//			+ "MINUS\r\n"
//			+ "SELECT r.res_seq, s.name AS sName, rif.name AS rifName, r.using_date, r.start_time, r.end_time\r\n"
//			+ "FROM studyroom s, room_info rif, reservation r, room_review rr\r\n"
//			+ "WHERE s.sr_seq = rif.sr_seq\r\n"
//			+ "AND rif.room_seq = r.room_seq\r\n"
//			+ "AND r.res_seq = rr.res_seq\r\n"
//			+ "AND r.user_id = :userId", nativeQuery = true)
//	public List<Object> findMyReqRoomReview(@Param("userId") String userId);
	
	
 }
