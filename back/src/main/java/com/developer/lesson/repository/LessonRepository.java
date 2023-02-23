package com.developer.lesson.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.developer.lesson.entity.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
	
    @Query("select DISTINCT l from Lesson l join fetch l.alList ")
    List<Lesson> findAll();
    
	@Query(value = "SELECT lr.cdate, lr.review, lr.star, al.user_id, l.lesson_name, u.name\n"
			+ "FROM users u\n"
			+ "INNER JOIN applied_lesson al\n"
			+ "ON al.tutor_id = u.user_id\n"
			+ "INNER JOIN lesson l\n"
			+ "ON al.al_lesson_seq = l.lesson_seq\n"
			+ "INNER JOIN lesson_review lr\n"
			+ "ON lr.apply_seq = al.apply_seq\n"
			+ "WHERE l.lesson_seq = :lessonSeq", 
				nativeQuery = true)
	public List<Object[]> selectAllReview(@Param("lessonSeq") Long lessonSeq);
	
	
	@Query(value="SELECT \n"
			+ "	l.lesson_seq, l.lesson_name, l.category, l.content, l.people, l.img_path, l.start_cdate, l.end_cdate,\n"
			+ "	l.price, l.start_date, l.end_date, l.location,\n"
			+ "	t.info, t.img_path AS tutorImg, t.star_avg,\n"
			+ "	u.name\n"
			+ "FROM lesson l\n"
			+ "INNER JOIN tutor t\n"
			+ "ON l.tutor_id = t.user_id\n"
			+ "INNER JOIN users u \n"
			+ "ON t.user_id = u.user_id\n"
			+ "WHERE l.tutor_id = :userId \n"
			+ "AND pay_lesson != 2",
			nativeQuery = true)
	public List<Object[]> selectTutorDetail(@Param("userId") String userId);
}
