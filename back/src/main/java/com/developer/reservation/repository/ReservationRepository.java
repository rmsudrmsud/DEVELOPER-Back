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
	public List<Object> findAllByUsingDate(@Param("roomSeq") Long roomSeq, @Param("usingDate") Date usingDate);
	
	//유저 아이디로 예약내역 찾기
	@Query(value="select * from Reservation where user_id = :userId ", nativeQuery = true)
	public List<Reservation> findByUserId(@Param("userId")String userId);
	
	//예약시퀀스로 찾기 예약상세O
	//public Reservation findById(String resSeq);
	public Reservation findByResSeq(Long resSeq);
	
	//예약시퀀스로 삭제
	@Query(value="delete from Reservation where res_seq = :resSeq ", nativeQuery = true)
	public void deleteByResSeq(@Param("resSeq") Long resSeq);
	//public void deleteById(Long resSeq);
	//예약insert하기 insertRV, 기본메서드인데 해줘야하나?
	//public void InsertRV();
	
	//호스트 아이디로 전부찾기
	@Query(value="select*from RESERVATION where host_id=:hostId", nativeQuery = true)
	public Reservation findByhostUser(@Param("hostId") String hostId);
	
	//n
}
