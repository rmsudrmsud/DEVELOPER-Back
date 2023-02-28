package com.developer.roominfo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.developer.roominfo.entity.RoomInfo;

public interface RoomInfoRepository extends CrudRepository<RoomInfo, Long> {
	//[SR]호스트마이페이지 - 가지고 있는 방 목록 출력
	@Query(value="SELECT rif.room_seq, rif.name, rif.info, rif.img_path, rif.person, rif.price "
			+ "FROM studyroom s, room_info rif "
			+ "where s.sr_seq = rif.sr_seq "
			+ "And s.sr_seq = :srSeq "
			+ "AND rif.status = 0 "
			+ "ORDER BY rif.room_seq ASC ", nativeQuery = true)
	public List<Object[]> selectAllRoom(@Param("srSeq")long srSreq);
}

