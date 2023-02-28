package com.developer.appliedlesson.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.developer.appliedlesson.entity.AppliedLesson;

public interface AppliedLessonRepository extends JpaRepository<AppliedLesson, Long> {  
	
	//근형 진행완료된 클래스 페이지 클래스명, 수강했던 튜티목록
	@Query(value="SELECT u.name, l.lesson_name "
			+ "FROM USERS u, APPLIED_LESSON a, LESSON l "
			+ "WHERE a.al_tutee_id = u.user_id "
			+ "and a.apply_ok = 1"
			+ "and l.end_cdate < TO_CHAR(SYSDATE,'yyyy-mm-dd') "
			+ "and l.lesson_seq = a.al_lesson_seq "
			+ "and l.lesson_seq = :lesson_seq "
			+ "order by u.name desc", nativeQuery=true)
	public List<Object[]> selectClassAndTutee(@Param("lesson_seq") Long lessonSeq);
	
	//근형 진행완료된클래스 후기목록
	@Query(value="SELECT u.name,  r.review, r.star\n"
			+ "from LESSON l, TUTOR t, USERS u, LESSON_REVIEW r, APPLIED_LESSON a "
			+ "where l.tutor_id = t.tutor_id "
			+ "and l.lesson_seq = a.al_lesson_seq "
			+ "and t.tutor_id = u.user_id "
			+ "and r.apply_seq = a.apply_seq "
			+ "and u.user_id = :userId "
			+ "order by l.lesson_seq desc", nativeQuery= true)
	public List<Object[]> selectCompletedClassList(@Param("userId") String userId);
}
