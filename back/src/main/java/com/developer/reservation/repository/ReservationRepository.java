package com.developer.reservation.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.developer.reservation.dto.ReservationDTO;
import com.developer.reservation.entity.Reservation;
import com.developer.studyroom.entity.Studyroom;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	//[SR] 스터디카페 예약내역 목록 출력
	@Query(value="SELECT r.res_seq, ri.name as riName, r.user_id, u.name As uName, r.host_id,"
			+ " r.using_date, r.start_time, r.end_time "
			+ "FROM reservation r JOIN users u ON r.user_id = u.user_id "
			+ "JOIN room_info ri ON   ri.room_seq = r.room_seq "
			+ "JOIN studyroom sr ON   sr.sr_seq = ri.sr_seq "
			+ "WHERE sr.host_id = :hostId "
			+ "AND ri.status = 0 "
			+ "UNION "
			+ "SELECT r.res_seq, ri.name, '', '',r.host_id,r.using_date, r.start_time, r.end_time "
			+ "FROM reservation r "
			+ "JOIN room_info ri ON ri.room_seq = r.room_seq "
			+ "WHERE host_id = :hostId "
			+ "AND ri.status = 0 "
			+ "ORDER BY using_date DESC", nativeQuery = true)
	public List<Object[]> selectAllReservation(@Param("hostId")String hostId);
	
	
	//[SR] 스터디카페 예약상세내역
		@Query(value="SELECT r.res_seq, r.user_id, u.name As uname, u.tel, rif.name, r.using_date, r.start_time, r.end_time, r.host_id "
				+ "FROM room_info rif "
				+ "inner JOIN reservation r ON rif.room_seq = r.room_seq "
				+ "left JOIN users u ON r.user_id = u.user_id "
				+ "WHERE r.res_seq = :resSeq", nativeQuery = true)
		public List<Object[]> selectReservation(@Param("resSeq")long resSeq);
	
}


