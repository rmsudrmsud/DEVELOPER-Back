package com.developer.lessonreview.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.developer.lessonreview.entity.LessonReview;

public interface LessonReviewRepository extends CrudRepository<LessonReview, Long> {
	
	@Query(nativeQuery = true,
				 value = "select * from applied_lesson al\n"
				 		+ "INNER JOIN lesson_review lr\n"
				 		+ "ON al.apply_seq = lr.apply_seq\n"
				 		+ "WHERE lr.apply_seq = :applySeq")
	public List<LessonReview> findAllById(@Param("applySeq") Long applySeq);
	
	@Query(value = "	SELECT COUNT(*)\n"
			+ "FROM lesson_review lr\n"
			+ "LEFT OUTER JOIN applied_lesson al\n"
			+ "ON lr.apply_seq = al.apply_seq\n"
			+ "Inner JOIN lesson l\n"
			+ "ON al.al_lesson_seq = l.lesson_seq\n"
			+ "WHERE l.tutor_id = :tutorId", nativeQuery = true)
	public int cntLReview(@Param("tutorId") String tutorId);
	
	@Query(value = "SELECT lr.review, lr.star, u.name "
			+ "FROM lesson_review lr "
			+ "LEFT OUTER JOIN applied_lesson al "
			+ "ON lr.apply_seq = al.apply_seq "
			+ "FULL OUTER JOIN users u "
			+ "ON al.al_tutee_id = u.user_id "
			+ "WHERE al.tutee_id = :tuteeId ",
					nativeQuery = true)
	public List<Object[]> listLRList(@Param("tuteeId") String tuteeId);
	
	@Query(value = "SELECT a.apply_seq, l.lesson_name "
			+ "FROM applied_lesson a "
			+ "INNER JOIN lesson l "
			+ "ON a.al_lesson_seq = l.lesson_seq "
			+ "WHERE a.user_id = :userId MINUS "
			+ "SELECT a.apply_seq, l.lesson_name "
			+ "FROM lesson_review lr "
			+ "INNER JOIN applied_lesson a "
			+ "ON lr.apply_seq = a.apply_seq "
			+ "INNER JOIN lesson l "
			+ "ON a.al_lesson_seq = l.lesson_seq "
			+ "where a.user_id = :userId",
					nativeQuery = true)
	public List<Object[]> noWriteLReview(@Param("userId") String userId);

}
