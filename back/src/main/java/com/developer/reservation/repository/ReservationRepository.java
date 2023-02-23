package com.developer.reservation.repository;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.developer.hostuser.entity.HostUser;
import com.developer.reservation.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	
	//룸시퀀스와 예약날짜로 찾기O
	@Query(value = "select r.room_seq, r.start_time, r.end_time, r.using_date, ro.price \r\n"
			+ "from reservation r, room_info ro, studyroom sr\r\n"
			+ "where r.room_seq = ro.room_seq\r\n"
			+ "AND r.room_seq= :roomSeq\r\n"
			+ "AND r.using_date= :usingDate\r\n"
			+ "AND sr.sr_seq = ro.sr_seq\r\n"
			+ "AND to_date(r.start_time,'HH24:MI') >= to_date(sr.open_time,'HH24:MI')\r\n"
			+ "AND to_date(r.end_time,'HH24:MI') <= to_date(sr.end_time,'HH24:MI')", nativeQuery = true)
	public List<Object[]> findAllByUsingDate(@Param("roomSeq") Long roomSeq, @Param("usingDate") Date usingDate);
	
	//유저 아이디로 예약내역 찾기O
	@Query(value="SELECT r. res_seq, s.name AS sName, rif.name AS rifName, r.using_date, r.start_time, r.end_time\r\n"
			+ "FROM studyroom s, room_info rif, reservation r\r\n"
			+ "WHERE s.sr_seq = rif.sr_seq\r\n"
			+ "AND rif.room_seq = r.room_seq\r\n"
			+ "AND r.user_id = :userId\r\n"
			+ "ORDER BY res_seq DESC", nativeQuery = true)
	public List<Object[]> findByUserId(@Param("userId")String userId);
	
	//예약시퀀스로 찾기 예약상세O
	//public Reservation findById(String resSeq);
	public Reservation findByResSeq(Long resSeq);
	

	//호스트 아이디로 예약내역 전체출력O
	@Query(value="select*from RESERVATION where host_id=:hostId", nativeQuery = true)
	public Reservation[] findByhostUser(@Param("hostId") String hostId);
	
	//n
}
