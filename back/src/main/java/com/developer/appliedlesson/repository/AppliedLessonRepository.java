package com.developer.appliedlesson.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.developer.appliedlesson.entity.AppliedLesson;

public interface AppliedLessonRepository extends JpaRepository<AppliedLesson, Long> {

	public Optional<AppliedLesson> findByApplySeq(Long applySeq);

   @Query(value = "SELECT u.name"
   +"FROM USERS u, APPLIED_LESSON a, LESSON l"
   +"WHERE a.user_id = u.user_id"
   +"and a.apply_ok = 0"
   +"and l.lesson_seq = a.lesson_seq"
   +"and l.lesson_seq = #{lessonSeq}"
   +"order by u.name desc, nativeQuery = true")
   public Object findLessonApplyUsers0(@Param("userId") String userId);
   
   @Query(value = "SELECT u.name"
   +"FROM USERS u, APPLIED_LESSON a, LESSON l"
   +"WHERE a.user_id = u.user_id"
   +"and a.apply_ok = 1"
   +"and l.lesson_seq = a.lesson_seq"
   +"and l.lesson_seq = #{lessonSeq}"
   +"order by u.name desc, nativeQuery = true")
   public Object findLessonApplyUsers1(@Param("userId") String userId);
   
}
