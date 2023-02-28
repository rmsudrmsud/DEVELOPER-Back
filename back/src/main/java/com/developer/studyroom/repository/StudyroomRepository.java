package com.developer.studyroom.repository;


import java.util.List;

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
	
	
}
