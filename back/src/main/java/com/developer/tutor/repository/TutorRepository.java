package com.developer.tutor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.developer.tutor.entity.Tutor;

public interface TutorRepository extends JpaRepository<Tutor, String> {

}
