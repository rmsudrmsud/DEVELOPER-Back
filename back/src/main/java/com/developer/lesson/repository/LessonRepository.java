package com.developer.lesson.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.developer.lesson.entity.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
	
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
