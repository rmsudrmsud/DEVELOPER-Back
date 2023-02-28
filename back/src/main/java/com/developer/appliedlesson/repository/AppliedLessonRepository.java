package com.developer.appliedlesson.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.developer.appliedlesson.entity.AppliedLesson;

public interface AppliedLessonRepository extends JpaRepository<AppliedLesson, Long> {

	//단위테스트용
	public Optional<AppliedLesson> findByApplySeq(Long applySeq);

	/**
	 * 미승인 튜티 리스트 확인
	 * @param userId
	 * @return
	 */
   @Query(value = " SELECT u.name"
   +" FROM USERS u, APPLIED_LESSON a, LESSON l"
   +" WHERE a.tutee_id = u.user_id"
   +" and a.apply_ok = 0"
   +" and l.lesson_seq = a.al_lesson_seq"
   +" and l.lesson_seq = :lessonSeq"
   +" order by u.name desc", nativeQuery = true)
   public List<Object[]> getLessonNotApplyUser(@Param("lessonSeq") long lessonSeq);
   
   /**
    * 승인 튜티 리스트 확인
    * @param userId
    * @return
    */
   @Query(value = " SELECT u.name"
   +" FROM USERS u, APPLIED_LESSON a, LESSON l"
   +" WHERE a.tutee_id = u.user_id"
   +" and a.apply_ok = 1"
   +" and l.lesson_seq = a.al_lesson_seq"
   +" and l.lesson_seq = :lessonSeq"
   +" order by u.name desc", nativeQuery = true)
   public List<Object[]> getLessonApplyUser(@Param("lessonSeq") long lessonSeq);
   

}
