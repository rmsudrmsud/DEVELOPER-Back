package com.developer.appliedlesson.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.developer.appliedlesson.entity.AppliedLesson;

public interface AppliedLessonRepository extends JpaRepository<AppliedLesson, Long> {

	@Query(nativeQuery =  true,
				value = "select * from applied_lesson al\n"
						+ "INNER JOIN lesson l\n"
						+ "ON al.al_lesson_seq = l.lesson_seq\n"
						+ "WHERE l.lesson_seq = :lessonSeq")
	public List<AppliedLesson> findByAlLessonSeq(@Param("lessonSeq") Long lessonSeq);


}
