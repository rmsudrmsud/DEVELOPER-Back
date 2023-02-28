package com.developer.lesson.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.developer.lesson.dto.LessonDTO;
import com.developer.lesson.entity.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
	
    @Query("select DISTINCT l from Lesson l join fetch l.alList ")
    public List<Lesson> findAll();
    
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

	
	@Query(value="	SELECT l.lesson_name"
			+ "	from LESSON l, TUTOR t, USERS u"
			+ "	where l.tutor_id = t.tutor_id"
			+ "	and t.tutor_id = u.user_id"
			+ "	and u.user_id = :tutorId"
			+ "	and TO_CHAR(SYSDATE,'yyyymmdd')<l.start_cdate"
			+ "	order by l.lesson_seq desc",
			nativeQuery = true)
	public List<Object[]> getLessonByUser1(@Param("tutorId") String tutorId);
	
	
	@Query(value="	SELECT l.lesson_name"
			+ "	from LESSON l, TUTOR t, USERS u"
			+ "	where l.tutor_id = t.tutor_id"
			+ "	and t.tutor_id = u.user_id"
			+ "	and u.user_id = :tutorId"
			+ "	and l.start_cdate<=TO_CHAR(SYSDATE,'yyyymmdd')"
			+ " and TO_CHAR(SYSDATE,'yyyymmdd')<=l.end_cdate"
			+ "	order by l.lesson_seq desc",
			nativeQuery = true)
	public List<Object[]> getLessonByUser2(@Param("tutorId") String tutorId);
	
	
	@Query(value="	SELECT l.lesson_name"
			+ "	from LESSON l, TUTOR t, USERS u"
			+ "	where l.tutor_id = t.tutor_id"
			+ "	and t.tutor_id = u.user_id"
			+ "	and u.user_id = :tutorId"
			+ "	and TO_CHAR(SYSDATE,'yyyymmdd')>l.end_cdate"
			+ "	order by l.lesson_seq desc",
			nativeQuery = true)
	public List<Object[]> getLessonByUser3(@Param("tutorId") String tutorId);
	
	
	@Query(value= "	SELECT l.lesson_name, l.img_path, l.location, l.start_cdate, l.end_cdate,"
			+ "	        l.category, l.people, u.name"
			+ "	FROM TUTOR t, LESSON l, USERS u"
			+ "	WHERE u.user_id = t.tutor_id"
			+ "	and t.tutor_id = l.tutor_id"
			+ "	and l.lesson_seq = :lessonSeq", nativeQuery = true)
	public List<Object[]> getLessonDetail(@Param("lessonSeq") Long lessonSeq);
	
	
	@Query(value=" SELECT l.lesson_name"
			+ " from LESSON l, USERS u, APPLIED_LESSON a"
			+ " where u.user_id = a.tutee_id"
			+ " and l.lesson_seq = a.al_lesson_seq"
			+ " and TO_CHAR(SYSDATE,'yyyy-mm-dd')<l.start_cdate"
			+ " and a.apply_ok=0"
			+ " and u.user_id = :userId"
			+ " order by l.lesson_seq desc", nativeQuery= true)
	public List<Object[]> getApplyLesson(@Param("userId") String userId);
	
	
	@Query(value=" SELECT l.lesson_name"
			+ " from LESSON l, USERS u, APPLIED_LESSON a"
			+ " where u.user_id = a.tutee_id"
			+ " and l.lesson_seq = a.al_lesson_seq"
			+ " and TO_CHAR(SYSDATE,'yyyy-mm-dd')<l.start_cdate"
			+ " and a.apply_ok=1"
			+ " and u.user_id = :userId"
			+ " order by l.lesson_seq desc", nativeQuery= true)
	public List<Object[]> upComingLesson(@Param("userId") String userId);
	
	
	@Query(value=" SELECT l.lesson_name"
			+ " from LESSON l, USERS u, APPLIED_LESSON a"
			+ " where u.user_id = a.tutee_id"
			+ " and l.lesson_seq = a.al_lesson_seq"
			+ " and l.start_cdate <= TO_CHAR(SYSDATE,'yyyy-mm-dd')"
			+ " and TO_CHAR(SYSDATE, 'yyyy-mm-dd') <=l.end_cdate"
			+ " and a.apply_ok=1"
			+ " and u.user_id = :userId"
			+ " order by l.lesson_seq desc", nativeQuery= true)
	public List<Object[]> onGoingLesson(@Param("userId") String userId);
	
}
