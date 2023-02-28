package com.developer.studyroom.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.developer.studyroom.entity.Studyroom;

public interface StudyroomRepository extends CrudRepository<Studyroom, Long> {

	/**
	 * 스터디카페 전체출력 
	 * @author choigeunhyeong
	 * @return
	 */
	@Query(value="select * from studyroom order by sr_seq desc", nativeQuery = true)
	public List<Object[]> getAllStudyroom();
	
	//[SR]호스트메인페이지 - 스터디룸+호스트정보 출력
		@Query(value="SELECT h.host_id, h.pwd, h.num, h.ready, h.name AS hname, h.tel, h.email,"
				+ "s.sr_seq, s.name, s.addr, s.info, s.open_time, s.end_time, s.img_path, s.oc "
				+ "FROM host_user h "
				+ "INNER JOIN studyroom s "
				+ "ON h.host_id = s.host_id "
				+ "WHERE h.host_id = :hostId", nativeQuery = true)
		public Optional<Studyroom> getHostAndStudyroom(@Param("hostId") String hostId);
		
		//ds
		@Query(value = "SELECT * \r\n"
				+ "FROM (SELECT sr_seq, name, host_id FROM studyroom ORDER BY sr_seq DESC)\r\n"
				+ "WHERE rownum BETWEEN 1 AND 5",nativeQuery = true)
		public List<Object[]> getList5();
		
		//ds
		@Query(value = "select * from studyroom where SR_Seq= :srSeq", nativeQuery = true)
		public Studyroom getById(@Param("srSeq") Long srSeq);
			

}
