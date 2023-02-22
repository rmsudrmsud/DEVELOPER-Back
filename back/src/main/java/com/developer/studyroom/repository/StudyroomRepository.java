package com.developer.studyroom.repository;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.repository.CrudRepository;

import com.developer.studyroom.entity.Studyroom;

public interface StudyroomRepository extends CrudRepository<Studyroom, Long> {
			
	
}
