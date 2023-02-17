package com.developer.lesson;

import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.developer.lesson.entity.Lesson;
import com.developer.lesson.repository.LessonRepository;

@SpringBootTest
public class LessonRepositoryTest {
	private Logger logger = LoggerFactory.getLogger(getClass()); 
	@Autowired
	private LessonRepository lRepository;

	
	@Test
	@DisplayName("SelectDetail() 테스트")
	void testSelectDetail() {
		Iterable<Lesson> all = lRepository.findAll();
		all.forEach((l)->{
			logger.info("즐겨찾기값: " + l.getFlList().get(0).getFavLesSeq()
					+ ", 즐겨찾기한 수업번호: " + l.getFlList().get(0).getLesson().getLessonSeq()
					+ "즐겨찾기값: " + l.getFlList().get(1).getFavLesSeq()
					+ ", 즐겨찾기한 수업번호: " + l.getFlList().get(1).getLesson().getLessonSeq());
		});
	}


	@Test
	@DisplayName("lessonAdd() 테스트")
	void testLessonAdd() {
		Lesson lesson = new Lesson();
		lesson.setCategory(1);
		lesson.setContent("sdfsdsdf");
		Date date = new Date("2023/03/12");
		lesson.setEndCdate(date);
		lesson.setEndDate(date);
		lesson.setImgPath("...");
		lesson.setLessonName("테스트입니다.");
		lesson.setLessonSeq(124L);
		lesson.setLocation("..."); 
		lesson.setPayLesson(0);
		lesson.setPeople(10);
		lesson.setPrice(1000);
		lesson.setStartCdate(date);
		lesson.setStartDate(date);
		lRepository.save(lesson);
	}

}
