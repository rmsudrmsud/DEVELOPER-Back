package com.developer.studyroom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.developer.studyroom.entity.Studyroom;

public interface StudyroomRepository extends CrudRepository<Studyroom, Long> {

}
