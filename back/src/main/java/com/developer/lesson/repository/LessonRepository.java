package com.developer.lesson.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.developer.lesson.entity.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
	
    @Query("select DISTINCT l from Lesson l join fetch l.alList ")
    public List<Lesson> findAll();
    
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
