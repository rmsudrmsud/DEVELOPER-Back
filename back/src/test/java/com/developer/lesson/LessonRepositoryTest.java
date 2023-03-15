package com.developer.lesson;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.developer.lesson.entity.Lesson;
import com.developer.lesson.repository.LessonRepository;
import com.developer.tutor.entity.Tutor;
import com.developer.tutor.repository.TutorRepository;

@SpringBootTest
public class LessonRepositoryTest {
	private Logger logger = LoggerFactory.getLogger(getClass()); 
	@Autowired
	private LessonRepository lRepository;
	@Autowired
	private TutorRepository tRepository;
	
	
	@Test
	@DisplayName("수업 UPDATE 테스트")
	void testUpdate() {
		Optional<Lesson> l = lRepository.findById(4L);
		Lesson lesson = l.get();
		lesson.setLessonName("수정중");
		lRepository.save(lesson);		
	}
	
	@Test
	@DisplayName("수업 DELETE 테스트")
	void testDelete() {
		Optional<Lesson> l = lRepository.findById(3L);
		Lesson lesson = l.get();
		lRepository.delete(lesson);	
	}

}
