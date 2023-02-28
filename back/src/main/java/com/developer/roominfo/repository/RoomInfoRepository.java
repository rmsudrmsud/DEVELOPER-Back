package com.developer.roominfo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.developer.roominfo.entity.RoomInfo;

public interface RoomInfoRepository extends CrudRepository<RoomInfo, Long> {

	/**
	 * 방 번호에 대한 예약내역출력
	 * @author choigeunhyeong
	 * @param srSeq
	 * @return
	 */
	@Query(value="SELECT r.res_seq, rif.name, u.user_id, u.name AS uName, r.using_date, r.start_time, r.end_time "
			+ "FROM room_info rif, reservation r, users u "
			+ "WHERE rif.room_seq = r.room_seq "
			+ "AND r.user_id = u.user_id "
			+ "AND rif.sr_seq = :srSeq "
			+ "ORDER BY r.using_date DESC", nativeQuery= true)
	public List<Object[]> getReservation(@Param("srSeq") Long srSeq);
	

	//[SR]호스트마이페이지 - 가지고 있는 방 목록 출력
	@Query(value="SELECT rif.room_seq, rif.name, rif.info, rif.img_path, rif.person, rif.price "
			+ "FROM studyroom s, room_info rif "
			+ "where s.sr_seq = rif.sr_seq "
			+ "And s.sr_seq = :srSeq "
			+ "AND rif.status = 0 "
			+ "ORDER BY rif.room_seq ASC ", nativeQuery = true)
	public List<Object[]> selectAllRoom(@Param("srSeq")long srSreq);
}

