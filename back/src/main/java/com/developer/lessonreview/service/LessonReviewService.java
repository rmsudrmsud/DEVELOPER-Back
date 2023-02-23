package com.developer.lessonreview.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.developer.appliedlesson.entity.AppliedLesson;
import com.developer.appliedlesson.repository.AppliedLessonRepository;
import com.developer.exception.FindException;
import com.developer.lessonreview.entity.LessonReview;
import com.developer.lessonreview.repository.LessonReviewRepository;

@Service
public class LessonReviewService {
	@Autowired
	LessonReviewRepository lrRepository; 
	@Autowired
	private AppliedLessonRepository alRepository;

	//[JW] 튜터의 수업에 대한 후기 작성 
	public void addReview(LessonReview lessonReview) throws FindException {
		Optional<AppliedLesson> al = alRepository.findById(1L);
		AppliedLesson appliedLesson = al.get();
		lessonReview.setAppliedLesson(appliedLesson);
		
		lrRepository.save(lessonReview);
	}
	
	//[JW] 해당 튜터의 후기 개수
	public int cntReview(String tutorId) throws FindException{
		int cnt = lrRepository.cntLReview(tutorId);
		return cnt;
	}
	
	//[JW] 작성한 후기 목록 확인  
	public List<Object[]> lReviewList(String userId) throws FindException{
		List<Object[]> list = lrRepository.listLRList(userId);
		return list;
	}
	
	//[JW] 후기를 작성하지 않은 수업 목록 확인
	public List<Object[]> noWriteLReview(String userId) throws FindException{
		List<Object[]> list = lrRepository.noWriteLReview(userId);
		return list;
	}

}
