package com.developer.lessonreview.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.developer.lessonreview.entity.LessonReview;

public interface LessonReviewRepository extends CrudRepository<LessonReview, Long> {
//	
//	@Query(value = "	SELECT COUNT(*)"
//			+ "FROM lesson_review lr"
//			+ "LEFT OUTER JOIN applied_lesson al"
//			+ "ON lr.lr_apply_seq = al.apply_seq"
//			+ "FULL OUTER JOIN lesson l"
//			+ "ON al.al_lesson_seq = l.lesson_seq"
//			+ "WHERE l.tutor_id = :tutorId", nativeQuery = true)
//	public int cntLReview(@Param("tutorId") String tutorId);
//	
//	@Query(value = "SELECT lr.review, lr.star, u.name"
//			+ "FROM lesson_review lr"
//			+ "LEFT OUTER JOIN applied_lesson al"
//			+ "ON lr.lr_apply_seq = al.apply_seq"
//			+ "FULL OUTER JOIN users u"
//			+ "ON al.al_user_id = u.user_id"
//			+ "WHERE al.user_id = :userId",
//					nativeQuery = true)
//	public List<Object[]> listLRList(@Param("userId") String userId);
//	
//	@Query(value = "SELECT a.apply_seq, l.lesson_name "
//			+ "FROM applied_lesson a "
//			+ "INNER JOIN lesson l "
//			+ "ON a.al_lesson_seq = l.lesson_seq "
//			+ "WHERE a.user_id = :userId MINUS "
//			+ "SELECT a.apply_seq, l.lesson_name "
//			+ "FROM lesson_review lr "
//			+ "INNER JOIN applied_lesson a "
//			+ "ON lr.apply_seq = a.apply_seq "
//			+ "INNER JOIN lesson l "
//			+ "ON a.al_lesson_seq = l.lesson_seq "
//			+ "where a.user_id = :userId",
//					nativeQuery = true)
//	public List<Object[]> noWriteLReview(@Param("userId") String userId);

}
