package com.developer.LessonReview;

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
import com.developer.lessonreview.entity.LessonReview;
import com.developer.lessonreview.repository.LessonReviewRepository;

@SpringBootTest
public class LessonReviewRepositoryTest {

	@Autowired
	private LessonReviewRepository lrRepository;
	
	@Test
	@DisplayName("insert() 테스트")
	void testSave() {
		LessonReview lr = new LessonReview();
		AppliedLesson al = new AppliedLesson();
		al.setApplySeq(1L);
		lr.setAlLesson(al);
		lr.setLeRevSeq(1L);
		lr.setCDate(null);
		lr.setReview("후기작성테스트중입니다.");
		lr.setStar(4);
		lrRepository.save(lr);
	}
	
	@Test
	@DisplayName("SelectDetail() 테스트")
	void testSelectDetail() {
		Optional<LessonReview> optLR = lrRepository.findById(3L);
		assertTrue(optLR.isPresent());
		Long expected = 3L;
		assertEquals(expected, optLR.get().getLeRevSeq());
	}
	


}
