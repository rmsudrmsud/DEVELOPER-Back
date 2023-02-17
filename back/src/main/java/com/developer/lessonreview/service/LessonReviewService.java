package com.developer.lessonreview.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.exception.FindException;
import com.developer.lessonreview.entity.LessonReview;
import com.developer.lessonreview.repository.LessonReviewRepository;

@Service
public class LessonReviewService {
	@Autowired
	LessonReviewRepository lrRepository;

	//TODO: DTO 구현 , 기능별로 ? inner class로 관리해도 괜찮은가 ? 
	// map으로 ,,,, 최대한 공통으로 사용되는 멤벼변수들을 dto로 만들고, 그 외의 멤벼변수 값들을 추가하는 방식 맵으로 만들기 

	//[JW] 튜터의 수업에 대한 후기 작성 
	public void addReview(LessonReview lessonReview) throws FindException {
		lrRepository.save(lessonReview);
	}
	
	//[JW] 해당 튜터의 후기 개수
	//[JW] 후기를 작성하지 않은 수업 목록 확인 - AppliedLessonVO
	//[JW] 작성한 후기 목록 확인 - AppliedLessonVO 

}
