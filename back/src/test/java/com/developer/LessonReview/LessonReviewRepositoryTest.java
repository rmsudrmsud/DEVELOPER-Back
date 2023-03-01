package com.developer.LessonReview;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.developer.appliedlesson.entity.AppliedLesson;
import com.developer.appliedlesson.repository.AppliedLessonRepository;
import com.developer.lessonreview.entity.LessonReview;
import com.developer.lessonreview.repository.LessonReviewRepository;

@SpringBootTest
public class LessonReviewRepositoryTest {
	private Logger logger = LoggerFactory.getLogger(getClass()); 
	@Autowired
	private LessonReviewRepository lrRepository;
	@Autowired
	private AppliedLessonRepository alRepository;
	
	@Test
	void test(){
		
	}
	
	@Test
	@DisplayName("수업후기 INSERT 테스트")
	void testSave() {
		Optional<AppliedLesson> al = alRepository.findById(2L);
		AppliedLesson appliedLesson = al.get();
		Long applySeq = appliedLesson.getApplySeq();
		
		LessonReview lr = new LessonReview();

		lr.setApplySeq(applySeq);
		lr.setReview("22후기작성테스트중입니다.");
		lr.setStar(4);
		lr.setAppliedLesson(appliedLesson);

	//	lr.setApplySeq(applySeq);
		lr.setReview("후기작성테스트중입니다.");
		lr.setStar(3);
	//	lr.setAppliedLesson(appliedLesson);
		lrRepository.save(lr);
	}
	

	@Test
	@DisplayName("수업후기 SELECT 테스트")
	void testSelectDetail() {
		Optional<LessonReview> optLR = lrRepository.findById(1L);
		assertTrue(optLR.isPresent());
		Long expected = 1L;
		//assertEquals(expected, optLR.get().getApplySeq());
	}
	

}
