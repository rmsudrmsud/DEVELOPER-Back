package com.developer.lesson.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.developer.lesson.entity.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
	
	//[JW]
    @Query("select DISTINCT l from Lesson l join fetch l.alList ")
    public List<Lesson> findAll();
    
	//[JW]
    @Query(nativeQuery = true,
    			 	value = "SELECT lr.cdate AS cDate, lr.review AS review, lr.star AS star, al.tutee_id AS tuteeId, l.lesson_name AS lessonName, u.name AS name "
    			 	+ "FROM users u "
    			 	+ "INNER JOIN applied_lesson al "
    			 	+ "ON al.tutee_id = u.user_id "
    			 	+ "INNER JOIN lesson l "
    			 	+ "ON al.al_lesson_seq = l.lesson_seq "
    			 	+ "INNER JOIN lesson_review lr "
    			 	+ "ON lr.apply_seq = al.apply_seq "
    			 	+ "WHERE l.lesson_seq = :lessonSeq")
	public List<Object[]> selectAllReview(@Param("lessonSeq") Long lessonSeq);
	
	//[JW]
	@Query(nativeQuery = true,
					value ="SELECT * FROM LESSON "
							+ "ORDER BY lesson_seq DESC ")
	public List<Object[]> selectAllLesson();
	
	//[JW]
	public List<Object[]> findByLessonNameContaining(String searchKeyword);

	//[SR]메인페이지 - 신청종료일 임박순으로 list 출력
	@Query(value = "SELECT *"
			+ "FROM (SELECT lesson_seq, lesson_name, img_path, price"
			+ "                FROM lesson"
			+ "                WHERE pay_lesson != 2"
			+ "                AND TO_DATE(end_date, 'YY-MM-DD') >= TO_DATE(sysdate, 'YY-MM-DD')"
			+ "                ORDER BY end_date ASC)"
			+ "WHERE rownum BETWEEN 1 AND 4", nativeQuery = true)
	public List<Object[]> selectAllBydateLesson();
	
}
