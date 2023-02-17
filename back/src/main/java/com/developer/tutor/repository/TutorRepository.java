package com.developer.tutor.repository;

import org.springframework.data.repository.CrudRepository;

import com.developer.tutor.entity.Tutor;

public interface TutorRepository extends CrudRepository<Tutor, String> {

}
