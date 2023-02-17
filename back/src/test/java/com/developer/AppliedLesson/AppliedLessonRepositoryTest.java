package com.developer.AppliedLesson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.developer.appliedLesson.repository.AppliedLessonRepository;
import com.developer.appliedlesson.entity.AppliedLesson;
import com.developer.lesson.entity.Lesson;

@SpringBootTest
public class AppliedLessonRepositoryTest {

	@Autowired
	private AppliedLessonRepository alRepository;
	
	@Test
	@DisplayName("insert() 테스트")
	void testSave() {
		AppliedLesson al = new AppliedLesson();
		Lesson l = new Lesson();
		l.setLessonSeq(1L);
		al.setLesson(l);
		al.setApplySeq(1L);
		al.setCdate(null);
		al.setApplyOk(0);
		alRepository.save(al);
	}
	
	@Test
	@DisplayName("SelectDetail() 테스트")
	void testSelectDetail() {
		Optional<AppliedLesson> optA = alRepository.findById(1L);
		assertTrue(optA.isPresent());
		Long expected = 1L;
		assertEquals(expected, optA.get().getApplySeq());
	}
	


}
